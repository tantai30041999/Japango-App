package com.example.japan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.japan.adapter.CourseAdapter;
import com.example.japan.adapter.ObjectAdapter;
import com.example.japan.model.ObjectGeneral;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    RecyclerView mRecyclerView;
    List<ObjectGeneral> list;
    String url = "https://apijapanese.herokuapp.com/api/course";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        getDataAndCreateView(url);
        return view;
    }

    private void getDataAndCreateView(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        list = new ArrayList<ObjectGeneral>();
                        for (int i = 0; i < response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                list.add(new ObjectGeneral(jsonObject.getInt("id"),jsonObject.getString("name"),"",jsonObject.getString("img")));
                            }
                            catch (JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                        CourseAdapter adapter = new CourseAdapter(getContext(),list);
                        mRecyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAAA","loi r");
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
}