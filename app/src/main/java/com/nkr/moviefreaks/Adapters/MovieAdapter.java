package com.nkr.moviefreaks.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nkr.moviefreaks.R;
import com.nkr.moviefreaks.model.UpcomingMovies;
import com.squareup.picasso.Picasso;

/**
 * Created by neel on 17/12/2015.
 */
public class MovieAdapter extends BaseAdapter {

    private final int VIEW_TYPE_TODAY=0;
    private final int VIEW_TYPE_FUTUREDAY=1;

    private Context mContext;
    private UpcomingMovies[] mUpcomingMovies;

    public MovieAdapter(Context context,UpcomingMovies[] upcomingMovies){
        mContext=context;
        mUpcomingMovies=upcomingMovies;
    }


    @Override
    public int getCount() {
        return mUpcomingMovies.length;
    }

    @Override
    public Object getItem(int position) {
        return mUpcomingMovies[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return (position==0)?VIEW_TYPE_TODAY:VIEW_TYPE_FUTUREDAY;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int viewType=getItemViewType(position);
        int layOutId=-1;

      ViewHolder holder;
        if(convertView==null){

            if (viewType==VIEW_TYPE_TODAY){
                layOutId= R.layout.upcoming_movie_today;
            }
            else if(viewType==VIEW_TYPE_FUTUREDAY){
                layOutId=R.layout.upcoming_movie_details;
            }
            convertView= LayoutInflater.from(mContext).inflate(layOutId,parent,false);
            holder=new ViewHolder();

            if(layOutId==R.layout.upcoming_movie_details) {
                holder.posterimage = (ImageView) convertView.findViewById(R.id.imagePoster);
                holder.mTitle = (TextView) convertView.findViewById(R.id.title);
                holder.mOverView = (TextView) convertView.findViewById(R.id.overview);
                holder.mReleaseDate = (TextView) convertView.findViewById(R.id.releaseDate);
            }

            else {

                    holder.posterimage=(ImageView)convertView.findViewById(R.id.imagePoster_today);

                holder.mOverView=(TextView)convertView.findViewById(R.id.overview_today);
                holder.mTitle=(TextView)convertView.findViewById(R.id.title_today);
                holder.mReleaseDate=(TextView)convertView.findViewById(R.id.releaseDate_today);
            }
            convertView.setTag(holder);

        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }


        UpcomingMovies upcomingMovies=mUpcomingMovies[position];
       // holder.posterimage.setImageResource(upcomingMovies.getPoster());
        holder.mTitle.setText(upcomingMovies.getTitle());
        holder.mOverView.setText(upcomingMovies.getOverview());
        holder.mReleaseDate.setText(upcomingMovies.getReleaseDate());
      //  holder.posterimage.setImageResource(Integer.parseInt(upcomingMovies.getPoster()));

        Picasso.with(convertView.getContext())
                .load("http://image.tmdb.org/t/p/w500/"+upcomingMovies.getPoster())
                .placeholder(R.drawable.placeholder2)
                .into(holder.posterimage);

        return convertView;
    }

    private static class ViewHolder{
        ImageView posterimage;
        TextView mTitle;
        TextView mOverView;
        TextView mReleaseDate;
    }
}
