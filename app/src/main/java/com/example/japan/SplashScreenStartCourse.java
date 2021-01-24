package com.example.japan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class SplashScreenStartCourse extends AppCompatActivity {
    Handler handler;
    TextView maxim_Content;
    ImageView iconView;
    int idCourse = 0;
    String nameCourse = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_start_course);

        maxim_Content = findViewById(R.id.maxim);
        iconView = findViewById(R.id.iconView);
        runAnimation(iconView);
        maxim_Content.setText(randomMaxim());
        handler = new Handler();
        Intent intent = getIntent();
        if(intent != null){
            idCourse = intent.getIntExtra("idCourse",0);
            nameCourse = intent.getStringExtra("nameCourse");
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenStartCourse.this, Course.class);
                intent.putExtra("idCourse",idCourse);
                intent.putExtra("nameCourse",nameCourse);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable, 2500);

    }

    public String randomMaxim() {
        String maxim_Tmp = "";
        ArrayList<String> listData = new ArrayList<>();
        Random random;

        String tmp1 = getResources().getString(R.string.maxim1);
        String tmp2 =getResources().getString(R.string.maxim2);
        String tmp3 = getResources().getString(R.string.maxim3);
        String tmp4 = getResources().getString(R.string.maxim4);
        String tmp5 = getResources().getString(R.string.maxim5);
        listData.add(tmp1);
        listData.add(tmp2);
        listData.add(tmp3);
        listData.add(tmp4);
        listData.add(tmp5);

        random = new Random();
        int i = random.nextInt(4);
        maxim_Tmp = listData.get(i);
        return maxim_Tmp;
    }

    public void runAnimation(ImageView iconView) {
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        iconView.setAnimation(rotateAnimation);

    }
}