package com.vancoding.contactsmanagerapp.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.vancoding.contactsmanagerapp.bean.ContactBean
import com.vancoding.contactsmanagerapp.repository.ContactRepository

class ContactViewModel(private val repository: ContactRepository) : ViewModel(), Observable {

    val contacts = repository.contacts;
    private var isUpdateOrDelete = false;
    private lateinit var contactToUpdateOrDelete: ContactBean
}