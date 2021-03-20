package com.michaelmagdy.loginandhomepagedemo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.michaelmagdy.loginandhomepagedemo.model.Repository;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.Category;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.Trending;
import com.michaelmagdy.loginandhomepagedemo.model.webservice.responses.Whats_new;

import java.util.List;

public class MainViewModel extends ViewModel {


    private Repository repository;
    private MutableLiveData<List<Category>> categoryLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Whats_new>> whatsNewLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Trending>> trendingLiveData = new MutableLiveData<>();

    public MainViewModel() {

        repository = new Repository();
        repository.homeRequest();
    }

    public MutableLiveData<List<Category>> getCategoryLiveData() {
        this.categoryLiveData = repository.getCategoryLiveData();
        return categoryLiveData;
    }

    public MutableLiveData<List<Whats_new>> getWhatsNewLiveData() {
        this.whatsNewLiveData = repository.getWhatsNewLiveData();
        return whatsNewLiveData;
    }

    public MutableLiveData<List<Trending>> getTrendingLiveData() {
        this.trendingLiveData = repository.getTrendingLiveData();
        return trendingLiveData;
    }
}
