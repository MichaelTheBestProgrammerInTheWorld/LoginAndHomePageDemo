package com.michaelmagdy.loginandhomepagedemo.model.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL =
            "https://iconprojectss.com/2021/Tam/webServices/";
    private static ApiClient apiClient;
    private Retrofit retrofit;

    private ApiClient(){

        Gson gson = new GsonBuilder()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized ApiClient getApiClient(){

        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiInterface getApiInterface(){

        return retrofit.create(ApiInterface.class);
    }
}
