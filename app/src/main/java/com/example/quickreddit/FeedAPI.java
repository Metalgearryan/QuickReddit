package com.example.quickreddit;


import com.example.quickreddit.model.Feed;
import retrofit2.http.GET;
import retrofit2.Call;

public interface FeedAPI {

    String BASE_URL = "https://www.reddit.com/r/";

    @GET("news.rss")
    Call<Feed> getFeed();

}
