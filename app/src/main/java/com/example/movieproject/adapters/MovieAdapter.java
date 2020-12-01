package com.example.movieproject.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieproject.R;
import com.example.movieproject.models.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<MovieModel> mMovie;


    public MovieAdapter(Context context, List<MovieModel> mMovie) {
        this.context = context;
        this.mMovie = mMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MovieModel movieModel = mMovie.get(position);
        String title = movieModel.getTitle();
        int duration = movieModel.getRuntime();
        String release = movieModel.getRelease_date();
        float rating = movieModel.getVote_average();

        holder.title.setText(title);
        holder.duration.setText(duration);
        holder.category.setText(release);
        holder.ratingBar.setRating(rating/2);


        try{

        }catch (Exception e){}


    }

    @Override
    public int getItemCount() {
        return mMovie.size();
    }

    public void setmMovie(List<MovieModel> mMovie) {
        this.mMovie = mMovie;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, duration, category;
        ImageView image;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            duration = itemView.findViewById(R.id.movie_duration);
            category = itemView.findViewById(R.id.movie_category);
            image = itemView.findViewById(R.id.movie_img);
            ratingBar = itemView.findViewById(R.id.ratingBar);



        }
    }


}
