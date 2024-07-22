package com.vancoding.contactlistapp.api

import com.vancoding.contactlistapp.bean.UserResponse
import com.vancoding.contactlistapp.bean.UsersBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * @Reference
 * @source : Udemy
 * @author : abbas masri
 */

interface ApiUrl {
    @GET("/api/users")
    fun getUsersList(): Call<UserResponse>
}