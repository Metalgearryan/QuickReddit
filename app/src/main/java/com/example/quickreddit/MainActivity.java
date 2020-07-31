package com.example.quickreddit;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import com.example.quickreddit.model.Feed;
import com.example.quickreddit.model.entry.Entry;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String BASE_URL = "https://www.reddit.com/r/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedAPI feedAPI = retrofit.create(FeedAPI.class);

        Call<Feed> call = feedAPI.getFeed();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());

                List<Entry> entrys = response.body().getEntrys();

                Log.d(TAG, "onResponse: entrys: " + response.body().getEntrys());

                ArrayList<Post> posts = new ArrayList<Post>();
                for (int i = 0; i < entrys.size(); i++) {
                    ExtractXML extractXML1 = new ExtractXML(entrys.get(i).getContent(), "<a href=");
                    List<String> postContent = extractXML1.start();

                    ExtractXML extractXML2 = new ExtractXML(entrys.get(i).getContent(), "<img src=");

                    try {
                        postContent.add(extractXML2.start().get(0));
                    } catch (NullPointerException e) {
                        postContent.add(null);
                        Log.e(TAG, "onResponse: NullPointerException(thumbnail):" + e.getMessage());
                    } catch (IndexOutOfBoundsException e) {
                        postContent.add(null);
                        Log.e(TAG, "onResponse: IndexOutOfBoundsException(thumbnail):" + e.getMessage());
                    }

                    int lastPosition = postContent.size() - 1;
                    posts.add(new Post(
                            entrys.get(i).getTitle(),
                            entrys.get(i).getAuthor().getName(),
                            entrys.get(i).getUpdated(),
                            postContent.get(2),
                            postContent.get(lastPosition)
                    ));
                }

                ListView listView = (ListView) findViewById(R.id.listView);
                    CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, R.layout.card_view_main, posts);
                    listView.setAdapter(customListAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.getMessage());
                Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();

            }
        });
    }
}