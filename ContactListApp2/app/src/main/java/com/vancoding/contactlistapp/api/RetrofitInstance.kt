package com.vancoding.contactlistapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Reference
 * @source : Udemy
 * @author : abbas masri
 */

class RetrofitInstance {
    companion object {

        private var retrofit: Retrofit? = null
        private const val baseUrl = "https://reqres.in/api/"

        val service : ApiUrl get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create(ApiUrl::class.java)
        }

    }
}