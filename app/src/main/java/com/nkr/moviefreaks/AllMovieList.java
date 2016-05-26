package com.nkr.moviefreaks;

import com.nkr.moviefreaks.model.UpcomingMovies;

/**
 * Created by neel on 17/12/2015.
 */
public class AllMovieList {
    UpcomingMovies[] mUpcomingMovies;

    public UpcomingMovies[] getUpcomingMovies() {
        return mUpcomingMovies;
    }

    public void setUpcomingMovies(UpcomingMovies[] upcomingMovies) {
        mUpcomingMovies = upcomingMovies;
    }
}
