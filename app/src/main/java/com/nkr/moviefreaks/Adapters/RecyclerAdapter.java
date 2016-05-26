package com.nkr.moviefreaks.Adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkr.moviefreaks.Fragments.NowPlayingFragments;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieNowPlaying;
import com.squareup.picasso.Picasso;

/**
 * Created by neel on 13/04/2016.
 */
public abstract class RecyclerAdapter extends RecyclerView.Adapter{
//
   private  MovieNowPlaying[] movie;

    protected RecyclerAdapter(MovieNowPlaying[] movie) {
        this.movie = movie;
    }

//

//    private final NowPlayingFragments.OnNowPlayingSelectedInterface mListener;
//
//    public RecyclerAdapter(MovieNowPlaying[] m, NowPlayingFragments.OnNowPlayingSelectedInterface listener){
//        movie=m;
//        mListener =listener ;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),parent,false);
        return new ListViewHolder(view) ;
    }

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ( (ListViewHolder)holder).bindView(movie[position]);
    }

    @Override
    public int getItemCount() {
        return movie.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitle;
        private TextView mOverview;
        private ImageView mImage;
       // private MovieNowPlaying[] movie;

        public ListViewHolder(View itemView) {
            super(itemView);
            mTitle=(TextView)itemView.findViewById(R.id.movie_now_playing_title);
            mOverview=(TextView)itemView.findViewById(R.id.movie_now_playing_overview);
            mImage=(ImageView)itemView.findViewById(R.id.posterImage_MovieNowPlaying);
            itemView.setOnClickListener(this);
        }

        public void bindView(MovieNowPlaying movie ){
            mTitle.setText(movie.getTitle());
 //           mOverview.setText(movie.getOverview());

            Picasso.with(itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w500/"+movie.getPoster())
                    .placeholder(R.drawable.placeholder2)
                    .into(mImage);

        }

        @Override
        public void onClick(View v) {
            onListSelected(movie[getAdapterPosition()]);

        }
    }

   protected abstract void onListSelected(MovieNowPlaying movieNowPlaying);
}
