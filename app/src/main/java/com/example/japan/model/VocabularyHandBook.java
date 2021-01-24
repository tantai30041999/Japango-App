package com.example.japan.model;

public class VocabularyHandBook {
    private int id;
    private String jWord;
    private String vnWord;
    private String type;
    private String imgWord;
    private String dateCreate;
    private String urlAudio;
    private int idHandBook;

    public VocabularyHandBook() {
    }

    public String getImgWord() {
        return imgWord;
    }

    public void setImgWord(String imgWord) {
        this.imgWord = imgWord;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    public VocabularyHandBook(int id, String jWord, String vnWord, String type, String imgWord, String dateCreate, String urlAudio, int idHandBook) {
        this.id = id;
        this.jWord = jWord;
        this.vnWord = vnWord;
        this.type = type;
        this.imgWord = imgWord;
        this.dateCreate = dateCreate;
        this.urlAudio = urlAudio;
        this.idHandBook = idHandBook;
    }

    public int getIdHandBook() {
        return idHandBook;
    }

    public void setIdHandBook(int idHandBook) {
        this.idHandBook = idHandBook;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "VocabularyHandBook{" +
                "id=" + id +
                ", jWord='" + jWord + '\'' +
                ", vnWord='" + vnWord + '\'' +
                ", type='" + type + '\'' +
                ", imgWord='" + imgWord + '\'' +
                ", dateCreate='" + dateCreate + '\'' +
                ", idHandBook=" + idHandBook +
                '}';
    }
}
