package com.example.chuyendedidong2.Model;

import java.util.ArrayList;

public class DonHang {
    private String idDonHang;
    private int TrangThaiDH = 0;
    private String hinhSP,idSanPham,tenSP;
    private int giaSP, soLuongSP;
    private String idKhachhang,tenKhachHang,diaChiKhachHang,sdtKhachHang;
    private String idNguoiGiaoHang,tenNguoiGiaoHang;
    private String idCuaHang,tenCuaHang, diaChiCuaHang,sdtCuaHang;

    public DonHang() {
    }

    public DonHang(String idDonHang, int trangThaiDH, String hinhSP, String idSanPham, String tenSP, int giaSP, int soLuongSP, String idKhachhang, String tenKhachHang, String diaChiKhachHang, String sdtKhachHang, String idNguoiGiaoHang, String tenNguoiGiaoHang, String idCuaHang, String tenCuaHang, String diaChiCuaHang, String sdtCuaHang) {
        this.idDonHang = idDonHang;
        TrangThaiDH = trangThaiDH;
        this.hinhSP = hinhSP;
        this.idSanPham = idSanPham;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.idKhachhang = idKhachhang;
        this.tenKhachHang = tenKhachHang;
        this.diaChiKhachHang = diaChiKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.idNguoiGiaoHang = idNguoiGiaoHang;
        this.tenNguoiGiaoHang = tenNguoiGiaoHang;
        this.idCuaHang = idCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.diaChiCuaHang = diaChiCuaHang;
        this.sdtCuaHang = sdtCuaHang;
    }

    public String getSdtCuaHang() {
        return sdtCuaHang;
    }

    public void setSdtCuaHang(String sdtCuaHang) {
        this.sdtCuaHang = sdtCuaHang;
    }

    public String getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(String idDonHang) {
        this.idDonHang = idDonHang;
    }

    public int getTrangThaiDH() {
        return TrangThaiDH;
    }

    public void setTrangThaiDH(int trangThaiDH) {
        TrangThaiDH = trangThaiDH;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public String getIdKhachhang() {
        return idKhachhang;
    }

    public void setIdKhachhang(String idKhachhang) {
        this.idKhachhang = idKhachhang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChiKhachHang() {
        return diaChiKhachHang;
    }

    public void setDiaChiKhachHang(String diaChiKhachHang) {
        this.diaChiKhachHang = diaChiKhachHang;
    }

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }

    public String getIdNguoiGiaoHang() {
        return idNguoiGiaoHang;
    }

    public void setIdNguoiGiaoHang(String idNguoiGiaoHang) {
        this.idNguoiGiaoHang = idNguoiGiaoHang;
    }

    public String getTenNguoiGiaoHang() {
        return tenNguoiGiaoHang;
    }

    public void setTenNguoiGiaoHang(String tenNguoiGiaoHang) {
        this.tenNguoiGiaoHang = tenNguoiGiaoHang;
    }

    public String getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(String idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getDiaChiCuaHang() {
        return diaChiCuaHang;
    }

    public void setDiaChiCuaHang(String diaChiCuaHang) {
        this.diaChiCuaHang = diaChiCuaHang;
    }

}
