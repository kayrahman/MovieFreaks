package com.nkr.moviefreaks.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieTopRated;
import com.nkr.moviefreaks.model.TopRatedSeries;
import com.squareup.picasso.Picasso;

/**
 * Created by neel on 23/12/2015.
 */
public class TopRatedMovieAdapter extends RecyclerView.Adapter<TopRatedMovieAdapter.TopRatedMovieViewHolder> {


    private Context mContext;
    private MovieTopRated[] mMovieTopRateds;

    public TopRatedMovieAdapter(MovieTopRated[] movieTopRateds) {
        mMovieTopRateds = movieTopRateds;
    }


    @Override
    public TopRatedMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_rated_movie_list,parent,false);
        TopRatedMovieViewHolder viewHolder=new TopRatedMovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TopRatedMovieViewHolder holder, int position) {
        holder.bindTopRatedMovie(mMovieTopRateds[position]);


    }

    @Override
    public int getItemCount() {
        return mMovieTopRateds.length;
    }

    public class TopRatedMovieViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mOverView;
        public TextView mReleaseDate;
        public ImageView mPoster;

        public TopRatedMovieViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.top_rated_movie_title);
            mOverView = (TextView) itemView.findViewById(R.id.top_rated_movie_overview);
            mReleaseDate = (TextView) itemView.findViewById(R.id.top_rated_movie_releaseDate);
            mPoster = (ImageView) itemView.findViewById(R.id.top_rated_movie_posterImage);
        }

        public void bindTopRatedMovie(MovieTopRated topRatedMovie) {
            mTitle.setText(topRatedMovie.getTitle());
            mOverView.setText(topRatedMovie.getOverview());
            mReleaseDate.setText(topRatedMovie.getReleaseDate());

            Picasso.with(itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w500/" + topRatedMovie.getPoster())
                    .placeholder(R.drawable.placeholder2)
                    .into(mPoster);

        }
    }
}
