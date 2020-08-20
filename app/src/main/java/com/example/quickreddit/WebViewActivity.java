package com.example.quickreddit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "WebViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.webview_activity);
        WebView webView = (WebView) findViewById(R.id.webview);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.webviewLoadingProgressBar);
        final TextView loadingText = (TextView) findViewById(R.id.progressText);

        progressBar.setVisibility(View.VISIBLE);

        Intent internt = getIntent();
        String url = internt.getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                progressBar.setVisibility(View.GONE);
                loadingText.setText("");
            }
        });
    }
}
