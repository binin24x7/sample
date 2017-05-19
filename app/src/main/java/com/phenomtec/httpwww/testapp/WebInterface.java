package com.phenomtec.httpwww.testapp;

import com.phenomtec.httpwww.testapp.model.ItemResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Binin Regi on 19/05/17.
 */

public interface WebInterface {
    @POST("/v2/sectors")
    void web_registerUser(@Query("id") String namee, @Query("name") String mobilee, Callback<ItemResponse> registrationCallback);

    @GET("/v2/sectors")
    void getData(Callback<ItemResponse> registrationCallback);


}

