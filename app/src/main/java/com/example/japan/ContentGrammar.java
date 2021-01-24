package com.example.japan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.japan.adapter.ObjectAdapter;
import com.example.japan.model.ObjectGeneral;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ContentGrammar extends AppCompatActivity {
    WebView webView;
    ObjectGeneral objectGeneral;
    String url = "";
    String html = "<p>a</p>";
    String nameGrammar="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_grammar);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new WebViewClient());

        ActionBar actionBar = getSupportActionBar();
        Intent intent = getIntent();
        if(intent != null){
            url = intent.getStringExtra("urlGrammar");
            nameGrammar = intent.getStringExtra("nameGrammar");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Document doc = Jsoup.connect(url).timeout(6000).get();
                        Elements ele = doc.select(".entry-content");
                        html = ele.toString();
                        Log.d("aaa",html);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadDataWithBaseURL(null,html, "text/html", "utf-8",null);
                        }
                    });
                }
            }).start();
        }
        actionBar.setTitle(nameGrammar);
    }
}