package com.vancoding.contactsmanagerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vancoding.contactsmanagerapp.repository.ContactRepository


// If your viewmodel has a constructor with parameters
// you can't use the default constructor that the
// viewmodel framework provides

// ViewModelFactory : Pass the required parameters to ViewModel.

class ContactViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class");
    }
}