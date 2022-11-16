package com.example.chuyendedidong2.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductModel implements Serializable {
    String product_id,img_url;
    float numStar;
    String name;
    int price;
    int soLuong;
    String desciption;
    String idShop;
    String nameShop;
    String type_id;
    String pic1,pic2,pic3;


    public ProductModel(String product_id, String img_url, float numStar, String name, int price, String desciption, String idShop, String nameShop, String pic1, String pic2, String pic3) {
        this.product_id = product_id;
        this.img_url = img_url;
        this.numStar = numStar;
        this.name = name;
        this.price = price;
        this.desciption = desciption;
        this.idShop = idShop;
        this.nameShop = nameShop;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
    }

    public ProductModel() {
    }


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }


    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public ArrayList<ProductModel> createNewProduct(){
        ArrayList<ProductModel> productModelList;
        productModelList = new ArrayList<>();
        productModelList.add(new ProductModel("sp01","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",500000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        productModelList.add(new ProductModel("sp02","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        productModelList.add(new ProductModel("sp03","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        productModelList.add(new ProductModel("sp04","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        productModelList.add(new ProductModel("sp05","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        return productModelList;
    }
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("price", price);
        hashMap.put("desciption",desciption);
        return hashMap;
    }

}
