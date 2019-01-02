package com.amp.sample_travel.Networking;

import com.amp.sample_travel.Models.Response.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by amal on 02/01/19.
 */

public interface ApiInterface {

    @GET("5c261ccb3000004f0067f6ec")
    Call<ApiResponse> getAllData();
}
