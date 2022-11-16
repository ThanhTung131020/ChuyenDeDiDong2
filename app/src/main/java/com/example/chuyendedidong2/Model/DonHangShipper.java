package com.example.chuyendedidong2.Model;

import java.util.ArrayList;

public class DonHangShipper {
    String bill_id, ten_ch, trang_thai, ten_sp;
    int gia;
    String dia_chi_ch, dia_chi_kh;

    public DonHangShipper(String bill_id, String ten_ch, String trang_thai, String ten_sp, int gia, String dia_chi_ch, String dia_chi_kh) {
        this.bill_id = bill_id;
        this.ten_ch = ten_ch;
        this.trang_thai = trang_thai;
        this.ten_sp = ten_sp;
        this.gia = gia;
        this.dia_chi_ch = dia_chi_ch;
        this.dia_chi_kh = dia_chi_kh;
    }

    public DonHangShipper() {
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getTen_ch() {
        return ten_ch;
    }

    public void setTen_ch(String ten_ch) {
        this.ten_ch = ten_ch;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getDia_chi_ch() {
        return dia_chi_ch;
    }

    public void setDia_chi_ch(String dia_chi_ch) {
        this.dia_chi_ch = dia_chi_ch;
    }

    public String getDia_chi_kh() {
        return dia_chi_kh;
    }

    public void setDia_chi_kh(String dia_chi_kh) {
        this.dia_chi_kh = dia_chi_kh;
    }
    public ArrayList<DonHangShipper> createList(){
        ArrayList<DonHangShipper> list = new ArrayList<>();
        list.add(new DonHangShipper("dhsp01","shop123","Chờ xác nhận","galaxy",10000,"TPHCM","Hue"));
        list.add(new DonHangShipper("dhsp02","shop123","Chờ xác nhận","galaxy",10000,"TPHCM","Hue"));
        return list;
    }
}
