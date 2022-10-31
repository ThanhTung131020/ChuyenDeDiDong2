package com.example.chuyendedidong2.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductModel implements Serializable {
    private int soLuong;
    String name, desciption, nameShop;
    int price;
    float numStar;
    String img_url,pic1,pic2,pic3;

    public ProductModel(int soLuong, String name, String desciption, String nameShop, int price, float numStar, String img_url) {
        this.soLuong = soLuong;
        this.name = name;
        this.desciption = desciption;
        this.nameShop = nameShop;
        this.price = price;
        this.numStar = numStar;
        this.img_url = img_url;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public ProductModel(String name, String desciption, int price, int numStar, String img_url) {
        this.name = name;
        this.desciption = desciption;
        this.price = price;
        this.numStar = numStar;
        this.img_url = img_url;
    }
    public ProductModel(String name, int price, int numStar, String img_url) {
        this.name = name;
        this.price = price;
        this.numStar = numStar;
        this.img_url = img_url;
    }

    public ProductModel(String name, String desciption, String nameShop, int price, int numStar, String img_url) {
        this.name = name;
        this.desciption = desciption;
        this.nameShop = nameShop;
        this.price = price;
        this.numStar = numStar;
        this.img_url = img_url;
    }

    public ProductModel() {
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


    public float getNumStar() {
        return numStar;
    }

    public void setNumStar(float numStar) {
        this.numStar = numStar;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public ArrayList<ProductModel> createNewProduct(){
        ArrayList<ProductModel> productModelList;
        productModelList = new ArrayList<>();
        productModelList.add(new ProductModel(1,"sakura","suónguong","shop143",1000,4,"https://th.bing.com/th/id/OIP.IJzazGh2VeCw8let2ORy6gHaFj?pid=ImgDet&rs=1"));
        productModelList.add(new ProductModel(2,"phone","suónguong","shop1433",1000,3,"https://th.bing.com/th/id/OIP.6nmHtLPqEuzaneJUo7dBVgAAAA?w=161&h=180&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        productModelList.add(new ProductModel(3,"phone","suónguong","shop431",1000,1,"https://th.bing.com/th/id/OIP.Wy0qTmH0k7j1pPOSDrRRYwHaFj?w=216&h=180&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        productModelList.add(new ProductModel(4,"phone","suónguong","shop143",1000,5,"https://th.bing.com/th/id/OIP.5aoY8tRad241YYAdP-E1VwHaHa?w=141&h=180&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        productModelList.add(new ProductModel(5,"phone","suónguong","shop31",1000,1,"https://th.bing.com/th/id/OIP.vcoZ9R6dpXXPjJ-0cpayzQHaEK?w=254&h=180&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        productModelList.add(new ProductModel(6,"phone","suónguong","shop143",1000,2,"https://th.bing.com/th/id/OIP.zPFPZwvFQb4UBS1e4n9MQAHaHa?w=161&h=180&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        productModelList.add(new ProductModel(7,"phone","suónguong","shop143",1000,4,"https://th.bing.com/th/id/OIP.WlDR0IvRBXoow-8k6_S0-QHaHa?w=215&h=215&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        productModelList.add(new ProductModel(8,"phone","suónguong","shop143",1000,0,"https://th.bing.com/th/id/OIP.z7A8QOAnzrO79jzarK-7vgHaHa?w=196&h=196&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        return productModelList;
    }
}
