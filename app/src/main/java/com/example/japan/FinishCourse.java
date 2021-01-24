package com.example.japan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

public class FinishCourse extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_course);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FinishCourse.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable, 3000);
    }
}