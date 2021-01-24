package com.example.japan.model;

public class WordModel {
    private int idWord;
    private String jWord;
    private String vnWord;
    private String imgWord;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WordModel(int idWord, String jWord, String vnWord, String imgWord, String type) {
        this.idWord = idWord;
        this.jWord = jWord;
        this.vnWord = vnWord;
        this.imgWord = imgWord;
        this.type = type;
    }
    public WordModel() {

    }

    public int getIdWord() {
        return idWord;
    }

    public void setIdWord(int idWord) {
        this.idWord = idWord;
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
}
