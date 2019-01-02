package com.amp.sample_travel.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.amp.sample_travel.Models.Response.ApiResponse;
import com.amp.sample_travel.Networking.ApiInterface;
import com.amp.sample_travel.Networking.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amal on 02/01/19.
 */

public class TravelDataRepository {
    private static TravelDataRepository Instance = null;
    private ApiInterface apiInterface;

    public TravelDataRepository(Application application) {
        this.apiInterface = RetrofitApiClient.getInstance().create(ApiInterface.class);

    }

    public static TravelDataRepository getInstance(Application application) {
        if (Instance == null)
            Instance = new TravelDataRepository(application);
        return Instance;
    }

    public LiveData<ApiResponse> getAllData() {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        Call<ApiResponse> call = apiInterface.getAllData();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
        return data;
    }
}
