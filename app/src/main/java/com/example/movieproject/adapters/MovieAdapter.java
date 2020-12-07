package com.example.movieproject.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.movieproject.MovieDetails;
import com.example.movieproject.R;
import com.example.movieproject.models.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<MovieModel> mMovie;

    private static final int DISPLAY_POP = 1;
    private static final int DISPLAY_SEARCH = 2;

    public MovieAdapter(Context context, List<MovieModel> mMovie) {
        this.context = context;
        this.mMovie = mMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;

        if(viewType == DISPLAY_SEARCH){
            view = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);
            return new ViewHolder(view);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);

            return new ViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MovieModel movieModel = mMovie.get(position);
        String title = movieModel.getTitle();
        String original_language = movieModel.getOriginal_language();
        String release_date = movieModel.getRelease_date();
        float vote_average = movieModel.getVote_average();
        String poster_path = movieModel.getPoster_path();
        String overView = movieModel.getMovie_overview();


        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+poster_path).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MovieDetails.class);
               intent.putExtra("overView",overView);
                intent.putExtra("title",title);
                intent.putExtra("rating",vote_average);
                intent.putExtra("poster",poster_path);

                Toast.makeText(context, "rating:"+vote_average, Toast.LENGTH_SHORT).show();
                context.startActivity(intent);




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



    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, runtime, release_date;
        ImageView image;
        RatingBar ratingBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

              image = itemView.findViewById(R.id.movie_img);


        }
    }


}
