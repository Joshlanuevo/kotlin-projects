package com.example.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    // Viewmodel: suitable for non-Android-specific logic
    // AndroidViewModel: used when viewModel class needs to
    //                   access Android-specific components

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.movieRepository = new MovieRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllMovies(){
        return movieRepository.getMutableLiveData();
    }
}
