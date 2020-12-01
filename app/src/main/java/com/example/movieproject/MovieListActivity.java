package com.example.movieproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.movieproject.models.MovieModel;
import com.example.movieproject.request.Servicey;
import com.example.movieproject.response.MovieSearchResponse;
import com.example.movieproject.utils.Credentials;
import com.example.movieproject.utils.MovieApi;
import com.example.movieproject.viewmodels.MovieListViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {


    //Before we run our app, we need to add the Network Security.



    Button btn;

    // ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);


        //Calling the observers

        ObserveAnyChange();

        //Testing the Method

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Displaying only the results of page 1


                searchMovieApi("Fast",1);
            }
        });



    }

    // Observing any data change
    private void ObserveAnyChange(){

        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change

                if(movieModels != null){
                    for(MovieModel movieModel:movieModels){
                        // Get the data in log
                        Log.v("Tag","onChanged:" + movieModel.getTitle());
                    }
                }
            }
        });

    }


    //Calling method in Main Activity

    private void searchMovieApi(String query, int pageNumber){
        movieListViewModel.searchMovieApi(query, pageNumber);
    }


    private void GetRetrofitResponse() {


        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi
                .searchMovie(
                        Credentials.API_KEY,
                        "Action",
                        1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if(response.code() == 200) { //okay


                    Log.v("Tag","the response" + response.body().toString());

                    List<MovieModel>  movies = new ArrayList<>(response.body().getMovies());

                    for(MovieModel movie : movies){
                        Log.v("Tag","The List :" + movie.getTitle());
                    }
                }else{

                    try{
                        Log.v("Tag","Error" + response.errorBody().toString());
                    }catch (Exception e){}
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });


    }

    private void GetRetrofitResponseAccordingToId(){

        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                550,Credentials.API_KEY);

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                if(response.code() == 200){
                      MovieModel movie = response.body();
                      Log.v("Tag","The Response " + movie.getTitle());
                }else{

                    try{
                        Log.v("Tag","Error " + response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

}


