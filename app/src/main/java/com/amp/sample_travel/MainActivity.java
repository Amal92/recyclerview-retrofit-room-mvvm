package com.amp.sample_travel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amp.sample_travel.Adapters.TravelListAdapter;
import com.amp.sample_travel.Models.LocationData;
import com.amp.sample_travel.ViewModels.TravelDataViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.travel_list)
    RecyclerView travel_list;

    private TravelDataViewModel travelDataViewModel;
    private TravelListAdapter travelListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        travelDataViewModel = ViewModelProviders.of(this).get(TravelDataViewModel.class);

        setUpRecyclerView();
        travelDataViewModel.getApiResponseLiveData().observe(this, new Observer<List<LocationData>>() {
            @Override
            public void onChanged(@Nullable List<LocationData> locationData) {
                if (locationData != null) {
                    if (locationData.isEmpty())
                        travelDataViewModel.fetchFromWeb();
                    else
                        travelListAdapter.setData(locationData);
                }
            }
        });
    }

    private void setUpRecyclerView() {
        travelListAdapter = new TravelListAdapter(this, travelDataViewModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        travel_list.setLayoutManager(linearLayoutManager);
        travel_list.setItemAnimator(new DefaultItemAnimator());
        travel_list.setAdapter(travelListAdapter);
    }

}
