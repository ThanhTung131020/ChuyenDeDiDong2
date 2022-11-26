package com.example.chuyendedidong2.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CartModel implements Serializable {
    String cart_id,user_id, product_id, shop_id,shop_name, product_imgurl, product_name;
    int product_price, product_quality;

    public CartModel() {
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public CartModel(String user_id, String product_id, String shop_id, String shop_name, String product_imgurl, String product_name, int product_price, int product_quality) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.product_imgurl = product_imgurl;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quality = product_quality;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getProduct_imgurl() {
        return product_imgurl;
    }

    public void setProduct_imgurl(String product_imgurl) {
        this.product_imgurl = product_imgurl;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_quality() {
        return product_quality;
    }

    public void setProduct_quality(int product_quality) {
        this.product_quality = product_quality;
    }
    public ArrayList<CartModel> createList(){
        ArrayList<CartModel> list = new ArrayList<>();
        list.add(new CartModel("cart01","kh01","sp01","shop01","hinh123","may tinh",10000,1));
        list.add(new CartModel("cart02","kh01","sp01","shop01","hinh123","dien thoai",10000,1));
        return list;
    }
}
