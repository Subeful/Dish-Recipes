package com.example.food.model;

public class StatesModel {
    int statesBgImage;
    String statesName;

    public StatesModel(int statesBgImage, String statesName) {
        this.statesBgImage = statesBgImage;
        this.statesName = statesName;
    }

    public int getStatesBgImage() {
        return statesBgImage;
    }
    public void setStatesBgImage(int statesBgImage) {
        this.statesBgImage = statesBgImage;
    }
    public String getStatesName() {
        return statesName;
    }
    public void setStatesName(String statesName) {
        this.statesName = statesName;
    }
}
