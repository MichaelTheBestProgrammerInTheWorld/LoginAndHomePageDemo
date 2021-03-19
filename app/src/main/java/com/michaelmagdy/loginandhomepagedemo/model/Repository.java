package com.michaelmagdy.loginandhomepagedemo.model;

import com.michaelmagdy.loginandhomepagedemo.model.webservice.ApiClient;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.ApiResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.michaelmagdy.loginandhomepagedemo.model.Constants.FCM_TOKEN;
import static com.michaelmagdy.loginandhomepagedemo.model.Constants.HEADER_API_KEY;
import static com.michaelmagdy.loginandhomepagedemo.model.Constants.HEADER_LANG;

public class Repository {


    private LoginCallbacks loginCallbacks;


    public void loginRequest(String emailStr, String passwordStr, LoginCallbacks loginCallbacks) {

        this.loginCallbacks = loginCallbacks;

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", emailStr);
        map.put("password", passwordStr);
        map.put("fcmToken", FCM_TOKEN);
        ApiClient.getApiClient().getApiInterface().login(HEADER_LANG,
                HEADER_API_KEY, map)
        .enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse result = response.body();
                if (result != null) {
                    int code = result.getCode();

                    if (code == 200) {

                        loginCallbacks.onSuccess();

                    } else {

                        loginCallbacks.onFail();
                    }
                } else {

                    loginCallbacks.onFail();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                loginCallbacks.onFail();
            }
        });
    }


    public interface LoginCallbacks {

        void onSuccess();

        void onFail();
    }
}
