package com.vancoding.contactlistapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vancoding.contactlistapp.base.BaseViewModel
import com.vancoding.contactlistapp.bean.UsersBean
import com.vancoding.contactlistapp.repository.UsersRepository

class UsersViewModel(private val usersRepository: UsersRepository) : BaseViewModel() {
    val usersLiveData = MutableLiveData<LoadState<List<UsersBean>>>()

    fun getUsersList() {
        if (usersLiveData.value is LoadState.Loading) return
        requestLaunch({
            val result = usersRepository.getUsersList()
            usersLiveData.value = LoadState.Success(result)
        }, onError = {
            usersLiveData.value = LoadState.Fail(it)
        }, onStart = {
            usersLiveData.value = LoadState.Loading()
        })
    }
}