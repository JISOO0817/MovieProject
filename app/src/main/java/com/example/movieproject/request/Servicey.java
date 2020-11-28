package com.example.movieproject.request;

import com.example.movieproject.utils.Credentials;
import com.example.movieproject.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {


    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());


    //싱글톤 패턴
    private static Retrofit retrofit = retrofitBuilder.build();

    //사용할 api
    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi(){
        return movieApi;
    }


}
