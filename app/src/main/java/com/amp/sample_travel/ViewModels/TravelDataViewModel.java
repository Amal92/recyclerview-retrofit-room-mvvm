package com.amp.sample_travel.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.amp.sample_travel.Models.LocationData;
import com.amp.sample_travel.Repository.TravelDataRepository;

import java.util.List;

/**
 * Created by amal on 02/01/19.
 */

public class TravelDataViewModel extends AndroidViewModel {

    private LiveData<List<LocationData>> apiResponseLiveData;
    private TravelDataRepository travelDataRepository;
    private MutableLiveData<Boolean> loadingState;

    public TravelDataViewModel(@NonNull Application application) {
        super(application);
        travelDataRepository = new TravelDataRepository(application);
        apiResponseLiveData = travelDataRepository.getSavedData();
        loadingState = travelDataRepository.getLoadingState();
    }

    public LiveData<List<LocationData>> getApiResponseLiveData() {
        return apiResponseLiveData;
    }

    public void fetchFromWeb() {
        travelDataRepository.getAllData();
    }

    public void updateData(LocationData locationData) {
        travelDataRepository.updateData(locationData);
    }

    public MutableLiveData<Boolean> getLoadingState() {
        return loadingState;
    }
}
