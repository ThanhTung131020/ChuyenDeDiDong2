package com.example.chuyendedidong2.Model;

public class Shop {
    String id, name, sdt, diachi, email;
    int spDaBan, spDangCo;
    String maSoThue;

    public Shop(String id, String name, String sdt, String diachi, String email) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.diachi = diachi;
        this.email = email;
    }

    public Shop() {
    }

    public Shop(String id, String name, String sdt, String diachi, String email, int spDaBan, int spDangCo, String maSoThue) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.diachi = diachi;
        this.email = email;
        this.spDaBan = spDaBan;
        this.spDangCo = spDangCo;
        this.maSoThue = maSoThue;
    }

    public Shop(String name, String sdt, String diachi, int spDaBan) {
        this.name = name;
        this.sdt = sdt;
        this.diachi = diachi;
        this.spDaBan = spDaBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSpDaBan() {
        return spDaBan;
    }

    public void setSpDaBan(int spDaBan) {
        this.spDaBan = spDaBan;
    }

    public int getSpDangCo() {
        return spDangCo;
    }

    public void setSpDangCo(int spDangCo) {
        this.spDangCo = spDangCo;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }
}
