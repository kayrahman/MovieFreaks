package com.nkr.moviefreaks.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.TopRatedSeries;
import com.squareup.picasso.Picasso;

/**
 * Created by neel on 23/12/2015.
 */
public class TopRatedSeriesAdapter extends RecyclerView.Adapter<TopRatedSeriesAdapter.TopRatedSeriesViewHolder> {



    private Context mContext;
    private TopRatedSeries[] mTopRatedSeries;


    public TopRatedSeriesAdapter(TopRatedSeries[] topRatedSeries){
        mTopRatedSeries=topRatedSeries;

    }


    @Override
    public TopRatedSeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.toprated_series_list,parent,false);
        TopRatedSeriesViewHolder viewHolder=new TopRatedSeriesViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(TopRatedSeriesViewHolder holder, int position) {

        holder.bindTopRatedseries(mTopRatedSeries[position]);
    }

    @Override
    public int getItemCount() {
        return mTopRatedSeries.length;
    }

    public class TopRatedSeriesViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public TextView mOverView;
        public TextView mReleaseDate;
        public ImageView mPoster;



        public TopRatedSeriesViewHolder(View itemView) {
            super(itemView);
           mTitle=(TextView)itemView.findViewById(R.id.top_rated_series_title);
            mOverView=(TextView)itemView.findViewById(R.id.top_rated_series_overview);
            mReleaseDate=(TextView)itemView.findViewById(R.id.top_rated_series_releaseDate);
            mPoster=(ImageView)itemView.findViewById(R.id.top_rated_series_posterImage);
        }


        public void bindTopRatedseries(TopRatedSeries topRatedSeries){
            mTitle.setText(topRatedSeries.getTitle());
            mOverView.setText(topRatedSeries.getOverview());
            mReleaseDate.setText(topRatedSeries.getReleaseDate());

            Picasso.with(itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w500/"+topRatedSeries.getPoster())
                    .placeholder(R.drawable.placeholder2)
                    .into(mPoster);


        }

    }

}
