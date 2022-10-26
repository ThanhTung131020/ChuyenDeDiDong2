package com.example.chuyendedidong2.Model;

import java.util.ArrayList;

public class NewProductModel {
    String name, desciption;
    int price,numStar;
    String img_url;

    public NewProductModel(String name, String desciption, int price, int numStar, String img_url) {
        this.name = name;
        this.desciption = desciption;
        this.price = price;
        this.numStar = numStar;
        this.img_url = img_url;
    }
    public NewProductModel(String name, int price, int numStar, String img_url) {
        this.name = name;
        this.price = price;
        this.numStar = numStar;
        this.img_url = img_url;
    }

    public NewProductModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumStar() {
        return numStar;
    }

    public void setNumStar(int numStar) {
        this.numStar = numStar;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public ArrayList<NewProductModel> createNewProduct(){
        ArrayList<NewProductModel> newProductModelList;
        newProductModelList = new ArrayList<>();
        newProductModelList.add(new NewProductModel("latop",1000,5,"https://th.bing.com/th/id/OIP.IJzazGh2VeCw8let2ORy6gHaFj?pid=ImgDet&rs=1"));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        return newProductModelList;
    }
}
