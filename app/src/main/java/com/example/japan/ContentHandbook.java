package com.example.japan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.japan.adapter.ContentHandBookAdapter;
import com.example.japan.adapter.LessonAdapter;
import com.example.japan.databse.DatabaseManager;
import com.example.japan.model.AudioStorage;
import com.example.japan.model.VocabularyHandBook;

import java.util.ArrayList;

public class ContentHandbook extends AppCompatActivity {
    DatabaseManager databaseManager;
    ArrayList<VocabularyHandBook> vocabularyHandBooks;
    private RecyclerView contentHandbookRecycler;
    private ContentHandBookAdapter contentHandBookAdapter;
    AudioStorage audioStorage;
    ArrayList<String> listAudioLink;
    private int idVocabularyCourse;
    int idHandBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_handbook);
        contentHandbookRecycler = findViewById(R.id.recycler_contenthandbook);
        contentHandbookRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        contentHandbookRecycler.setLayoutManager(layoutManager);
        databaseManager = new DatabaseManager(this);
        audioStorage = new AudioStorage();
        listAudioLink = new ArrayList<>();
        Intent intent = getIntent();
        if(intent != null){
            idHandBook = intent.getIntExtra("idHandBook",-1);
//            idVocabularyCourse = intent.getIntExtra("idVocabularyCourse",0);
            loadData();
//            listAudioLink.addAll(audioStorage.getListAudioByIdLesson(idVocabularyCourse));
            contentHandBookAdapter = new ContentHandBookAdapter(vocabularyHandBooks,ContentHandbook.this);
            contentHandbookRecycler.setAdapter(contentHandBookAdapter);
        }

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(contentHandbookRecycler);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ContentHandBookAdapter.ViewHolder viewHolder = (ContentHandBookAdapter.ViewHolder) contentHandbookRecycler.findViewHolderForAdapterPosition(0);
                RelativeLayout frameLayout = viewHolder.itemView.findViewById(R.id.rl1);
                frameLayout.animate().setDuration(600).scaleX(1).scaleY(1).setInterpolator(new AccelerateDecelerateInterpolator()).start();
            }
        }, 500);

        contentHandbookRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view = snapHelper.findSnapView(layoutManager);
                int pos = layoutManager.getPosition(view);

                ContentHandBookAdapter.ViewHolder viewHolder = (ContentHandBookAdapter.ViewHolder) contentHandbookRecycler.findViewHolderForAdapterPosition(pos);
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

    public void loadData(){
        vocabularyHandBooks = databaseManager.getListDataVocabularyByIdHandBook(idHandBook);
    }
    public ArrayList<String> getAllAudioLink() {
        return listAudioLink;
    }
}