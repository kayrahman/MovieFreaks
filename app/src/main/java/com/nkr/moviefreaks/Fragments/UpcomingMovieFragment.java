package com.nkr.moviefreaks.Fragments;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nkr.moviefreaks.Adapters.MovieAdapter;
import com.nkr.moviefreaks.AlertDialogFragment;
import com.nkr.moviefreaks.AllMovieList;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.UpcomingMovies;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by neel on 06/04/2016.
 */
public class UpcomingMovieFragment extends ListFragment {

   // private UpcomingMovies[] mUpcomingMovies;
    AllMovieList mAllMovieList;
    Context mContext;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.upcomingmovie_fragment,container,false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
                       // Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mAllMovieList = parseAllMovieListDetails(jsonData);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDiaplay();
                                }
                            });


                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                       // Log.e(TAG, "EXCEPTION CAUGHT:", e);
                    } catch (JSONException e) {
                      //  Log.e(TAG, "EXCEPTION CAUGHT:", e);

                    }
                }
            });
        } else {
            //Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
       // Log.d(TAG, "Main Ui code is running!");


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

    private void updateDiaplay() {
        //mTitle.setText(mUpcomingMovies.);
        MovieAdapter adapter = new MovieAdapter(getActivity(), mAllMovieList.getUpcomingMovies());

        setListAdapter(adapter);
    }


    AllMovieList parseAllMovieListDetails(String jsonData) throws JSONException {

        AllMovieList allMovieList = new AllMovieList();
        allMovieList.setUpcomingMovies(getMovieDetails(jsonData));
        return allMovieList;

    }

    private boolean isNetWorkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;

    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        //dialog.show(getFragmentManager(), "error_dialog");


    }


}
