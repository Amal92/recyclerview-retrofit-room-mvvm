package com.amp.sample_travel.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.amp.sample_travel.Models.Response.ApiResponse;
import com.amp.sample_travel.Repository.TravelDataRepository;

/**
 * Created by amal on 02/01/19.
 */

public class TravelDataViewModel extends AndroidViewModel {

    private LiveData<ApiResponse> apiResponseLiveData;
    private TravelDataRepository travelDataRepository;

    public TravelDataViewModel(@NonNull Application application) {
        super(application);
        travelDataRepository = new TravelDataRepository(application);
        apiResponseLiveData = travelDataRepository.getAllData();
    }

    public LiveData<ApiResponse> getApiResponseLiveData() {
        return apiResponseLiveData;
    }
}
