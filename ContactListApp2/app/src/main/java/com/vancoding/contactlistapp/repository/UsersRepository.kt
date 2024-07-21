package com.vancoding.contactlistapp.repository

import com.vancoding.contactlistapp.api.ApiUrl
import com.vancoding.contactlistapp.bean.UsersBean

class UsersRepository(private val usersApi: ApiUrl) {
    suspend fun getUsersList() : List<UsersBean> {
        return usersApi.getUsersList()
    }
}