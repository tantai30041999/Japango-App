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
import com.example.japan.model.ObjectGeneral;

import java.util.List;

public class ObjectAdapter extends BaseAdapter {
    public Context context;
    private int layout;
    private List<ObjectGeneral> list;

    public ObjectAdapter(Context context, int layout, List<ObjectGeneral> list) {

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
        ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
            if(view == null){
                holder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(layout, viewGroup,false);
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                view = inflater.inflate(layout,null);

                holder.textRow = (TextView) view.findViewById(R.id.title);
                holder.img = (ImageView) view.findViewById(R.id.iconObject);
                holder.tableRow = (TableRow) view.findViewById(R.id.tableRowId);
                view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        final ObjectGeneral objectGeneral = list.get(i);
        holder.textRow.setText(objectGeneral.getName());
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
