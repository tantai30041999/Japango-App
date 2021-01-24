package com.example.japan.model;

import java.io.Serializable;

public class ObjectGeneral implements Serializable {
    private int id;
    private String name;
    private String content;
    private String srcImg;

    public ObjectGeneral(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.srcImg = "";
    }
    // using for vocabulary
    public ObjectGeneral(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ObjectGeneral(int id, String name, String content, String srcImg) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.srcImg = srcImg;
    }

    public String getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(String srcImg) {
        this.srcImg = srcImg;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
