package com.tando.mba01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyCoyote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coyote);
        WebView webView = (WebView) findViewById(R.id.webView);
        //enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //avoid jumping to browser
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://weblogon.csusb.edu/cas/login?service=https%3A%2F%2Fmy.csusb.edu%2Fpaf%2Fauthorize");
    }
}
