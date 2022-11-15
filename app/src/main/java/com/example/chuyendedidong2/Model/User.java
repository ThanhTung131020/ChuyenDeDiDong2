package com.example.chuyendedidong2.Model;
class loaiUser{
    private int typeID;
    private String nameType;

    public loaiUser(int typeID, String nameType) {
        this.typeID = typeID;
        this.nameType = nameType;
    }

    @Override
    public String toString() {
        return "loaiUser{" +
                "typeID=" + typeID +
                ", nameType='" + nameType + '\'' +
                '}';
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
    public void loaiTaiKhoan(){
        if(typeID == 0){
            setNameType("khách hàng");
        }
        if(typeID == 1){
            setNameType("cửa hàng");
        }
        if(typeID == 2){
            setNameType("người giao hàng");
        }
    }
}



public class User {
    private int user_ID , id_type;
    private String userName , userPhone , userAddress , Email  ;



    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
