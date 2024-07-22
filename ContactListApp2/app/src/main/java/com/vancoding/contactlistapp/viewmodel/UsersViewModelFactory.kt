package com.vancoding.contactlistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vancoding.contactlistapp.repository.UsersRepository

/**
 * @Reference
 * @author: Mullatoez
 * @date : Mar 30, 2023
 * */
class UsersViewModelFactory(
    private val repository: UsersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)){
            return UsersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}