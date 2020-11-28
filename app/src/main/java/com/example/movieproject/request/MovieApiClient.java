package com.example.movieproject.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieproject.AppExecutors;
import com.example.movieproject.models.MovieModel;
import com.example.movieproject.response.MovieSearchResponse;
import com.example.movieproject.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;


public class MovieApiClient {


    //LiveData
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;


    //making Global RUNNABLE

    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance(){
        if(instance == null){
            instance  = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();

    }

    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }


    public void searchMoviesApi(String query, int pageNumber){

        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);;

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                myHandler.cancel(true);
                //Cancelling the retrofit call
            }
        },5000, TimeUnit.MICROSECONDS);
    }

    //Retreving data from RESTAPI by runnable class
    //we have 2 types of Queries : the ID & search Queries

    private class RetrieveMoviesRunnable implements Runnable{

        private String query;
        private int pageNumber;
        boolean cancelRequest;


        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try{
                Response response = getMovies(query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if(response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        //Sending data to live data
                        //PostValue: used for background thread
                        //setValue: not for background thread
                        mMovies.postValue(list);
                    }else{

                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag","Error " + error);
                    mMovies.postValue(null);
                }

            }catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }



        }
            //Search Method/ query

            private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
                return Servicey.getMovieApi().searchMovie(
                        Credentials.API_KEY,
                        query,
                        pageNumber
                );
            }

            private void cancleRequest() {

                Log.v("Tag","Cancelling Search Request");
                cancelRequest = true;
            }



    }

}
