package com.vancoding.contactlistapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vancoding.contactlistapp.base.BaseViewModel
import com.vancoding.contactlistapp.bean.UsersBean
import com.vancoding.contactlistapp.repository.UsersRepository

/**
 * @Reference
 * @source : Live700 code
 * @author : N/A
 */
class UsersViewModel(private val usersRepository: UsersRepository) : BaseViewModel() {
    val usersLiveData = MutableLiveData<LoadState<List<UsersBean>>>()

    fun getUsersList() {
        if (usersLiveData.value is LoadState.Loading) return
        requestLaunch({
            usersLiveData.value = LoadState.Loading()
            try {
                val result = usersRepository.getUsersList()
                usersLiveData.value = LoadState.Success(result)
            } catch (e:Exception) {
                usersLiveData.value = LoadState.Fail(e)
            }
        }, onError = {
            usersLiveData.value = LoadState.Fail(it)
        }, onStart = {
            usersLiveData.value = LoadState.Loading()
        })
    }
}