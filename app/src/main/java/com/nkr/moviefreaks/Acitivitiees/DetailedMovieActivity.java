package com.nkr.moviefreaks.Acitivitiees;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.UpcomingMovies;
import com.squareup.picasso.Picasso;

public class DetailedMovieActivity extends AppCompatActivity {



    private UpcomingMovies mUpcomingMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //ADS GO HERE//
        AdView mAdView = (AdView) findViewById(R.id.adView_Detail);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        Intent intent=getIntent();

        mUpcomingMovies=intent.getExtras().getParcelable(MainActivity.DETAILED_INFO);

        TextView mTitle=(TextView)findViewById(R.id.title_detail);
        TextView mOverview=(TextView)findViewById(R.id.overview_detail);
        TextView mReleaseDate=(TextView)findViewById(R.id.releaseDate_detail);
        ImageView mImage=(ImageView)findViewById(R.id.detailed_image);

        mTitle.setText(mUpcomingMovies.getTitle());
        mOverview.setText(mUpcomingMovies.getOverview());
        mReleaseDate.setText(mUpcomingMovies.getReleaseDate());
       // mImage.setImageResource(mUpcomingMovies.getPoster());
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500/"+mUpcomingMovies.getPoster())
                .placeholder(R.drawable.placeholder2)
                .into(mImage);









      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
