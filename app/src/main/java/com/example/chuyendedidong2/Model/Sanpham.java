package com.example.chuyendedidong2.Model;

public class Sanpham {
    String imvsanPham;
    int gia, soLuong;

    public Sanpham() {
    }

    public Sanpham(String imvsanPham, int gia, int soLuong) {
        this.imvsanPham = imvsanPham;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getImvsanPham() {
        return imvsanPham;
    }

    public void setImvsanPham(String imvsanPham) {
        this.imvsanPham = imvsanPham;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
