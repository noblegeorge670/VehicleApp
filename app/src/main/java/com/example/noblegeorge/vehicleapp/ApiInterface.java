package com.example.noblegeorge.vehicleapp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by noblegeorge on 2018-12-06.
 */

public interface ApiInterface {


    @GET("api/v5/story/275497")
    Call<String> getData();

}
