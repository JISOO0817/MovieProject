package com.example.movieproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieproject.models.MovieModel;
import com.example.movieproject.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {


    //this class is used for VIEWMODEL


    private MovieRepository movieRepository;


    //Constructor
    public MovieListViewModel() {

        movieRepository = MovieRepository.getInstance();
    }


    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

}
