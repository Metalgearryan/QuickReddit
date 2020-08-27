package com.example.quickreddit;


import android.net.Uri;

import com.example.quickreddit.Account.CheckLogin;
import com.example.quickreddit.Comments.CheckComment;
import com.example.quickreddit.model.Feed;


import java.net.URLEncoder;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FeedAPI {

    String BASE_URL = "https://www.reddit.com/r/";


    @GET("{feed_name}/.rss")
    Call<Feed> getFeed(@Path("feed_name") String feed_name);

    @POST("{user}")
    Call<CheckLogin> signIn(
            @HeaderMap Map<String, String> headers,
            @Path("user") String username,
            @Query("user") String user,
            @Query("passwd") String password,
            @Query("api_type") String type
    );

    @POST("{comment}")
    Call<CheckComment> submitComment(
            @HeaderMap Map<String, String> headers,
            @Path("comment") String comment,
            @Query("parent") String parent,
            @Query("amp;text") String text
    );
}
