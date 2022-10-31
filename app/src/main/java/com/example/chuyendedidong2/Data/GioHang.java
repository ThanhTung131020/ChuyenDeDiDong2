package com.example.chuyendedidong2.Data;

import java.io.Serializable;

public class GioHang implements Serializable {
    private int soLuong;
    private int gia;
    private int resoureID;
    private String tenCH;
    private String tenSP;

    public GioHang(int soLuong, int gia, int resoureID, String tenCH, String tenSP) {
        this.soLuong = soLuong;
        this.gia = gia;
        this.resoureID = resoureID;
        this.tenCH = tenCH;
        this.tenSP = tenSP;
    }

    public int getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getResoureID() {
        return resoureID;
    }

    public void setResoureID(int resoureID) {
        this.resoureID = resoureID;
    }

    public String getTenCH() {
        return tenCH;
    }

    public void setTenCH(String tenCH) {
        this.tenCH = tenCH;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
