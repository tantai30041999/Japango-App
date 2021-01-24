package com.example.japan.model;

public class QuestionCourse {
    private int id;
    private String jWord;
    private String vnWord;
    private String imgWord;
    private int type;

    public QuestionCourse() {
    }

    public QuestionCourse(int id, String jWord, String vnWord, String imgWord, int type) {
        this.id = id;
        this.jWord = jWord;
        this.vnWord = vnWord;
        this.imgWord = imgWord;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getjWord() {
        return jWord;
    }

    public void setjWord(String jWord) {
        this.jWord = jWord;
    }

    public String getVnWord() {
        return vnWord;
    }

    public void setVnWord(String vnWord) {
        this.vnWord = vnWord;
    }

    public String getImgWord() {
        return imgWord;
    }

    public void setImgWord(String imgWord) {
        this.imgWord = imgWord;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
