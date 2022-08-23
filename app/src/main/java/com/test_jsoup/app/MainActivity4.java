package com.test_jsoup.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity4 extends AppCompatActivity {
    private WebView mWebView;
    @SuppressLint("SetJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mWebView = findViewById(R.id.mWebView);
        mWebView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=https://drive.google.com/uc?id=1qmdMioOJC1mNC-Bv7t8X9ByrHyU_NTLf");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() );

        mWebView.setWebChromeClient(new WebChromeClient() );
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        mWebView.setLongClickable(false);
    }
}