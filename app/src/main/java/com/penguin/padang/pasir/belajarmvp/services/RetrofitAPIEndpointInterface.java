package com.penguin.padang.pasir.belajarmvp.services;

import com.penguin.padang.pasir.belajarmvp.model.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jowy on 5/24/16.
 */
public interface RetrofitAPIEndpointInterface {

    @GET("api")
    Call<Result> getResultInfo();

    @GET("api")
    Call<ResponseBody> getResultAsJSON();
}
