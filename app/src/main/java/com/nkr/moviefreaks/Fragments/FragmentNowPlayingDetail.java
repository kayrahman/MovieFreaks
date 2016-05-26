package com.nkr.moviefreaks.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nkr.moviefreaks.Acitivitiees.MainActivity;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieNowPlaying;

/**
 * Created by neel on 11/04/2016.
 */
public class FragmentNowPlayingDetail extends Fragment {

   private MovieNowPlaying detailMovie;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      //  Intent intent=getIntent();


        Bundle bundle=this.getArguments();


        detailMovie=bundle.getParcelable(MainActivity.DETAILED_NOW);

            View view = inflater.inflate(R.layout.fragment_nowplaying_detail, container, false);

            TextView mTitle = (TextView) view.findViewById(R.id.title_now_detail);
            TextView mOverview = (TextView) view.findViewById(R.id.overview_now_detail);

        if(bundle!=null) {
            mTitle.setText(detailMovie.getTitle());
            mOverview.setText(detailMovie.getOverview());
        }
            return view;
        }
    }
