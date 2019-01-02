package com.amp.sample_travel.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amal on 02/01/19.
 */

public class RetrofitApiClient {

    private static String BASE_URL = "http://www.mocky.io/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }

}
