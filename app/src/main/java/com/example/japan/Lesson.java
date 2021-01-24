package com.example.japan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.japan.adapter.LessonAdapter;
import com.example.japan.model.AudioStorage;
import com.example.japan.model.WordModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Lesson extends AppCompatActivity  {
    private RecyclerView lessonRecycler;
    private LessonAdapter lessonAdapter;
    private String url ="https://apijapanese.herokuapp.com/api/vocabulary";
    public Button btn_addHandBook;
    private int idVocabularyCourse;
    private  String nameLesson="";
    ActionBar actionBar;
    ArrayList<String> listAudioLink;
    AudioStorage audioStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);


        actionBar = getSupportActionBar();
        lessonRecycler = findViewById(R.id.recycler_lesson);
        lessonRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lessonRecycler.setLayoutManager(layoutManager);
        audioStorage = new AudioStorage();
       listAudioLink = new ArrayList<>();


        Intent intent = getIntent();
        if(intent != null) {
            idVocabularyCourse = intent.getIntExtra("idVocabularyCourse",0);
            System.out.println(idVocabularyCourse+"idCourse");
            nameLesson = intent.getStringExtra("nameLesson");
            actionBar.setTitle(nameLesson);
            url += "/"+idVocabularyCourse;
            listAudioLink.addAll(audioStorage.getListAudioByIdLesson(idVocabularyCourse));
            System.out.println(listAudioLink.size()+"SIze");
            if(savedInstanceState == null) {
                getData(url);
            }
        }


        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(lessonRecycler);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LessonAdapter.ViewHolder viewHolder = (LessonAdapter.ViewHolder) lessonRecycler.findViewHolderForAdapterPosition(0);
                RelativeLayout frameLayout = viewHolder.itemView.findViewById(R.id.rl1);
                frameLayout.animate().setDuration(600).scaleX(1).scaleY(1).setInterpolator(new AccelerateDecelerateInterpolator()).start();
            }
        }, 500);

        lessonRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view = snapHelper.findSnapView(layoutManager);
                int pos = layoutManager.getPosition(view);

                LessonAdapter.ViewHolder viewHolder = (LessonAdapter.ViewHolder) lessonRecycler.findViewHolderForAdapterPosition(pos);
                RelativeLayout frameLayout = viewHolder.itemView.findViewById(R.id.rl1);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    frameLayout.animate().setDuration(210).scaleX(1).scaleY(1).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                } else {
                    frameLayout.animate().setDuration(210).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    public ArrayList<String> getAllAudioLink() {
        return listAudioLink;
    }

    public void changeFragment(String jWord, String vnWord, String imgWord, String type,String urlAudio){
        Intent intent = new Intent(Lesson.this,ActivityHandleHandBook.class);
        intent.putExtra("jWord",jWord);
        intent.putExtra("vnWord",vnWord);
        intent.putExtra("imgWord",imgWord);
        intent.putExtra("type",type);
        intent.putExtra("idVocabularyCourse",idVocabularyCourse);
        intent.putExtra("urlAudio",urlAudio);
        startActivity(intent);

    }

    private void getData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       ArrayList<WordModel> tmp = new ArrayList<WordModel>();
                        for (int i = 0; i < response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                WordModel wordModel = new WordModel();
                                wordModel.setIdWord(jsonObject.getInt("id"));
                                wordModel.setjWord(jsonObject.getString("jWord"));
                                wordModel.setVnWord(jsonObject.getString("vnWord"));
                                wordModel.setImgWord(jsonObject.getString("imgWord"));
                                wordModel.setType("");
                                tmp.add(wordModel);

                            }
                            catch (JSONException ex){
                                ex.printStackTrace();
                            }
                        }

                        lessonAdapter = new LessonAdapter(tmp,Lesson.this);
                        lessonRecycler.setAdapter(lessonAdapter);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("changeVocabulary",2);
        startActivity(intent);

    }

}