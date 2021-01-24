package com.example.japan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.japan.adapter.ObjectAdapter;
import com.example.japan.model.ObjectGeneral;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContentSearch extends AppCompatActivity {
    TextView txtLanguage1,txtLanguage2;
    EditText inputLanguage1,inputLanguage2;
    Button btnChangeLanguage, btnTranslate;
    String language1, language2;
    String url = "https://api.mymemory.translated.net/get?langpair=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_search);
        language1 = "ja";
        language2 = "vi";
        anhXa();
        inputLanguage2.setFocusable(false);

        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(language1.equals("ja")){
                    txtLanguage1.setText("Tiếng Việt");
                    txtLanguage2.setText("Tiếng Nhật");
                    language1 = "vi";
                    language2 = "ja";
                }
                else {
                    txtLanguage1.setText("Tiếng Nhật");
                    txtLanguage2.setText("Tiếng Việt");
                    language1 = "ja";
                    language2 = "vi";
                }
                inputLanguage1.setText("");
                inputLanguage2.setText("");
            }
        });

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = inputLanguage1.getText().toString().trim();
                String param = language1 + "|" + language2 + "&q="+word;
                String url2 = url + param;
                getData(url2);
            }
        });
    }

    public void anhXa(){
        txtLanguage1 = (TextView) findViewById(R.id.txtLanguage1);
        txtLanguage2 = (TextView) findViewById(R.id.txtLanguage2);
        inputLanguage1 = (EditText) findViewById(R.id.inputLanguage1);
        inputLanguage2 = (EditText) findViewById(R.id.inputLanguage2);
        btnChangeLanguage = (Button) findViewById(R.id.btnChangeLanguage);
        btnTranslate = (Button) findViewById(R.id.btnTranslate);
    }

    private void getData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            inputLanguage2.setText(response.getJSONObject("responseData").getString("translatedText"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                inputLanguage2.setText("Không thấy kết quả");
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }
}