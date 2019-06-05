package com.sdk.wisetracker.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sdk.wisetracker.dox.open.api.DOX;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WebViewActivity extends AppCompatActivity {

    private final String TAG = "WebViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);
        setWebView();
    }

    private void setWebView() {

        WebView webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                try {
                    InputStreamReader inputReader = new InputStreamReader(getAssets().open("sample.js"));
                    BufferedReader reader = new BufferedReader(inputReader);
                    String line;
                    String result = "";
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                    webView.loadUrl("javascript:" + "(" + result + ")" + "()");
                } catch (Exception e) {
                    Log.e(TAG, "on page finished error !!", e);
                }
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebContentsDebuggingEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.loadUrl("file:///android_asset/www/main.html");
        // webView.loadUrl("https://google.com");
        DOX.setWebView(webView);

    }

}