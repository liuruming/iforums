package com.googlecode.iforums.bean;

import java.io.Serializable;

public class PostText implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3446698634644871707L;
    
    private int id;
    private String text;
    private String title;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
