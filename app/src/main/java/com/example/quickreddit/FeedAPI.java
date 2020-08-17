package com.example.quickreddit;


import com.example.quickreddit.model.Feed;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface FeedAPI {

    String BASE_URL = "https://www.reddit.com/r/";

    @GET("{feed_name}/.rss")
    Call<Feed> getFeed(@Path("feed_name") String feed_name);

}
