package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
    WebView wv;
    String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        if(intent.hasExtra("web"))
        {
            url = intent.getStringExtra("web");
        }
        if(intent.hasExtra("databank"))
        {
            url = intent.getStringExtra("databank");
        }
        else
        {
            url = "http://thenationaltrust.gov.in/content/innerpage/schemes.php";
        }
        wv=findViewById(R.id.webview);
        wv.setWebViewClient(new MyBrowser());

        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.loadUrl(url);
    }



    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
