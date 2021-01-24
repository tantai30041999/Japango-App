package com.example.japan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.japan.adapter.HandBookAdapter;
import com.example.japan.databse.DatabaseManager;
import com.example.japan.model.HandBook;


import java.util.ArrayList;
import java.util.List;

public class Handbook extends Fragment {
    SwipeMenuListView listView;
    HandBookAdapter adapter;
    List<HandBook> listHandBook;
    DatabaseManager databaseManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_handbook, container, false);
        listView = (SwipeMenuListView) view.findViewById(R.id.listView);
        listHandBook = new ArrayList<HandBook>();
        databaseManager = new DatabaseManager(getContext());
        databaseManager.createTable();
        loadData();

        adapter = new HandBookAdapter(getActivity(),R.layout.row_handbook,listHandBook);
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(100);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);

                // create "edit" item
                SwipeMenuItem openItem = new SwipeMenuItem(getContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(100);
                // set a icon
                openItem.setIcon(R.drawable.ic_edit);
                // add to menu
                menu.addMenuItem(openItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showContentHandBook(listHandBook.get(i).getId());
            }
        });

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        showDialogDelete(listHandBook.get(position).getId());

                        break;
                    case 1:
//                        databaseManager
                        showDialog(listHandBook.get(position).getId(), listHandBook.get(position).getName());
                        Log.d("AAAA",position+" edit");
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        return view;
    }
    public void loadData(){
        listHandBook = databaseManager.getDataHandBook();
    }
    public void showContentHandBook(int idHandBook){
        Intent intent = new Intent(getActivity(),ContentHandbook.class);
        intent.putExtra("idHandBook",idHandBook);
        startActivity(intent);
    }

    public void showDialog(int id,String name){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Nhập tên:");

        final EditText input = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setText(name);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Cập nhật",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = input.getText().toString().trim();

                        if (newName.length() != 0 && !newName.equals(name) && !databaseManager.isExitHandBook(newName)) {
                            databaseManager.updateRowHandBook(id,newName);
                            listHandBook = databaseManager.getDataHandBook();
                            adapter = new HandBookAdapter(getActivity(),R.layout.row_handbook,listHandBook);
                            listView.setAdapter(adapter);
                            Toast.makeText(getContext(),"Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(),"Cập nhật không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        alertDialog.setNegativeButton("Đóng",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public void showDialogDelete(int id){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
        aBuilder.setTitle("Thông báo!");
        aBuilder.setMessage("Bạn có chắc chắn muốn xóa?");
        aBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseManager.deleteRowHandBook(id);
                listHandBook = databaseManager.getDataHandBook();
                adapter = new HandBookAdapter(getActivity(),R.layout.row_handbook,listHandBook);
                listView.setAdapter(adapter);
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