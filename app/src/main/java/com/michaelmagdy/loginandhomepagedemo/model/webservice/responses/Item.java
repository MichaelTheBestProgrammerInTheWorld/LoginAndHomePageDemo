package com.michaelmagdy.loginandhomepagedemo.model.webservice.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @Expose
    @SerializedName("trending")
    private List<Trending> trending;
    @Expose
    @SerializedName("whats_new")
    private List<Whats_new> whats_new;
    @Expose
    @SerializedName("category")
    private List<Category> category;

    public List<Trending> getTrending() {
        return trending;
    }

    public List<Whats_new> getWhats_new() {
        return whats_new;
    }

    public List<Category> getCategory() {
        return category;
    }

}
