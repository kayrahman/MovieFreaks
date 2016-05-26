package com.nkr.moviefreaks.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nkr.moviefreaks.Adapters.TopRatedSeriesAdapter;
import com.nkr.moviefreaks.AlertDialogFragment;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.TopRatedSeries;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TopRatedSeriesFragment extends Fragment {


    private TopRatedSeries[] mTopRatedSerieses;
   // private UpcomingMovies[] mUpcomingMovies;
    public static final String TAG = TopRatedSeriesFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_toprated_series,container,false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_toprated_series);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String tvShowUrl = "https://api.themoviedb.org/3/tv/popular?api_key=148883e571ec509b3c07b0514a0dc4fe";


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
                            mTopRatedSerieses=getUpcomingSerieses(jsonData);
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
                        Log.e(TAG,"Exception caught:",e);
                    }
                    catch (JSONException e){
                        Log.e(TAG,"JSON EXCEPTION CAUGHT:",e);
                    }

                }
            });
        }
    }

    ///////////////////////////////////
   ////////////////////// PRIVATE METHODS

    private void UpdateDisplay(){

        RecyclerView mRecyclerView=(RecyclerView)getActivity().findViewById(R.id.top_rated_series_list_item);
        TopRatedSeriesAdapter adapter =new TopRatedSeriesAdapter(mTopRatedSerieses);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

    }


    private TopRatedSeries[] getUpcomingSerieses(String jsonData)throws JSONException {
        JSONObject tmdb = new JSONObject(jsonData);
        // String title=tmdb.getString("page");
        //  Log.i(TAG,"FROM JSON:" +title);

        //  JSONObject results=tmdb.getJSONObject("results");
        JSONArray results = tmdb.getJSONArray("results");

        TopRatedSeries[] upcomingSerieses = new TopRatedSeries[results.length()];
        for(int i=0;i<results.length();i++){
            JSONObject result=results.getJSONObject(i);

            TopRatedSeries upcomingSeries=new TopRatedSeries();

            upcomingSeries.setTitle(result.getString("original_name"));
            upcomingSeries.setOverview(result.getString("overview"));
            upcomingSeries.setReleaseDate(result.getString("first_air_date"));
            upcomingSeries.setPoster(result.getString("poster_path"));

            upcomingSerieses[i]=upcomingSeries;
        }


        return upcomingSerieses;
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
       // dialogFragment.show(getFragmentManager(),"error_dialog");
    }
}
