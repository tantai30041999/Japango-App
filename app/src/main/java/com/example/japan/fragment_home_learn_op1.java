package com.example.japan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.japan.model.QuestionCourse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class fragment_home_learn_op1 extends Fragment {
    TextView txtQuestion;
    SpannableString spannableString;
    Button btnCheck;
    ProgressBar progressBar;
    ImageView imgQuestion1,imgQuestion2,imgQuestion3,imgQuestion4;
    CardView answer1, answer2,answer3,answer4;
    ArrayList<Integer> listImg;
    ArrayList<ImageView> listImageView;
    ArrayList<CardView> listCardView;
    int viTri, idQuestion,resultFromUser = -1,old = -1;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_learn_op1, container, false);
        anhXa();
        listCardView = new ArrayList<CardView>();
        listCardView.add(answer1);
        listCardView.add(answer2);
        listCardView.add(answer3);
        listCardView.add(answer4);
        listImageView = new ArrayList<ImageView>();
        listImageView.add(imgQuestion1);
        listImageView.add(imgQuestion2);
        listImageView.add(imgQuestion3);
        listImageView.add(imgQuestion4);
        loadData();

        Random random = new Random();
        viTri = random.nextInt(3);
        Bundle bundle = getArguments();
        if(bundle != null){
            idQuestion = bundle.getInt("idWord");
            String jWord = bundle.getString("jWord");
            spannableString = new SpannableString(jWord);
            spannableString.setSpan(new UnderlineSpan(),0,spannableString.length(),0);
            txtQuestion.setText(spannableString);
            String imgWord = bundle.getString("imgWord");

            if(imgWord.contains("https://firebasestorage.googleapis.com")){
                Picasso.get().load(imgWord).into(listImageView.get(viTri));
            }
            else {
                int img = getContext().getResources().getIdentifier("drawable/"+imgWord, null, getContext().getPackageName());
                listImageView.get(viTri).setImageResource(img);
                for (int i = 0 ; i < listImg.size();i++){
                    if(listImg.get(i) == img){
                        listImg.remove(i);
                        break;
                    }
                }
            }
            for(int i = 0;i<listImageView.size();i++){
                int rdImg = random.nextInt(listImg.size());
                if(i != viTri){
                    listImageView.get(i).setImageResource(listImg.get(rdImg));
                    listImg.remove(rdImg);
                }
            }
            listImg.clear();
            for (int i = 0; i< listCardView.size(); i++){
                int finalI = i;
                listCardView.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(old != -1){
                            listCardView.get(old).setBackgroundColor(Color.WHITE);
                        }
                        old = finalI;
                        listCardView.get(finalI).setBackgroundResource(R.drawable.card_edge);
                        resultFromUser = finalI;
                    }
                });
            }
            btnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean checkResult = checkResult(resultFromUser,viTri);
                    ((Course) getActivity()).showDialog(checkResult);
                    if(checkResult){
                        listCardView.clear();
                        listImageView.clear();
                        ((Course)getActivity()).increaseProgressBar();
                        ((Course) getActivity()).removeRightQuestion(idQuestion);
                    }
                    if(((Course)getActivity()).randomQuestion() != null){
                        QuestionCourse questionCourse = ((Course)getActivity()).randomQuestion();
                        Bundle bundle = ((Course)getActivity()).createBundleToPass(questionCourse.getId(),questionCourse.getjWord(),questionCourse.getVnWord(),questionCourse.getImgWord());
                        Fragment fragment = ((Course)getActivity()).setFragment(questionCourse.getType());
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment fragment2 = fragmentManager.findFragmentById(R.id.frame_course);
                        fragmentTransaction.remove(fragment2);
                        fragmentTransaction.add(R.id.frame_course, fragment);
                        fragmentTransaction.commit();
                    }
                }
            });
        }
        return view;
    }

    public void loadData(){
        listImg = new ArrayList<Integer>();
        listImg.add(R.drawable.conmeo);
        listImg.add(R.drawable.hocsinh);
        listImg.add(R.drawable.bacsi);
        listImg.add(R.drawable.baidoxe);
        listImg.add(R.drawable.canhsat);
        listImg.add(R.drawable.dienthoaididong);
        listImg.add(R.drawable.dung);
        listImg.add(R.drawable.hoahong);
        listImg.add(R.drawable.lanh);
        listImg.add(R.drawable.nong);
        listImg.add(R.drawable.nongthon);
        listImg.add(R.drawable.nuoc);
        listImg.add(R.drawable.taixe);
        listImg.add(R.drawable.tienmat);
        listImg.add(R.drawable.toanha);
        listImg.add(R.drawable.tulanh);
        listImg.add(R.drawable.xedap);
        listImg.add(R.drawable.xehoi);
    }

    public void anhXa(){
        txtQuestion = view.findViewById(R.id.txtQuestion);
        btnCheck = view.findViewById(R.id.btnCheck);
        progressBar = getActivity().findViewById(R.id.progressBar52);
        imgQuestion1 = view.findViewById(R.id.imgQuestion1);
        imgQuestion2 = view.findViewById(R.id.imgQuestion2);
        imgQuestion3 = view.findViewById(R.id.imgQuestion3);
        imgQuestion4 = view.findViewById(R.id.imgQuestion4);
        answer1 = view.findViewById(R.id.cardViewAnswer1);
        answer2 = view.findViewById(R.id.cardViewAnswer2);
        answer3 = view.findViewById(R.id.cardViewAnswer3);
        answer4 = view.findViewById(R.id.cardViewAnswer4);
    }

    public boolean checkResult(int result, int viTri){
        if(result == viTri && result != -1){
            return true;
        }
        return false;
    }

}