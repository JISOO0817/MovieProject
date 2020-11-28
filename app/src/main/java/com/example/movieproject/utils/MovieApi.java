package com.example.movieproject.utils;

import com.example.movieproject.models.MovieModel;
import com.example.movieproject.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {


    //Search for movies
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );


    //https://api.themoviedb.org/3/movie/550?api_key=fff72fc82622cdebdad5616b557c33dd
    //Remember that movie_id =  550 is for Fight Club movie
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key

    );
}
