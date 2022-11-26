package com.example.chuyendedidong2.Model;

import androidx.annotation.NonNull;

import com.example.chuyendedidong2.Adapter.ProductsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;


public class ProductModel implements Serializable {
    String productID;
    String img_url;
    float numStar;
    String name;
    int price, soLuong;
    String desciption;
    String nameShop;
    String type;
    String pic1,pic2,pic3;
    private boolean isAddToCart;

    public ProductModel(String productID, String img_url, float numStar, String name, int price, int soLuong, String desciption, String nameShop, String type, String pic1, String pic2, String pic3) {
        this.productID = productID;
        this.img_url = img_url;
        this.numStar = numStar;
        this.name = name;
        this.price = price;
        this.soLuong = soLuong;
        this.desciption = desciption;
        this.nameShop = nameShop;
        this.type = type;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
    }

    public ProductModel(int soLuong, String name, String desciption, String nameShop, int price, float numStar, String img_url) {
        this.soLuong = soLuong;
        this.name = name;
        this.desciption = desciption;
        this.nameShop = nameShop;
        this.price = price;
        this.numStar = numStar;
        this.img_url = img_url;
    }
    public ProductModel() {
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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


//    public ArrayList<ProductModel> createNewProduct() {
//
//        ArrayList<ProductModel> productModelList;
//        productModelList = new ArrayList<>();
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = firebaseDatabase.getReference();
//        databaseReference.child("product").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot snap : snapshot.getChildren()){
//                    ProductModel productModel = snap.getValue(ProductModel.class);
//                    productModelList.add(productModel);
//                }
//                productsAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return productModelList;



//        productModelList.add(new ProductModel(1,"ádas","nameshop","shop123",1000000,5,"https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0"));
//        productModelList.add(new ProductModel(1,"tádad","suónguong","shop143",1000,4,"https://media.ex-cdn.com/EXP/media.nhadautu.vn/files/nguyenhong/2019/02/19/may-tinh-apple-0809.jpg"));
//        productModelList.add(new ProductModel(2,"cádad","suónguong","shop1433",1000,3,"https://external-preview.redd.it/kQD84pPdT41PdNDSYLLdi6-QLkj5ZAkKPLwr-pEY8Zk.jpg?width=640&crop=smart&auto=webp&s=94957c861b4f9e0caccb8e3decb997a662279b07"));
//        productModelList.add(new ProductModel(3,"báda","suónguong","shop431",1000,1,"https://th.bing.com/th/id/R.baa1564fc082dc6856de75c72afdab0e?rik=BSFgvEtb3Xx%2bTg&pid=ImgRaw&r=0"));
//        productModelList.add(new ProductModel(4,"máda","suónguong","shop143",1000,5,"https://th.bing.com/th?q=New%20LG%20Cell%20Phone&pid=ImgDet&w=150&h=150&c=1&p=1&rs=1&t=1&dpr=1.7&mkt=en-ww&adlt=demote"));
//        productModelList.add(new ProductModel(5,"nádad","suónguong","shop31",1000,1,"https://th.bing.com/th?q=T-Mobile%20LG%20Phones&pid=ImgDet&w=150&h=150&c=1&p=1&rs=1&t=1&dpr=1.7&mkt=en-ww&adlt=demote"));
//        productModelList.add(new ProductModel(6,"fasda","suónguong","shop143",1000,2,"https://th.bing.com/th/id/R.0e7a3fffa6f41b8b2cb95c25b0fa791a?rik=oCirMXvWuh5UDA&riu=http%3a%2f%2f3.bp.blogspot.com%2f-OipYuuNx7bc%2fUYznOsel-mI%2fAAAAAAAAA48%2fvoQrFOp9vNI%2fs1600%2flaptop.jpg&ehk=n8GXTV9JaytbWC9ckuoOP0DmWQu5pp7nylPuH9xdb3k%3d&risl=&pid=ImgRaw&r=0"));
//        productModelList.add(new ProductModel(7,"bád","suónguong","shop143",1000,4,"https://th.bing.com/th/id/OIP.eCK38VgOPGsYdiH2_D5-HgHaFk?pid=ImgDet&rs=1"));
//        productModelList.add(new ProductModel(8,"óaada","suónguong","shop143",1000,0,"https://th.bing.com/th/id/OIP.z7A8QOAnzrO79jzarK-7vgHaHa?w=196&h=196&c=7&r=0&o=5&dpr=1.7&pid=1.7"));




    public ArrayList<ProductModel> createProductCart() {
        ArrayList<ProductModel> productModelList;
        productModelList = new ArrayList<>();
        productModelList.add(new ProductModel(1,"name","nameshop","shop123",1000000,5,"123456789"));
        return productModelList;
    }


}
