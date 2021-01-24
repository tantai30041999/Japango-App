package com.example.japan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.japan.model.QuestionCourse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course extends AppCompatActivity {
    int idCourse = 0;
    String url = "https://apijapanese.herokuapp.com/api/course/";
    List<QuestionCourse> listCourse;
    ProgressBar progressBar;
    QuestionCourse questionCourse;
    ArrayList<String> allWord;
    String nameCourse ="";
    ActionBar actionBar ;
    TextView titleToolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        progressBar = findViewById(R.id.progressBar52);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(137,226,25)));
        progressBar.setScaleY(2f);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.backgroundOp1)));
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.toolbar_title_course, null);
        titleToolbar = v.findViewById(R.id.title_toolbar_course);
        actionBar.setCustomView(v);


        Intent intent = getIntent();
        if(intent != null){
            idCourse = intent.getIntExtra("idCourse",0);
            nameCourse = intent.getStringExtra("nameCourse");
            titleToolbar.setText(nameCourse);
            url += idCourse;
            if(savedInstanceState == null) {
                getData(url);
            }
        }
        actionBar.setDisplayShowCustomEnabled(true);


    }

    private void getData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listCourse = new ArrayList<QuestionCourse>();
                        for (int i = 0; i < response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listCourse.add(new QuestionCourse(jsonObject.getInt("id"),jsonObject.getString("jWord"),jsonObject.getString("vnWord"),jsonObject.getString("imgWord"),jsonObject.getInt("type")));
                            }
                            catch (JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                        createDataForAllWord();
                        questionCourse = randomQuestion();
                        Fragment fragment = setFragment(questionCourse.getType());
                        Bundle bundle = createBundleToPass(questionCourse.getId(),questionCourse.getjWord(),questionCourse.getVnWord(),questionCourse.getImgWord());
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment fragment2 = fragmentManager.findFragmentById(R.id.frame_course);
                        if(fragment2 != null){
                            fragmentTransaction.remove(fragment2);
                        }
                        fragmentTransaction.add(R.id.frame_course, fragment);
                        fragmentTransaction.commit();
//                        getSupportFragmentManager().beginTransaction().add(R.id.frame_course,fragment).commit();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAAA","loi r ben Course");
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void createDataForAllWord(){
        allWord = new ArrayList<String>();
        for (QuestionCourse question: listCourse){
            String[] arrayWord = question.getjWord().split(" ");
            for(String word : arrayWord){
                allWord.add(word);
            }
        }
    }
    public QuestionCourse removeRightQuestion(int idCourse){
        for (QuestionCourse questionCourse : listCourse){
            if(questionCourse.getId() == idCourse){
                listCourse.remove(questionCourse);
                return questionCourse;
            }
        }
        return null;
    }

    public void increaseProgressBar(){
        int currentProgress = progressBar.getProgress();
        progressBar.setProgress(currentProgress+20);
    }

    public QuestionCourse randomQuestion(){
        if(listCourse.size() != 0){
            Random random = new Random();
            int rdQuestion = random.nextInt(listCourse.size());
            return listCourse.get(rdQuestion);
        }
        else {
            Intent intent = new Intent(Course.this,FinishCourse.class);
            startActivity(intent);
            return null;
        }
    }

    public Fragment setFragment(int type){
        Fragment fragment;
        if(type == 1){
            fragment = new fragment_home_learn_op1();
        }
        else{
            fragment = new fragment_home_learn_op2();
        }
        return fragment;
    }

    public Bundle createBundleToPass(int id, String jWord,String vnWord,String imgWord){
        Bundle bundle = new Bundle();
        bundle.putInt("idWord",id);
        bundle.putString("jWord", jWord);
        bundle.putString("vnWord", vnWord);
        bundle.putString("imgWord",imgWord);
        return bundle;
    }

    public ArrayList<String> getALLStringCourse(){
        return allWord;
    }

    public void showDialog(boolean check) {
        Handler handler = new Handler();
        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder builder;
        if(check){
            builder = new AlertDialog.Builder(this).setView(inflater.inflate(R.layout.dialog_dung, null));
        }
        else {
            builder = new AlertDialog.Builder(this).setView(inflater.inflate(R.layout.dialog_sai, null));
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(400,320);
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
            }
        };
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                handler.removeCallbacks(runnable);
            }
        });
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void onBackPressed() {
        showDialogBackPress();
    }

    public void showDialogBackPress(){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("Thông báo!");
        aBuilder.setMessage("Bạn có muốn thoát khỏi quá trình làm?");
        aBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Course.this, MainActivity.class);
                startActivity(intent);
            }
        });
        aBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        aBuilder.show();
    }
}