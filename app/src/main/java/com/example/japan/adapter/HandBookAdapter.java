package com.example.japan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.japan.R;
import com.example.japan.model.HandBook;

import java.util.List;

public class HandBookAdapter extends BaseAdapter {
    public Context context;
    private int layout;
    private List<HandBook> list;

    public HandBookAdapter(Context context, int layout, List<HandBook> list) {

        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TableRow tableRow;
        TextView textRow;
        TextView dateRow;
        ImageView img;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HandBookAdapter.ViewHolder holder;
        if(view == null){
            holder = new HandBookAdapter.ViewHolder();
            view = LayoutInflater.from(context).inflate(layout, viewGroup,false);

            holder.textRow = (TextView) view.findViewById(R.id.title);
            holder.dateRow = (TextView) view.findViewById(R.id.dateCreate);
            holder.img = (ImageView) view.findViewById(R.id.iconObject);
            holder.tableRow = (TableRow) view.findViewById(R.id.tableRowId);
            view.setTag(holder);
        }
        else {
            holder = (HandBookAdapter.ViewHolder) view.getTag();
        }
        final HandBook handBook = list.get(i);
//        String image = handBook.get
        holder.textRow.setText(handBook.getName());
        holder.dateRow.setText(handBook.getDataCreate());
//        holder.tableRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                        Log.d("aaa","event");
//                ((Grammar) context).showContentGrammar(objectGeneral.getId());
//            }
//        });
//        grammarCall.call(objectGeneral.getId());


        return view;
    }
}
