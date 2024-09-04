package com.example.food.model;

import android.graphics.drawable.Drawable;


import com.example.food.Help.CategoryDishLish;

import java.util.List;

public class CategoryModel {
    int id;
    Drawable background;
    String name;
    List<DishModel> dishList;

    public CategoryModel(int id, Drawable background, String name, List<DishModel> dishList) {
        this.id = id;
        this.background = background;
        this.name = name;
        this.dishList = dishList;
    }

    public List<DishModel> getDishList() {
        switch (name){
            case "Первые блюда" : return CategoryDishLish.first;
            case "Вторые блюда" : return CategoryDishLish.second;
            case "Салаты" : return CategoryDishLish.salad;
            case "Закуски" : return CategoryDishLish.snack;
            default: return dishList;
            }
        }

    public void setDishList(List<DishModel> dishList) {
        this.dishList = dishList;
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

