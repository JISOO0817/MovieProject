package com.example.movieproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.movieproject.models.MovieModel;

public class MovieDetails extends AppCompatActivity {

    private ImageView posterIv;
    private TextView titleTv,descDetailTv;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        posterIv = findViewById(R.id.posterIv);
        titleTv = findViewById(R.id.titleTv);
        descDetailTv = findViewById(R.id.descDetailTv);
        ratingBar = findViewById(R.id.rating);

        getData();
    }

    private void getData() {

        if(getIntent().hasExtra("movie")){
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

        }

    }
}