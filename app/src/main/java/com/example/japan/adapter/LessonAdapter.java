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

import com.example.japan.Lesson;
import com.example.japan.R;
import com.example.japan.model.WordModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    private ArrayList<WordModel> listData;
    Lesson context;
    ArrayList<String> url;
    MediaPlayer mediaPlayer;
    String urlDefault ="https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/audioDefault.mp3?alt=media&token=48fe5a3d-adfb-4f29-b86a-fdb46f9f9a1a";



    public LessonAdapter(ArrayList<WordModel> listData, Lesson context) {
        this.listData = listData;
        this.context = context;

    }

    @NonNull
    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.ViewHolder holder, int position) {
        WordModel oneWord = listData.get(position);
        url = new ArrayList<>();
        url.addAll(context.getAllAudioLink());
        String img = oneWord.getImgWord();
        if(img.contains("https://firebasestorage.googleapis.com")){
            Picasso.get().load(img).into(holder.imgViewWord);
        }
        else {
            int image = context.getResources().getIdentifier("drawable/"+img, null, context.getPackageName());
            holder.imgViewWord.setImageResource(image);
        }
        holder.textPageNumber.setText((position + 1) + "/" + listData.size());
        holder.txtWord.setText(oneWord.getjWord());
        holder.txtMeanWord.setText(oneWord.getVnWord());
        holder.btn_addHandBook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String urlAudio = "";
            if(position >= url.size()) {
                urlAudio = urlDefault;
            }else {
                urlAudio = url.get(position).toString();
            }
            String jWord = listData.get(position).getjWord();
            String vnWord = listData.get(position).getVnWord();
            String type = listData.get(position).getType();
            String imgWord = listData.get(position).getImgWord();

            context.changeFragment(jWord,vnWord,imgWord,type,urlAudio);
        }
        });




        holder.btn_speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                int indexAudio =  position;
                String urlAudio;
                if(indexAudio >= url.size()) {
                    urlAudio = urlDefault;
                }else {
                    urlAudio = url.get(indexAudio).toString();
                }

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
        public Button btn_addHandBook, btn_speaker;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rl1);
            txtWord = itemView.findViewById(R.id.idWord);
            txtMeanWord = itemView.findViewById(R.id.meanWord);
            textPageNumber = itemView.findViewById(R.id.pageNumberWord);
            imgViewWord = itemView.findViewById(R.id.imgWord);
            btn_addHandBook = itemView.findViewById(R.id.addHandBook);
            btn_speaker = itemView.findViewById(R.id.speaker);
        }
    }
}
