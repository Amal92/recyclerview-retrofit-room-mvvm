package com.amp.sample_travel.UI.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amp.sample_travel.Models.LocationData;
import com.amp.sample_travel.R;
import com.amp.sample_travel.UI.Activities.DetailviewActivity;
import com.amp.sample_travel.Utils.SharedPreferencesUtils;
import com.amp.sample_travel.ViewModels.TravelDataViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amal on 02/01/19.
 */

public class TravelListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<LocationData> locationDataArrayList;
    private TravelDataViewModel travelDataViewModel;

    public TravelListAdapter(Activity mContext, TravelDataViewModel travelDataViewModel) {
        this.mContext = mContext;
        this.travelDataViewModel = travelDataViewModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == 2) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.travel_row, parent, false);

            viewHolder = new MyViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_header, parent, false);

            viewHolder = new MyHeaderViewHolder(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == 2) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            LocationData detail = locationDataArrayList.get(position);
            holder.place_name.setText(detail.getPlace());
            holder.time_tv.setText(detail.getDate());
            if (detail.isFavourite())
                holder.favourite_button.setImageResource(R.drawable.heart);
            else holder.favourite_button.setImageResource(R.drawable.heart_outline);
            Glide.with(mContext).load(detail.getUrl()).into(holder.poster_iv);
        } else {
            MyHeaderViewHolder holder = (MyHeaderViewHolder) viewHolder;
            String customerName = (String) SharedPreferencesUtils.getParam(mContext, SharedPreferencesUtils.CUSTOMER_NAME, "");
            holder.header_tv.setText(String.format("Hi %s,", customerName));
        }
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
        locationDataArrayList.add(0, null);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (locationDataArrayList.get(position) == null)
            return 1;
        else return 2;
    }

    public class MyHeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_tv)
        TextView header_tv;

        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.place_name)
        TextView place_name;

        @BindView(R.id.time_tv)
        TextView time_tv;

        @BindView(R.id.favourite_button)
        ImageView favourite_button;

        @BindView(R.id.poster_iv)
        ImageView poster_iv;

        @BindView(R.id.cardView)
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            favourite_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (favourite_button.getDrawable().getConstantState() == mContext.getResources().getDrawable(R.drawable.heart).getConstantState()) {
                        // remove from favourites
                        favourite_button.setImageResource(R.drawable.heart_outline);
                        LocationData locationData = locationDataArrayList.get(getAdapterPosition());
                        locationData.setFavourite(false);
                        travelDataViewModel.updateData(locationData);
                    } else {
                        //add to favourites
                        favourite_button.setImageResource(R.drawable.heart);
                        LocationData locationData = locationDataArrayList.get(getAdapterPosition());
                        locationData.setFavourite(true);
                        travelDataViewModel.updateData(locationData);
                    }
                }
            });
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailviewActivity.class);
                    intent.putExtra("data", locationDataArrayList.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
