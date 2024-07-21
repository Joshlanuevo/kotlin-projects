package com.vancoding.contactlistapp.repository

import android.util.Log
import com.vancoding.contactlistapp.api.ApiUrl
import com.vancoding.contactlistapp.bean.UsersBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val usersApi: ApiUrl) {
    suspend fun getUsersList() : List<UsersBean> {
        return withContext(Dispatchers.IO) {
            val response = usersApi.getUsersList().execute()
            if (response.isSuccessful) {
                val users = response.body()?.data ?: emptyList()
                Log.d("UsersRepository", "List of users: $users")
                users
            } else {
                Log.e("UsersRepository", "Error: ${response.errorBody()?.string()}")
                emptyList()
            }
        }
    }
}