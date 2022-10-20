package com.example.appbandodientuonline.model;

public class Category {
    private int imgCat;
    private String nameCat;

    public Category(int imgCat, String nameCat) {
        this.imgCat = imgCat;
        this.nameCat = nameCat;
    }

    public Category() {
    }

    public int getImgCat() {
        return imgCat;
    }

    public void setImgCat(int imgCat) {
        this.imgCat = imgCat;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }
}
