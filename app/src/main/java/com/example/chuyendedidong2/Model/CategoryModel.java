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
    public ArrayList<CategoryModel> createCatList(){
        ArrayList<CategoryModel> list = new ArrayList<>();
        list.add(new CategoryModel("cat001","https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/Ellipse%2015%20(1).png?alt=media&token=f9fd9685-3073-46cd-9abe-e525a167d021","Máy tính"));
        list.add(new CategoryModel("cat002","https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/Ellipse%2014.png?alt=media&token=62bd7504-42a9-4a37-ab75-81b2745d7680","Laptop"));
        list.add(new CategoryModel("cat003","https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/Ellipse%2013%20(1).png?alt=media&token=dcbc49ac-9027-4c1d-bcf4-adbee15c45aa","Điện thoại"));
        return list;
    }
}
