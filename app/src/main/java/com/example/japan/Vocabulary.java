package com.example.japan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class Vocabulary extends Fragment {
    private ArrayList<ObjectGeneral> listVocabulary;
    ListView listView;
    private String url ="https://apijapanese.herokuapp.com/api/vocabulary";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        listView = view.findViewById(R.id.list_unit_vocabulary);
        getData(url);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Lesson.class);
                intent.putExtra("idVocabularyCourse",listVocabulary.get(i).getId());
                intent.putExtra("nameLesson",listVocabulary.get(i).getName());
                startActivity(intent);
            }
        });



        return view;
    }


    private void getData(String url){

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listVocabulary = new ArrayList<>();
                            for (int i = 0; i < response.length();i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    listVocabulary.add(new ObjectGeneral(jsonObject.getInt("id"),jsonObject.getString("name")));
                                    System.out.println(listVocabulary.get(i).getName());
                                }
                                catch (JSONException ex){
                                    ex.printStackTrace();
                                }
                            }
                            ObjectAdapter objectAdapter = new ObjectAdapter(getContext(), R.layout.row, listVocabulary);
                            listView.setAdapter(objectAdapter);


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