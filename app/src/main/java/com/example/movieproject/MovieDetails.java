package com.example.movieproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieproject.models.MovieModel;

import static java.lang.Float.parseFloat;

public class MovieDetails extends AppCompatActivity {

    private ImageView posterIv;
    private TextView titleTv,descDetailTv;
    private RatingBar ratingBar;
    private float ratingBa;

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


        String title = getIntent().getStringExtra("title");
        String overView = getIntent().getStringExtra("overView");
        String poster = getIntent().getStringExtra("poster");
        float rating = getIntent().getFloatExtra("rating",ratingBa);



        titleTv.setText(title);
        descDetailTv.setText(overView);
        ratingBar.setRating(rating/2);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+poster).into(posterIv);
    }
}