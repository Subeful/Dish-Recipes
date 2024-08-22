package com.example.food.model;

import android.graphics.drawable.Drawable;

public class CategoryModel {
    int id;
    Drawable background;
    String name;

    public CategoryModel(int id, Drawable background, String name) {
        this.id = id;
        this.background = background;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

