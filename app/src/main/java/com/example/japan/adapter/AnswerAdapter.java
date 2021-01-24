package com.example.japan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japan.R;
import com.example.japan.fragment_home_learn_op2;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolder> {
    private fragment_home_learn_op2 context;
    private List<String> list;

    public AnswerAdapter(fragment_home_learn_op2 context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_question, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.returnAnswer(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class AnswerViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    CardView mCardView;
    AnswerViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.txtAnswer2);
        mCardView = itemView.findViewById(R.id.cardViewAnswerChar);
    }
}
