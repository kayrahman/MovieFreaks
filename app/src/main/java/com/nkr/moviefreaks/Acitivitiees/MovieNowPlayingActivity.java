//package com.nkr.moviefreaks.Acitivitiees;
//
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//
//import com.nkr.moviefreaks.Adapters.MovieNowPlayingAdapter;
//import com.nkr.moviefreaks.Adapters.TopRatedSeriesAdapter;
//import com.nkr.moviefreaks.AlertDialogFragment;
//import com.nkr.moviefreaks.Fragments.NowPlayingFragments;
//import com.nkr.moviefreaks.R;
//import com.nkr.moviefreaks.model.MovieNowPlaying;
//import com.nkr.moviefreaks.model.TopRatedSeries;
//import com.nkr.moviefreaks.model.UpcomingMovies;
//import com.squareup.okhttp.Call;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//public class MovieNowPlayingActivity extends AppCompatActivity {
//
//    public static final String TAG = MovieNowPlayingActivity.class.getSimpleName();
//
//    private MovieNowPlaying[] mMovieNowPlayings;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie_now_playing);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        NowPlayingFragments fragment = new NowPlayingFragments();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.placeholder, fragment);
//        fragmentTransaction.commit();
//
//
////        String tvShowUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=148883e571ec509b3c07b0514a0dc4fe";
////
////        if (isNetworkAvailable()) {
////            OkHttpClient client = new OkHttpClient();
////            Request request = new Request.Builder()
////                    .url(tvShowUrl)
////                    .build();
////
////            final Call call = client.newCall(request);
////
////
////            call.enqueue(new Callback() {
////                @Override
////                public void onFailure(Request request, IOException e) {
////                    alertUserAboutError();
////                }
////
////
////                @Override
////                public void onResponse(Response response) throws IOException {
////                    try{
////                        String jsonData=response.body().string();
////
////                        if(response.isSuccessful()){
////                            mMovieNowPlayings=getMovieNowPlayings(jsonData);
////                            runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////                                    UpdateDisplay();
////                                }
////                            });
////
////
////                        }
////
////                        else{
////                            alertUserAboutError();
////
////                        }
////                    }
////                    catch (IOException e){
////                        Log.e(TAG, "Exception caught:", e);
////                    }
////                    catch (JSONException e){
////                        Log.e(TAG,"JSON EXCEPTION CAUGHT:",e);
////                    }
////
////                }
////            });
////        }
////    }
////
////
////
////    private void UpdateDisplay(){
////
//////        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.movie_now_playing_list_item);
//////        MovieNowPlayingAdapter adapter =new MovieNowPlayingAdapter(mMovieNowPlayings);
//////        mRecyclerView.setAdapter(adapter);
//////
//////        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
//////        mRecyclerView.setLayoutManager(layoutManager);
//////
//////        mRecyclerView.setHasFixedSize(true);
////
////    }
////
////    private MovieNowPlaying[] getMovieNowPlayings(String jsonData)throws JSONException {
////        JSONObject tmdb = new JSONObject(jsonData);
////        JSONArray results = tmdb.getJSONArray("results");
////
////        MovieNowPlaying[] movieNowPlayings = new MovieNowPlaying[results.length()];
////        for(int i=0;i<results.length();i++){
////            JSONObject result=results.getJSONObject(i);
////
////            MovieNowPlaying upcomingSeries=new MovieNowPlaying();
////
////            upcomingSeries.setTitle(result.getString("original_title"));
////            upcomingSeries.setOverview(result.getString("overview"));
////            upcomingSeries.setReleaseDate(result.getString("release_date"));
////            upcomingSeries.setPoster(result.getString("backdrop_path"));
////
////            movieNowPlayings[i]=upcomingSeries;
////        }
////
////
////        return movieNowPlayings;
////    }
////
////
////    private boolean isNetworkAvailable(){
////        ConnectivityManager manager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
////
////        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
////        boolean isAvailable=false;
////
////        if(networkInfo!=null && networkInfo.isConnected()){
////            isAvailable=true;
////        }
////        return isAvailable;
////    }
////
////    private void alertUserAboutError(){
////        AlertDialogFragment dialogFragment=new AlertDialogFragment();
////        dialogFragment.show(getFragmentManager(),"error_dialog");
////    }
//    }
//
//
//}