package com.example.notekeeper.model;

import java.io.Serializable;

public class NoteModel implements Serializable {

    public String name;

    private String title;
    private String description;
    private int img;
    private int randomID;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getRandomID() {
        return randomID;
    }

    public void setRandomID(int randomID) {
        this.randomID = randomID;
    }
}
