package com.example.food.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DishModel implements Serializable {
    int id;
    String nameDish;
    String cookingTime;
    int backgroundImage;
    int difficultyDish;

    public DishModel(int id, String nameDish, String cookingTime, int backgroundImage, int difficultyDish) {
        this.id = id;
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
    public int getBackgroundImage() {
        return backgroundImage;
    }
    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    public int getDifficultyDish() {
        return difficultyDish;
    }
    public void setDifficultyDish(int difficultyDish) {
        this.difficultyDish = difficultyDish;
    }


}
