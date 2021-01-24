package com.example.japan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.japan.adapter.HandBookAdapter;
import com.example.japan.databse.DatabaseManager;
import com.example.japan.model.HandBook;
import com.example.japan.model.VocabularyHandBook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityHandleHandBook extends AppCompatActivity {
    SwipeMenuListView listView;
    HandBookAdapter adapter;
    List<HandBook> listHandBook;
    DatabaseManager databaseManager;
    String jWord,vnWord,imgWord,type, urlAudio;
    private int idVocabularyCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_hand_book);


        listView = findViewById(R.id.listViewHandle);
        listHandBook = new ArrayList<HandBook>();

        databaseManager = new DatabaseManager(getApplicationContext());
        databaseManager.createTable();
        loadData();


        Intent intent = getIntent();
        if(intent != null){
            jWord = intent.getStringExtra("jWord");
            vnWord = intent.getStringExtra("vnWord");
            imgWord = intent.getStringExtra("imgWord");
            type = intent.getStringExtra("type");
            idVocabularyCourse = intent.getIntExtra("idVocabularyCourse",0);
            urlAudio = intent.getStringExtra("urlAudio");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HandBook handBook = listHandBook.get(position);
                    int idHandBook = handBook.getId();
                    String currentDate = getDateCurrent();
                    if(databaseManager.isExitVocabulary(idHandBook,jWord)){
                        Toast.makeText(ActivityHandleHandBook.this,"Từ này đã có trong danh sách",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseManager.insertRowContentHandBook(jWord,vnWord,imgWord,type,currentDate,urlAudio,idHandBook);
                        Toast.makeText(ActivityHandleHandBook.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(ActivityHandleHandBook.this, Lesson.class);
                    intent.putExtra("idVocabularyCourse",idVocabularyCourse);
                    startActivity(intent);
                }
            });

        }
        adapter = new HandBookAdapter(getApplicationContext(), R.layout.row_handbook, listHandBook);
        listView.setAdapter(adapter);


    }

    public void loadData() {
        listHandBook = databaseManager.getDataHandBook();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_handbook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addHandBook) {
            showCreateHandBook();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showCreateHandBook() {
        AlertDialog alertDialog;
        final EditText input = new EditText(getApplicationContext());
        input.setHint("Nhập nhóm cần lưu");
        input.setGravity(Gravity.CENTER_HORIZONTAL);
        input.setSingleLine();
        String tmp ="";
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(5, 5, 5, 5);
        input.setLayoutParams(lp);
        alertDialog = new AlertDialog.Builder(ActivityHandleHandBook.this).setTitle("Tạo nhóm từ:")
                .setPositiveButton("Tạo", null)
                .setView(input)
                .setNegativeButton("Đóng",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                .show();

        Button btn = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập tên nhóm",Toast.LENGTH_SHORT).show();
                }else {
                    String nameHandBook = input.getText().toString();
                    if(databaseManager.isExitHandBook(nameHandBook)){
                        Toast.makeText(ActivityHandleHandBook.this,"HandBook này đã tồn tại",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String currentDate="" ;
                        currentDate = getDateCurrent();
                        databaseManager.insertRowHandBook(nameHandBook,currentDate);
                        loadData();
                        int id = listHandBook.get(listHandBook.size()-1).getId();
                        databaseManager.insertRowContentHandBook(jWord,vnWord,imgWord,type,currentDate,urlAudio,id);
                        Toast.makeText(ActivityHandleHandBook.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityHandleHandBook.this, Lesson.class);
                        intent.putExtra("idVocabularyCourse",idVocabularyCourse);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }


                }

            }
        });
    }

     public String getDateCurrent() {
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         String currentDate="" ;
         Date  date = new Date();
         currentDate = format.format(date).toString();

         return currentDate;
     }


}