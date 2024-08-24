package com.vancoding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vancoding.viewmodel.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private var mRepository = MainRepository()
    var users : MutableLiveData<List<User>?> = MutableLiveData()

    fun getUserData() {
        viewModelScope.launch {
            var result : List<User>? = null
            withContext(Dispatchers.IO){
                result = mRepository.getUsers()
            }
            users.value = result
        }
    }
}