package com.example.japan.adapter;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japan.ContentHandbook;
import com.example.japan.Lesson;
import com.example.japan.R;
import com.example.japan.model.VocabularyHandBook;
import com.example.japan.model.WordModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class ContentHandBookAdapter extends RecyclerView.Adapter<ContentHandBookAdapter.ViewHolder> {

    private ArrayList<VocabularyHandBook> listData;
    ContentHandbook context;
    MediaPlayer mediaPlayer;

    String urlDefault = "https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/audioDefault.mp3?alt=media&token=48fe5a3d-adfb-4f29-b86a-fdb46f9f9a1a";

    public ContentHandBookAdapter(ArrayList<VocabularyHandBook> listData, ContentHandbook context) {
        this.listData = listData;
        this.context = context;

    }

    @Override
    public ContentHandBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_handbook, parent, false);
        ContentHandBookAdapter.ViewHolder viewHolder = new ContentHandBookAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentHandBookAdapter.ViewHolder holder, int position) {
        VocabularyHandBook vocabularyHandBook = listData.get(position);


        String image = vocabularyHandBook.getImgWord();
        if (image.contains("https://firebasestorage.googleapis.com")) {
            Picasso.get().load(image).into(holder.imgViewWord);
        } else {
            int img = context.getResources().getIdentifier("drawable/" + image, null, context.getPackageName());
            holder.imgViewWord.setImageResource(img);
        }
        holder.textPageNumber.setText((position + 1) + "/" + listData.size());
        holder.txtWord.setText(vocabularyHandBook.getjWord());
        holder.txtMeanWord.setText(vocabularyHandBook.getVnWord());

        holder.btn_speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                int indexAudio = position;

                String urlAudio;

                urlAudio = vocabularyHandBook.getUrlAudio().toString();


                try {
                    mediaPlayer.setDataSource(urlAudio);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public TextView txtWord, txtMeanWord, textPageNumber;
        public ImageView imgViewWord;
        public Button btn_speaker;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rl1);
            txtWord = itemView.findViewById(R.id.idWord);
            txtMeanWord = itemView.findViewById(R.id.meanWord);
            textPageNumber = itemView.findViewById(R.id.pageNumberWord);
            imgViewWord = itemView.findViewById(R.id.imgWord);
            btn_speaker = itemView.findViewById(R.id.speaker);
        }
    }
}
