package com.example.munia.retrofitbangla.network;

import com.example.munia.retrofitbangla.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by munia on 4/24/2019.
 */

public interface ApiInterface {
    @GET("/json")
    Call<ServerResponse> getMyIp();
}
