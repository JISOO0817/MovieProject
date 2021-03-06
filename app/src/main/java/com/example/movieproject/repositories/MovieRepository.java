package com.example.movieproject.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieproject.models.MovieModel;
import com.example.movieproject.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    //This class is acting as repositories

    //singleton
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;



    public static MovieRepository getInstance(){

        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;

    }

    private MovieRepository(){

        movieApiClient = MovieApiClient.getInstance();


    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }
    public LiveData<List<MovieModel>> getPopular(){
        return movieApiClient.getMoviesPop();
    }



    //Calling the method in repository
    public void searchMovieApi(String query, int pageNumber){

        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    public void searchMoviePop(int pageNumber){

        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApiPop(pageNumber);
    }

    public  void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }

}


