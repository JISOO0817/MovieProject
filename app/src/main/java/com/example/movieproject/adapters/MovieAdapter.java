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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieproject.R;
import com.example.movieproject.models.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<MovieModel> mMovie;


    public MovieAdapter(Context context) {
        this.context = context;

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
        String original_language = movieModel.getOriginal_language();
        String release_date = movieModel.getRelease_date();
        float vote_average = movieModel.getVote_average();
        String poster_path = movieModel.getPoster_path();

        holder.title.setText(title);
        holder.runtime.setText(original_language);
        holder.release_date.setText(release_date);
        holder.ratingBar.setRating(vote_average/2);


        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+poster_path).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "이영화는" + title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mMovie != null){
            return mMovie.size();
        }
       return 0;
    }

    public void setmMovie(List<MovieModel> mMovie) {
        this.mMovie = mMovie;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, runtime, release_date;
        ImageView image;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            runtime = itemView.findViewById(R.id.movie_duration);
            release_date = itemView.findViewById(R.id.movie_category);
            image = itemView.findViewById(R.id.movie_img);
            ratingBar = itemView.findViewById(R.id.ratingBar);



        }
    }


}
