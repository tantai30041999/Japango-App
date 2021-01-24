package com.example.japan.model;

import java.util.ArrayList;
import java.util.List;

public class HandBook {
    private int id;
    private String name;
    private String dataCreate;
    private List<VocabularyHandBook> listWord;

    public HandBook() {
    }
    public HandBook(int id, String name, String dataCreate) {
        this.id = id;
        this.name = name;
        this.dataCreate = dataCreate;
        this.listWord = new ArrayList<VocabularyHandBook>();
    }

    public HandBook(int id, String name, String dataCreate, List<VocabularyHandBook> listWord) {
        this.id = id;
        this.name = name;
        this.dataCreate = dataCreate;
        this.listWord = listWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(String dataCreate) {
        this.dataCreate = dataCreate;
    }

    public List<VocabularyHandBook> getListWord() {
        return listWord;
    }

    public void setListWord(List<VocabularyHandBook> listWord) {
        this.listWord = listWord;
    }
}
