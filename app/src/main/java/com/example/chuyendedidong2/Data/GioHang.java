package com.example.chuyendedidong2.Data;

public class GioHang {
    private int gia;
    private int resoureID;
    private String tenCH;

    public GioHang(int gia, int resoureID, String tenCH) {
        this.gia = gia;
        this.resoureID = resoureID;
        this.tenCH = tenCH;
    }

    public int getGia() {
        return gia;
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
}
