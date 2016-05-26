package com.nkr.moviefreaks.Acitivitiees;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.nkr.moviefreaks.Adapters.AiringTodayTvSeriesAdapter;
import com.nkr.moviefreaks.Adapters.TopRatedSeriesAdapter;
import com.nkr.moviefreaks.AlertDialogFragment;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.AiringTodayTvSeries;
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

public class AiringTodayTvSeriesActivity extends AppCompatActivity {


   // public static final String TAG = AiringTodayTvSeriesActivity.class.getSimpleName();

    private AiringTodayTvSeries[]mAiringTodayTvSeries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airing_today_tv_series);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    String tvShowUrl = "https://api.themoviedb.org/3/tv/airing_today?api_key=148883e571ec509b3c07b0514a0dc4fe";


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
                            mAiringTodayTvSeries=getAiringTodayTvSeries(jsonData);
                            runOnUiThread(new Runnable() {
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
                      //  Log.e(TAG, "Exception caught:", e);
                    }
                    catch (JSONException e){
                       // Log.e(TAG,"JSON EXCEPTION CAUGHT:",e);
                    }

                }
            });
        }
    }







    private void UpdateDisplay(){

        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.airing_today_tv_series_content_main);
        AiringTodayTvSeriesAdapter adapter =new AiringTodayTvSeriesAdapter(mAiringTodayTvSeries);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

    }



    private AiringTodayTvSeries[] getAiringTodayTvSeries(String jsonData)throws JSONException {
        JSONObject tmdb = new JSONObject(jsonData);
        JSONArray results = tmdb.getJSONArray("results");

        AiringTodayTvSeries[] upcomingSerieses = new AiringTodayTvSeries[results.length()];
        for(int i=0;i<results.length();i++){
            JSONObject result=results.getJSONObject(i);

            AiringTodayTvSeries upcomingSeries=new AiringTodayTvSeries();

            upcomingSeries.setTitle(result.getString("original_name"));
            upcomingSeries.setOverview(result.getString("overview"));
            upcomingSeries.setReleaseDate(result.getString("first_air_date"));
            upcomingSeries.setPoster(result.getString("poster_path"));

            upcomingSerieses[i]=upcomingSeries;
        }


        return upcomingSerieses;
    }


    private boolean isNetworkAvailable(){
        ConnectivityManager manager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        boolean isAvailable=false;

        if(networkInfo!=null && networkInfo.isConnected()){
            isAvailable=true;
        }
        return isAvailable;
    }

    private void alertUserAboutError(){
        AlertDialogFragment dialogFragment=new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(), "error_dialog");
    }
}
