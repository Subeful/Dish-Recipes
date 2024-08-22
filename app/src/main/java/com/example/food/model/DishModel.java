package com.example.food.model;

import android.graphics.drawable.Drawable;

public class DishModel {
    String nameDish;
    String cookingTime;
    Drawable backgroundImage;
    Drawable difficultyDish;

    public DishModel(String nameDish, String cookingTime, Drawable backgroundImage, Drawable difficultyDish) {
        this.nameDish = nameDish;
        this.cookingTime = cookingTime;
        this.backgroundImage = backgroundImage;
        this.difficultyDish = difficultyDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Drawable getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Drawable backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Drawable getDifficultyDish() {
        return difficultyDish;
    }

    public void setDifficultyDish(Drawable difficultyDish) {
        this.difficultyDish = difficultyDish;
    }
}
