package com.amp.sample_travel.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.amp.sample_travel.Database.TravelDao;
import com.amp.sample_travel.Database.TravelRoomDatabase;
import com.amp.sample_travel.Models.LocationData;
import com.amp.sample_travel.Models.Response.ApiResponse;
import com.amp.sample_travel.Networking.ApiInterface;
import com.amp.sample_travel.Networking.RetrofitApiClient;
import com.amp.sample_travel.Utils.SharedPreferencesUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amal on 02/01/19.
 */

public class TravelDataRepository {
    private static TravelDataRepository Instance = null;
    private ApiInterface apiInterface;
    private LiveData<List<LocationData>> savedData;
    private TravelDao travelDao;
    private Context mContext;

    public TravelDataRepository(Application application) {
        this.apiInterface = RetrofitApiClient.getInstance().create(ApiInterface.class);
        TravelRoomDatabase db = TravelRoomDatabase.getDatabase(application);
        travelDao = db.dataDao();
        savedData = travelDao.getAllSavedData();
        mContext = application.getApplicationContext();
    }

    public static TravelDataRepository getInstance(Application application) {
        if (Instance == null)
            Instance = new TravelDataRepository(application);
        return Instance;
    }

    public LiveData<List<LocationData>> getSavedData() {
        return savedData;
    }

    public void getAllData() {

        Call<ApiResponse> call = apiInterface.getAllData();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200) {
                    insertData(response.body().getLocations());
                    SharedPreferencesUtils.setParam(mContext, SharedPreferencesUtils.CUSTOMER_NAME, response.body().getCust_name());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }

    public void insertData(List<LocationData> locationDataList) {

        new insertAsyncTask(travelDao).execute(locationDataList);
    }

    public void updateData(LocationData locationData) {

        new updateAsyncTask(travelDao).execute(locationData);
    }


    private static class insertAsyncTask extends AsyncTask<List<LocationData>, Void, Void> {

        private TravelDao mAsyncTaskDao;

        insertAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<LocationData>[] lists) {
            mAsyncTaskDao.insert(lists[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<LocationData, Void, Void> {

        private TravelDao mAsyncTaskDao;

        updateAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(LocationData... locationData) {
            mAsyncTaskDao.update(locationData[0]);
            return null;
        }
    }

}
