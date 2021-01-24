 package com.example.japan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String titleActionbar = "";
    ActionBar actionBar;
    TextView titleToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.toolbar_title_main, null);
        titleToolbar = v.findViewById(R.id.title_toolbar);
        actionBar.setCustomView(v);
        actionBar.setDisplayShowCustomEnabled(true);

        ColorStateList listColor = new ColorStateList(
                new int[][] {
                        new int[] {-android.R.attr.state_checked}, // unchecked
                        new int[] { android.R.attr.state_checked}  // checked
                },
                new int[] {
                        Color.WHITE,
                        Color.GREEN
                }
        );

        bottomNavigationView = findViewById(R.id.bottomNavigationNavigation);
        bottomNavigationView.setItemIconSize(80);
        bottomNavigationView.setItemIconTintList(listColor);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            titleActionbar ="Home";
            titleToolbar.setText(titleActionbar);
            Fragment fragment_Default = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment_Default).commit();
        }
        Intent intent = getIntent();
        if (intent != null) {
            int change = intent.getIntExtra("changeVocabulary", -1);
            if (change == 2) {
                Fragment fragment_Default = new Vocabulary();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment_Default).commit();
                bottomNavigationView.setSelectedItemId(R.id.vocabulary);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, ContentSearch.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = new Home();
            int id = menuItem.getItemId();



            switch (id) {
                case R.id.homePage:
                    fragment = new Home();
                    titleActionbar ="Home";
                    titleToolbar.setText(titleActionbar);



                    break;
                case R.id.handbook:
                    fragment = new Handbook();
                    titleActionbar ="Handbook";
                    titleToolbar.setText(titleActionbar);

                    break;
                case R.id.grammar:
                    fragment = new Grammar();
                    titleActionbar ="Grammar";
                    titleToolbar.setText(titleActionbar);

                    break;
                case R.id.vocabulary:
                    fragment = new Vocabulary();
                    titleActionbar ="Vocabulary";
                    titleToolbar.setText(titleActionbar);

                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }

    };

}
