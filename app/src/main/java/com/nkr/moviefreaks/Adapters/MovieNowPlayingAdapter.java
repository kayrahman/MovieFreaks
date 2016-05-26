package com.nkr.moviefreaks.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkr.moviefreaks.Fragments.NowPlayingFragments;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieNowPlaying;
import com.squareup.picasso.Picasso;

/**
 * Created by neel on 23/12/2015.
 */
public class MovieNowPlayingAdapter extends RecyclerView.Adapter<MovieNowPlayingAdapter.MovieNowPlayingViewHolder>{

  //  private Context mContext;
    private final NowPlayingFragments.OnNowPlayingSelectedInterface mListener;



    private MovieNowPlaying[] mMovieNowPlayings;

    public MovieNowPlayingAdapter(MovieNowPlaying[] movieNowPlayings,NowPlayingFragments.OnNowPlayingSelectedInterface listener){
        mMovieNowPlayings=movieNowPlayings;
        mListener=listener;
    }

    @Override
    public MovieNowPlayingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view=LayoutInflater.from(parent.getContext())
            .inflate(R.layout.movie_playing_now_list, parent, false);
        MovieNowPlayingViewHolder viewHolder=new MovieNowPlayingViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MovieNowPlayingViewHolder holder, int position) {
        holder.bindMovieNowPlaying(mMovieNowPlayings,position);
    }

    @Override
    public int getItemCount() {
        return mMovieNowPlayings.length;
    }

    public class MovieNowPlayingViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        public TextView mTitle;
        public TextView mOverView;
        public TextView mReleaseDate;
        public ImageView mPosterImage;
        public int mIndex;
       // public MovieNowPlaying[] mMovieNowPlaying;

      //  Context mContext;

        public MovieNowPlayingViewHolder(View itemView) {
            super(itemView);



            mTitle=(TextView)itemView.findViewById(R.id.movie_now_playing_title);
            mOverView=(TextView)itemView.findViewById(R.id.movie_now_playing_overview);
            mReleaseDate=(TextView)itemView.findViewById(R.id.movie_now_playing_releaseDate);
            mPosterImage=(ImageView)itemView.findViewById(R.id.posterImage_MovieNowPlaying);
            itemView.setOnClickListener(this);
        }

        public void bindMovieNowPlaying(MovieNowPlaying[] movieNowPlaying,int position){
            mTitle.setText(movieNowPlaying[position].getTitle());
            mOverView.setText(movieNowPlaying[position].getOverview());
            mReleaseDate.setText(movieNowPlaying[position].getReleaseDate());
           mIndex=position;


          //  Picasso.with(mContext)
                //    .load("http://image.tmdb.org/t/p/w500/"+movieNowPlaying.getPoster())
                  //  .placeholder(R.drawable.placeholder2)
                   // .into(mPoster);

        //    Picasso.with(mContext)
          //          .load("http://image.tmdb.org/t/p/w500/"+movieNowPlaying.getPoster())
            //        .placeholder(R.drawable.placeholder2)
              //      .into(mPosterImage);

        Picasso.with(itemView.getContext())
                .load("http://image.tmdb.org/t/p/w500/"+movieNowPlaying[position].getPoster())
                .placeholder(R.drawable.placeholder2)
                .into(mPosterImage);

       }


        @Override
        public void onClick(View v) {
            mListener.onNowPlayingListSelected(mMovieNowPlayings[getAdapterPosition()]);

        }
    }

}
