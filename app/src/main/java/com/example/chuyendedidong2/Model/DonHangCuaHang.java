package com.example.chuyendedidong2.Model;

import java.util.ArrayList;

public class DonHangCuaHang {
    private String bill_id;
    private String img_sanpham;
    private String trangthai_sanpham, ten_sanpham;
    private int gia_sanpham, sl_sanpham;
    private String ten_khachhang, sdt_khachhang, diachi_khachhang,diachi_cuahhang,shop_id;

    public DonHangCuaHang() {
    }

    public DonHangCuaHang(String bill_id, String img_sanpham, String trangthai_sanpham, String ten_sanpham, int gia_sanpham, int sl_sanpham, String ten_khachhang, String sdt_khachhang, String diachi_khachhang, String diachi_cuahhang, String shop_id) {
        this.bill_id = bill_id;
        this.img_sanpham = img_sanpham;
        this.trangthai_sanpham = trangthai_sanpham;
        this.ten_sanpham = ten_sanpham;
        this.gia_sanpham = gia_sanpham;
        this.sl_sanpham = sl_sanpham;
        this.ten_khachhang = ten_khachhang;
        this.sdt_khachhang = sdt_khachhang;
        this.diachi_khachhang = diachi_khachhang;
        this.diachi_cuahhang = diachi_cuahhang;
        this.shop_id = shop_id;
    }

    public DonHangCuaHang(String bill_id, String img_sanpham, String trangthai_sanpham, String ten_sanpham, int gia_sanpham, int sl_sanpham, String ten_khachhang, String sdt_khachhang, String diachi_khachhang) {
        this.bill_id = bill_id;
        this.img_sanpham = img_sanpham;
        this.trangthai_sanpham = trangthai_sanpham;
        this.ten_sanpham = ten_sanpham;
        this.gia_sanpham = gia_sanpham;
        this.sl_sanpham = sl_sanpham;
        this.ten_khachhang = ten_khachhang;
        this.sdt_khachhang = sdt_khachhang;
        this.diachi_khachhang = diachi_khachhang;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getImg_sanpham() {
        return img_sanpham;
    }

    public void setImg_sanpham(String img_sanpham) {
        this.img_sanpham = img_sanpham;
    }

    public String getTrangthai_sanpham() {
        return trangthai_sanpham;
    }

    public void setTrangthai_sanpham(String trangthai_sanpham) {
        this.trangthai_sanpham = trangthai_sanpham;
    }

    public String getTen_sanpham() {
        return ten_sanpham;
    }

    public void setTen_sanpham(String ten_sanpham) {
        this.ten_sanpham = ten_sanpham;
    }

    public int getGia_sanpham() {
        return gia_sanpham;
    }

    public void setGia_sanpham(int gia_sanpham) {
        this.gia_sanpham = gia_sanpham;
    }

    public int getSl_sanpham() {
        return sl_sanpham;
    }

    public void setSl_sanpham(int sl_sanpham) {
        this.sl_sanpham = sl_sanpham;
    }

    public String getTen_khachhang() {
        return ten_khachhang;
    }

    public void setTen_khachhang(String ten_khachhang) {
        this.ten_khachhang = ten_khachhang;
    }

    public String getSdt_khachhang() {
        return sdt_khachhang;
    }

    public void setSdt_khachhang(String sdt_khachhang) {
        this.sdt_khachhang = sdt_khachhang;
    }

    public String getDiachi_khachhang() {
        return diachi_khachhang;
    }

    public void setDiachi_khachhang(String diachi_khachhang) {
        this.diachi_khachhang = diachi_khachhang;
    }
    public ArrayList<DonHangCuaHang> createList(){
        ArrayList<DonHangCuaHang> donHangCuaHangs = new ArrayList<>();
        donHangCuaHangs.add(new DonHangCuaHang("dh01","https://firebasestorage.googleapis.com/v0/b/chuyendedidong2-4ba31.appspot.com/o/imagesjpg?alt=media&token=6b15dd07-7cea-4715-a186-a66d464db6c7","Chờ xác nhận","máy tinh",400000,1,"Trần Đăng Khoa","090245673","TPHCM","Hue","shop001"));
        donHangCuaHangs.add(new DonHangCuaHang("dh02","https://cdn01.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture//Apro/Apro_product_25056/tu-lanh-inverte_main_59_1020.png.webp","Chờ xác nhận","máy tinh",400000,1,"Trần Đăng","090245673","TPHCM","Hue","shop002"));
        donHangCuaHangs.add(new DonHangCuaHang("dh03","https://upload.wikimedia.org/wikipedia/commons/c/cc/Modified-pc-case.png","Chờ xác nhận","máy tinh",400000,1,"Trần Đăng","090245673","TPHCM","Hue","shop003"));

        return donHangCuaHangs;
    }
}
