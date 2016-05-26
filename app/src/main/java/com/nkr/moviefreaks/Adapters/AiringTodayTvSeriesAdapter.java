package com.nkr.moviefreaks.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.AiringTodayTvSeries;
import com.squareup.picasso.Picasso;

/**
 * Created by neel on 23/12/2015.
 */
public class AiringTodayTvSeriesAdapter extends RecyclerView.Adapter<AiringTodayTvSeriesAdapter.AiringTodayTvSeriesVieHolder> {

    private final int VIEW_TYPE_TODAY=0;
    private final int VIEW_TYPE_FUTUREDAY=1;

    private Context mContext;
    private AiringTodayTvSeries[] mAiringTodayTvSeries;
    public AiringTodayTvSeriesAdapter(AiringTodayTvSeries[]airingTodayTvSeries){
        mAiringTodayTvSeries=airingTodayTvSeries;
    }




    @Override
    public AiringTodayTvSeriesVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airing_today_tv_series_list,parent,false);
        AiringTodayTvSeriesVieHolder viewHolder=new AiringTodayTvSeriesVieHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AiringTodayTvSeriesVieHolder holder, int position) {
        holder.bindAiringTodayTvSeries(mAiringTodayTvSeries[position]);


    }

    @Override
    public int getItemViewType(int position) {
        return (position==0)?VIEW_TYPE_TODAY:VIEW_TYPE_FUTUREDAY;
    }

    public int getViewTypeCount(){
        return 2;
    }


    @Override
    public int getItemCount() {
        return mAiringTodayTvSeries.length;
    }

    public class AiringTodayTvSeriesVieHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public TextView mOverView;
        public TextView mReleaseDate;
        public ImageView mPosterImage;

        public AiringTodayTvSeriesVieHolder(View itemView) {
            super(itemView);

            mTitle=(TextView)itemView.findViewById(R.id.airing_today_tv_series_title);
            mOverView=(TextView)itemView.findViewById(R.id.airing_today_tv_series_overview);
            mReleaseDate=(TextView)itemView.findViewById(R.id.airing_today_tv_series_releaseDate);
            mPosterImage=(ImageView)itemView.findViewById(R.id.airing_today_tv_series_imagePoster);
        }

        public void bindAiringTodayTvSeries(AiringTodayTvSeries airingTodayTvSeries){
            mTitle.setText(airingTodayTvSeries.getTitle());
            mOverView.setText(airingTodayTvSeries.getOverview());
            mReleaseDate.setText(airingTodayTvSeries.getReleaseDate());


            Picasso.with(mContext)
                    .load("http://image.tmdb.org/t/p/w500/"+airingTodayTvSeries.getPoster())
                    .placeholder(R.drawable.placeholder2)
                    .into(mPosterImage);
        }
    }
}
