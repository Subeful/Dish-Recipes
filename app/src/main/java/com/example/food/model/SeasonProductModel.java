package com.example.food.model;

public class SeasonProductModel {
    int id;
    int bgImage;
    String nameProduct;

    public SeasonProductModel(int id, int bgImage, String nameProduct) {
        this.id = id;
        this.bgImage = bgImage;
        this.nameProduct = nameProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBgImage() {
        return bgImage;
    }

    public void setBgImage(int bgImage) {
        this.bgImage = bgImage;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
