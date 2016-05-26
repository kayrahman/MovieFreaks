package com.nkr.moviefreaks.Adapters;

import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieNowPlaying;

/**
 * Created by neel on 15/04/2016.
 */
public class TopRatedGridAdapter extends RecyclerAdapter {

    public TopRatedGridAdapter(MovieNowPlaying[] movie) {
        super(movie);

    }

    @Override
    protected int getLayoutId() {
        return (R.layout.grid_fragment);
    }

    @Override
    protected void onListSelected(MovieNowPlaying movieNowPlaying) {


    }
}
