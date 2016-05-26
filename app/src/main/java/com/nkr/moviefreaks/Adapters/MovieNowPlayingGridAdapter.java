package com.nkr.moviefreaks.Adapters;

import com.nkr.moviefreaks.Fragments.NowPlayingFragments;
import com.nkr.moviefreaks.Fragments.NowPlayingGridFragment;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieNowPlaying;

/**
 * Created by neel on 23/12/2015.
 */
public class MovieNowPlayingGridAdapter extends RecyclerAdapter {


    private final NowPlayingGridFragment.OnNowPlayingSelectedInterface mListener;
   // private MovieNowPlaying[] movie;

    public MovieNowPlayingGridAdapter(MovieNowPlaying[] movieNowPlayings,NowPlayingGridFragment.OnNowPlayingSelectedInterface listener){
        super(movieNowPlayings);
       // movie=movieNowPlayings;
        mListener=listener;
    }

    @Override
    protected int getLayoutId() {
        return(R.layout.grid_fragment);
    }

    @Override
    protected void onListSelected(MovieNowPlaying movie) {
        mListener.onNowPlayingListSelected(movie);
    }
}
