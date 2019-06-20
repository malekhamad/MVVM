package com.example.livedata;

public class MainActivityModel {
    private String title ;
    private String description ;

    public MainActivityModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return this.title ;
    }
    public String getDescription(){
        return this.description;
    }

}
