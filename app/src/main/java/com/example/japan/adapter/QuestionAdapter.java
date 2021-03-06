package com.example.japan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japan.R;
import com.example.japan.fragment_home_learn_op2;
import com.example.japan.model.ObjectGeneral;

import java.util.List;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {
    private fragment_home_learn_op2 context;
    private List<String> list;

    public QuestionAdapter(fragment_home_learn_op2 context, List<String> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
//        if(list.get(position) == ""){
//            holder.mCardView.setVisibility(View.GONE);
//        }
        holder.textView.setText(list.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mCardView.setVisibility(View.GONE);

//                String t = list.get(position);
//                t = "";
                context.sendResult(list.get(position), holder.mCardView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class QuestionViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    CardView mCardView;
    QuestionViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.txtAnswer2);
        mCardView = itemView.findViewById(R.id.cardViewAnswerChar);
    }
}
