package com.michaelmagdy.loginandhomepagedemo.model.webservice;

import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.ApiResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("login/")
    Call<ApiResponse> login(@Header("lang") String lang,
                              @Header("apiKey") String apiKey,
                              @Body HashMap<String, Object> body);
}
