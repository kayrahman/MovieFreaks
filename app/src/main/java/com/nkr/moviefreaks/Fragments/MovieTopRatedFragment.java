package com.nkr.moviefreaks.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nkr.moviefreaks.Adapters.MovieNowPlayingAdapter;
import com.nkr.moviefreaks.Adapters.TopRatedGridAdapter;
import com.nkr.moviefreaks.Adapters.TopRatedMovieAdapter;
import com.nkr.moviefreaks.AlertDialogFragment;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.MovieNowPlaying;
import com.nkr.moviefreaks.model.MovieTopRated;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MovieTopRatedFragment extends Fragment{

    private MovieNowPlaying[] mMovieNowPlayings;
   // private MovieTopRated[] mMovieTopRated;
  //  public static final String TAG = MovieNowPlayingActivity.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_movie_top_rated,container,false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie_top_rated);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        String tvShowUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=148883e571ec509b3c07b0514a0dc4fe";

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(tvShowUrl)
                    .build();

            final Call call = client.newCall(request);


            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    alertUserAboutError();
                }


                @Override
                public void onResponse(Response response) throws IOException {
                    try{
                        String jsonData=response.body().string();

                        if(response.isSuccessful()){
                            mMovieNowPlayings=getMovieNowPlayings(jsonData);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UpdateDisplay();
                                }
                            });


                        }

                        else{
                            alertUserAboutError();

                        }
                    }
                    catch (IOException e){
                       // Log.e(TAG, "Exception caught:", e);
                    }
                    catch (JSONException e){
                      //  Log.e(TAG,"JSON EXCEPTION CAUGHT:",e);
                    }

                }
            });
        }
    }






    private void UpdateDisplay(){

        NowPlayingFragments.OnNowPlayingSelectedInterface listener= (NowPlayingFragments.OnNowPlayingSelectedInterface) getActivity();
        RecyclerView mRecyclerView=(RecyclerView)getActivity().findViewById(R.id.movie_top_rated_list_item);
        TopRatedGridAdapter adapter =new TopRatedGridAdapter(mMovieNowPlayings);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

    }


//    private MovieTopRated[] getMovieTopRated(String jsonData)throws JSONException {
//        JSONObject tmdb = new JSONObject(jsonData);
//        JSONArray results = tmdb.getJSONArray("results");
//
//        MovieTopRated[] movieNowPlayings = new MovieTopRated[results.length()];
//        for(int i=0;i<results.length();i++){
//            JSONObject result=results.getJSONObject(i);
//
//            MovieTopRated upcomingSeries=new MovieTopRated();
//
//            upcomingSeries.setTitle(result.getString("original_title"));
//            upcomingSeries.setOverview(result.getString("overview"));
//            upcomingSeries.setReleaseDate(result.getString("release_date"));
//            upcomingSeries.setPoster(result.getString("backdrop_path"));
//
//            movieNowPlayings[i]=upcomingSeries;
//        }
//
//
//        return movieNowPlayings;
//    }

    private MovieNowPlaying[] getMovieNowPlayings(String jsonData)throws JSONException {
        JSONObject tmdb = new JSONObject(jsonData);
        JSONArray results = tmdb.getJSONArray("results");

        MovieNowPlaying[] movieNowPlayings = new MovieNowPlaying[results.length()];
        for(int i=0;i<results.length();i++){
            JSONObject result=results.getJSONObject(i);

            MovieNowPlaying upcomingSeries=new MovieNowPlaying();

            upcomingSeries.setTitle(result.getString("original_title"));
            upcomingSeries.setOverview(result.getString("overview"));
            upcomingSeries.setReleaseDate(result.getString("release_date"));
            upcomingSeries.setPoster(result.getString("backdrop_path"));

            movieNowPlayings[i]=upcomingSeries;
        }


        return movieNowPlayings;
    }



    private boolean isNetworkAvailable(){
        ConnectivityManager manager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        boolean isAvailable=false;

        if(networkInfo!=null && networkInfo.isConnected()){
            isAvailable=true;
        }
        return isAvailable;
    }

    private void alertUserAboutError(){
        AlertDialogFragment dialogFragment=new AlertDialogFragment();
        //dialogFragment.show(getFragmentManager(), "error_dialog");
    }

}
