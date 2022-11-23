package com.example.chuyendedidong2.Model;

public class CuaHang {
    String ten, diaChi, sdt;
    int daBan;

    public CuaHang(String ten, String diaChi, String sdt, int daBan) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.daBan = daBan;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getDaBan() {
        return daBan;
    }

    public void setDaBan(int daBan) {
        this.daBan = daBan;
    }
}
