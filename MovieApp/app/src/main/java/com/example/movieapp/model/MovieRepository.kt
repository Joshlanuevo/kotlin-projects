package com.example.movieapp.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.R
import com.example.movieapp.serviceapi.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val application: Application) {
    // Used to abstract the data source details and
    // provides a clean API for the ViewModel to
    // fetch and manage data
    private var movies = ArrayList<Movie>()
    private val mutableLiveData = MutableLiveData<List<Movie>>()

    fun getMutableLiveData(): MutableLiveData<List<Movie>> {
        val movieApiService = RetrofitInstance.service

        val call = movieApiService.getPopularMovies(
            application.applicationContext.getString(R.string.api_key)
        )

        // Perform network request in the background thread and
        // handle the response on the main (UI) thread
        call?.enqueue(object : Callback<Result?> {
            override fun onResponse(call: Call<Result?>, response: Response<Result?>) {
                // Success
                val result = response.body()

                if (result != null && result.results != null) {
                    movies = result.results as ArrayList<Movie>
                    mutableLiveData.value = movies
                }
            }

            override fun onFailure(call: Call<Result?>, t: Throwable) {
            }
        })

        return mutableLiveData
    }
}
