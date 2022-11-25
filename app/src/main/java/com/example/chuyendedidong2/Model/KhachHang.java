package com.example.chuyendedidong2.Model;

public class KhachHang {
    String  name, sdt, diachi;
    int daMua, daHuy;

    public KhachHang(String name, String sdt, String diachi, int daMua, int daHuy) {
        this.name = name;
        this.sdt = sdt;
        this.diachi = diachi;
        this.daMua = daMua;
        this.daHuy = daHuy;
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

    public int getDaMua() {
        return daMua;
    }

    public void setDaMua(int daMua) {
        this.daMua = daMua;
    }

    public int getDaHuy() {
        return daHuy;
    }

    public void setDaBan(int daHuy) {
        this.daHuy = daHuy;
    }
}
