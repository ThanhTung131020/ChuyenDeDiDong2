package com.example.chuyendedidong2.Model;

import java.util.ArrayList;

public class CategoryModel {
    private String catID, catImg, catName;

    public CategoryModel() {
    }

    public CategoryModel(String catID, String catImg, String catName) {
        this.catID = catID;
        this.catImg = catImg;
        this.catName = catName;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatImg() {
        return catImg;
    }

    public void setCatImg(String catImg) {
        this.catImg = catImg;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
