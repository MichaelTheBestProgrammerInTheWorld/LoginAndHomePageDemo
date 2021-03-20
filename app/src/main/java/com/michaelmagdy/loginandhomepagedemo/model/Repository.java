package com.michaelmagdy.loginandhomepagedemo.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.michaelmagdy.loginandhomepagedemo.model.webservice.ApiClient;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.ApiResponse;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.Category;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.Item;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.Trending;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.Whats_new;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.michaelmagdy.loginandhomepagedemo.model.Constants.FCM_TOKEN;
import static com.michaelmagdy.loginandhomepagedemo.model.Constants.HEADER_API_KEY;
import static com.michaelmagdy.loginandhomepagedemo.model.Constants.HEADER_LANG;

public class Repository {


    //login screen
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



    //home screen
    private MutableLiveData<List<Category>> categoryLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Whats_new>> whatsNewLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Trending>> trendingLiveData = new MutableLiveData<>();
    private List<Category> categoryList = new ArrayList<>();
    private List<Whats_new> whatsNewList = new ArrayList<>();
    private List<Trending> trendingList = new ArrayList<>();


    public void homeRequest() {

        ApiClient.getApiClient().getApiInterface().home(HEADER_LANG,
                HEADER_API_KEY)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        ApiResponse result = response.body();
                        if (result != null) {
                            int code = result.getCode();

                            if (code == 200) {

                                Item item = result.getItem();
                                categoryList = item.getCategory();
                                whatsNewList = item.getWhats_new();
                                trendingList = item.getTrending();

                                if (categoryList != null) {
                                    categoryLiveData.postValue(categoryList);
                                }
                                if (whatsNewList != null) {
                                    whatsNewLiveData.postValue(whatsNewList);
                                }
                                if (trendingList != null) {
                                    trendingLiveData.postValue(trendingList);
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                        Log.d("network_error", t.getMessage());
                    }
                });
    }

    public MutableLiveData<List<Category>> getCategoryLiveData() {
        return categoryLiveData;
    }

    public MutableLiveData<List<Whats_new>> getWhatsNewLiveData() {
        return whatsNewLiveData;
    }

    public MutableLiveData<List<Trending>> getTrendingLiveData() {
        return trendingLiveData;
    }
}
