package com.vancoding.contactlistapp.api

import com.vancoding.contactlistapp.bean.UserResponse
import com.vancoding.contactlistapp.bean.UsersBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * @Reference
 * @author: Mullatoez
 * @date : Mar 30, 2023
 * */

interface ApiUrl {
    @GET("/api/users")
    fun getUsersList(): Call<UserResponse>
}