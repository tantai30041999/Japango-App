package com.example.japan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;
import java.util.List;

public class Grammar extends Fragment{
    ListView listView;
    ObjectAdapter adapter;
    String url = "https://apijapanese.herokuapp.com/api/grammar";
    List<ObjectGeneral> listGrammar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_grammar, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_handleHandbook);
        getDataAndCreateView(url);
        return view;
    }

    public void showContentGrammar(String url,String name){
        Intent intent = new Intent(getActivity(),ContentGrammar.class);
        intent.putExtra("urlGrammar",url);
        intent.putExtra("nameGrammar",name);
        startActivity(intent);
    }

    private void getDataAndCreateView(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listGrammar = new ArrayList<ObjectGeneral>();
                        for (int i = 0; i < response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listGrammar.add(new ObjectGeneral(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("url")));
                            }
                            catch (JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                        adapter = new ObjectAdapter(getActivity(),R.layout.row,listGrammar);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                showContentGrammar(listGrammar.get(i).getContent(),listGrammar.get(i).getName());
                            }
                        });
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