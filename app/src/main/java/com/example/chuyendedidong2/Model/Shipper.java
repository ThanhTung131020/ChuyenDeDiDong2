package com.example.chuyendedidong2.Model;

public class Shipper {
    String id, name, sdt, diachi, email;
    int hangDaGiao;

    public Shipper(String id, String name, String sdt, String diachi, String email, int hangDaGiao) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.diachi = diachi;
        this.email = email;
        this.hangDaGiao = hangDaGiao;
    }

    public Shipper() {
    }

    public Shipper(String id, String name, String sdt, String diachi, String email) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.diachi = diachi;
        this.email = email;
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

    public int getHangDaGiao() {
        return hangDaGiao;
    }

    public void setHangDaGiao(int hangDaGiao) {
        this.hangDaGiao = hangDaGiao;
    }
}
