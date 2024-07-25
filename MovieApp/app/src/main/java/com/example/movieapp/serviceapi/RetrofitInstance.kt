package com.example.movieapp.serviceapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Acts as a Central configuration point for
    // defining how HTTP requests and responses
    // should be handled.
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val service: MovieApiService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create(MovieApiService::class.java)
        }
}
