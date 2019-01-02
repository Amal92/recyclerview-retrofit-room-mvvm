package com.amp.sample_travel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amp.sample_travel.Models.LocationData;
import com.amp.sample_travel.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amal on 02/01/19.
 */

public class TravelListAdapter extends RecyclerView.Adapter<TravelListAdapter.MyViewHolder> {

    private Context mContext;
    private List<LocationData> locationDataArrayList;

    public TravelListAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    public TravelListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TravelListAdapter.MyViewHolder holder, int position) {
        LocationData detail = locationDataArrayList.get(position);
        holder.place_name.setText(detail.getPlace());
        holder.time_tv.setText(detail.getDate());
        Glide.with(mContext).load(detail.getUrl()).into(holder.poster_iv);
    }

    @Override
    public int getItemCount() {
        if (locationDataArrayList == null)
            return 0;
        else
            return locationDataArrayList.size();
    }

    public void setData(List<LocationData> locationDataList) {
        locationDataArrayList = locationDataList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.place_name)
        public TextView place_name;

        @BindView(R.id.time_tv)
        public TextView time_tv;

        @BindView(R.id.favourite_button)
        public ImageView favourite_button;

        @BindView(R.id.poster_iv)
        public ImageView poster_iv;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
