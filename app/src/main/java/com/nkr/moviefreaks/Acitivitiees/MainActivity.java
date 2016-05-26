package com.nkr.moviefreaks.Acitivitiees;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nkr.moviefreaks.AllMovieList;
import com.nkr.moviefreaks.Fragments.FragmentNowPlayingDetail;
import com.nkr.moviefreaks.Fragments.MovieTopRatedFragment;
import com.nkr.moviefreaks.Fragments.NowPlayingFragments;
import com.nkr.moviefreaks.Fragments.NowPlayingGridFragment;
import com.nkr.moviefreaks.Fragments.TopRatedSeriesFragment;
import com.nkr.moviefreaks.Fragments.UpcomingMovieFragment;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieNowPlaying;
import com.nkr.moviefreaks.model.UpcomingMovies;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,NowPlayingFragments.OnNowPlayingSelectedInterface,
NowPlayingGridFragment.OnNowPlayingSelectedInterface{


    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String MOVIE = "MOVIE";
    public static final String DETAILED_INFO = "DETAILED_INFO";
    public static final String DETAILED_NOW = "detailed_now";
    //  private UpcomingMovies[] mUpcomingMovies;
    private MovieNowPlaying mMovieNowPlayings;
    boolean isTablet;

    // private MovieAdapter mMovieAdapter;
  //  AllMovieList mAllMovieList;


//      @Bind(R.id.imagePoster)
//      ImageView mImageView;
//    @Bind(R.id.title) TextView mTitle;
//      @Bind(R.id.overview)
//      TextView mOverview;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         isTablet=getResources().getBoolean(R.bool.is_tablet);
       // Toast.makeText(MainActivity.this,isTablet+"",Toast.LENGTH_SHORT).show();
        // setSupportActionBar(toolbar);
       // ButterKnife.bind(this);

       // UpcomingMovieFragment savedFragment=(UpcomingMovieFragment) getFragmentManager().findFragmentById(R.id.contentPlaceHolder);
        if(!isTablet){


          if(savedInstanceState==null) {
              UpcomingMovieFragment fragment = new UpcomingMovieFragment();
              FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contentPlaceHolder, fragment);
            fragmentTransaction.commit();
          }
        }else{

            if(savedInstanceState==null) {

                NowPlayingGridFragment fragment = new NowPlayingGridFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.contentPlaceHolder, fragment);
                fragmentTransaction.commit();
            }
        }

//

/*
        //Ads go here
      // AdView mAdView = (AdView) findViewById(R.id.adView_main);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);

*/

        //NEED TO CLEAN UP

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
      //  boolean isTablet=getResources().g

        if (id == R.id.nav_NowPlayingMovie) {
            // Toast.makeText(MainActivity.this, "On progress ", Toast.LENGTH_LONG).show();
            // Handle the camera action

//            Intent intent=new Intent(this, MovieNowPlayingActivity.class);
//            startActivity(intent);

            NowPlayingFragments fragment = new NowPlayingFragments();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentPlaceHolder, fragment);
           fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();



        } else if (id == R.id.nav_TopRatedTvSeries) {
            //Toast.makeText(MainActivity.this, "On progress ", Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(MainActivity.this,TopRatedSeriesFragment.class);
//            startActivity(intent);
            TopRatedSeriesFragment fragment = new TopRatedSeriesFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentPlaceHolder, fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_TopRatedTMovie) {

            // Toast.makeText(MainActivity.this, "On progress ", Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(MainActivity.this, MovieTopRatedFragment.class);
//            startActivity(intent);

//            MovieTopRatedFragment fragment = new MovieTopRatedFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.contentPlaceHolder, fragment);
//            fragmentTransaction.commit();

            if(!isTablet){


                    MovieTopRatedFragment fragment = new MovieTopRatedFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.contentPlaceHolder, fragment);
                    fragmentTransaction.commit();

            }else{

                MovieTopRatedFragment fragment = new MovieTopRatedFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.contentPlaceHolder, fragment);
                fragmentTransaction.commit();

//                    NowPlayingGridFragment fragment = new NowPlayingGridFragment();
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.add(R.id.contentPlaceHolder, fragment);
//                    fragmentTransaction.commit();

            }




        } else if (id == R.id.nav_airingToday) {
            //  Toast.makeText(MainActivity.this, "On progress ", Toast.LENGTH_LONG).show();

            Intent intent=new Intent(MainActivity.this, AiringTodayTvSeriesActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {
            Toast.makeText(MainActivity.this, "On progress ", Toast.LENGTH_LONG).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onNowPlayingListSelected(MovieNowPlaying m) {


        FragmentNowPlayingDetail fragmentNowPlayingDetail=new FragmentNowPlayingDetail();
        mMovieNowPlayings=m;
        Bundle bundle=new Bundle();
        bundle.putParcelable(DETAILED_NOW, mMovieNowPlayings);
        fragmentNowPlayingDetail.setArguments(bundle);


       // FragmentNowPlayingDetail fragment = new FragmentNowPlayingDetail();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentPlaceHolder,fragmentNowPlayingDetail);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


//        Intent intent =new Intent(this,FragmentNowPlayingDetail.class);
//        intent.putExtra(DETAILED_INFO,m);
//
//        startActivity(intent);
    }




    //NEED TO CLEAN UP

 /*   private void updateDiaplay() {
        //mTitle.setText(mUpcomingMovies.);
        MovieAdapter adapter = new MovieAdapter(this, mAllMovieList.getUpcomingMovies());
        setListAdapter(adapter);
    }


    AllMovieList parseAllMovieListDetails(String jsonData) throws JSONException {

        AllMovieList allMovieList = new AllMovieList();
        allMovieList.setUpcomingMovies(getMovieDetails(jsonData));
        return allMovieList;

    }


    private UpcomingMovies[] getMovieDetails(String jsonData) throws JSONException {
        JSONObject tmdb = new JSONObject(jsonData);
        // String title=tmdb.getString("page");
        //  Log.i(TAG,"FROM JSON:" +title);

        //  JSONObject results=tmdb.getJSONObject("results");
        JSONArray results = tmdb.getJSONArray("results");

        UpcomingMovies[] upcomingMovies = new UpcomingMovies[results.length()];

        for (int i = 0; i < results.length(); i++) {
            JSONObject jsonResults = results.getJSONObject(i);

            UpcomingMovies upcomingMovie = new UpcomingMovies();
            upcomingMovie.setTitle(jsonResults.getString("title"));
            upcomingMovie.setOverview(jsonResults.getString("overview"));
            upcomingMovie.setReleaseDate(jsonResults.getString("release_date"));
            upcomingMovie.setPoster(jsonResults.getString("poster_path"));

            upcomingMovies[i] = upcomingMovie;
        }


        return upcomingMovies;
    }

    private boolean isNetWorkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;

    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");


    }
    */





 /*   @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(MainActivity.this, DetailedMovieActivity.class);
        intent.putExtra(DETAILED_INFO, mAllMovieList.mUpcomingMovies[position]);
        startActivity(intent);
    }



     //upcoming movie list
        String movieUrl = "http://api.themoviedb.org/3/movie/upcoming?api_key=148883e571ec509b3c07b0514a0dc4fe";


        if (isNetWorkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(movieUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mAllMovieList = parseAllMovieListDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDiaplay();
                                }
                            });


                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "EXCEPTION CAUGHT:", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "EXCEPTION CAUGHT:", e);

                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "Main Ui code is running!");



*/
}
