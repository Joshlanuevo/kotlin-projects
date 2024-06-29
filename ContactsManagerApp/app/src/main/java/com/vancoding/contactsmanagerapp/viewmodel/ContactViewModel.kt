package com.vancoding.contactsmanagerapp.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vancoding.contactsmanagerapp.bean.ContactBean
import com.vancoding.contactsmanagerapp.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel(), Observable {

    val contacts = repository.contacts;
    private var isUpdateOrDelete = false;
    private lateinit var contactToUpdateOrDelete: ContactBean;

    // Data Binding with Live Data
    @Bindable
    val inputName = MutableLiveData<String?>();

    @Bindable
    val inputEmail = MutableLiveData<String?>();

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>();

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>();

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun insert(contact: ContactBean) = viewModelScope.launch {
        repository.insert(contact);
    }

    fun delete(contact: ContactBean) = viewModelScope.launch {
        repository.delete(contact);

        // Reseting the buttons and fields
        inputName.value = null;
        inputEmail.value = null;
        isUpdateOrDelete = false;
        saveOrUpdateButtonText.value = "Save";
        clearAllOrDeleteButtonText.value = "Clear All";
    }

    fun update(contact: ContactBean) = viewModelScope.launch {
        repository.update(contact);

        // Reseting the buttons and fields
        inputName.value = null;
        inputEmail.value = null;
        isUpdateOrDelete = false;
        saveOrUpdateButtonText.value = "Save";
        clearAllOrDeleteButtonText.value = "Clear All";
    }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll();
    }

    // Detecting if inserting or updating a new contact in the database.
    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            // Make an update:
            contactToUpdateOrDelete.name = inputName.value!!
            contactToUpdateOrDelete.email = inputEmail.value!!
            update(contactToUpdateOrDelete);
        } else {
            // Inserting a new contact
            val name = inputName.value!!
            val email = inputEmail.value!!

            insert(ContactBean(0, name, email))

            // Reset the name and email
            inputName.value = null;
            inputEmail.value = null;
        }
    }

    // Detecting if delete or clear all functionality
    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(contactToUpdateOrDelete);
        } else {
            clearAll();
        }
    }

    // Detect if this an update or delete function
    fun initUpdateAndDelete(contact: ContactBean) {
        inputName.value = contact.name;
        inputEmail.value = contact.email;
        isUpdateOrDelete = true;
        contactToUpdateOrDelete = contact;
        saveOrUpdateButtonText.value = "Update";
        clearAllOrDeleteButtonText.value = "Delete";
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}