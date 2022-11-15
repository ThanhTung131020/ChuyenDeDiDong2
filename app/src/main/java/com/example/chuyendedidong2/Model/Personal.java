package com.example.chuyendedidong2.Model;

import java.util.HashMap;
import java.util.Map;

public class Personal {
    String id, name, sdt, diachi, email;

    public Personal() {
    }

    public Personal(String id, String name, String sdt, String diachi, String email) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.diachi = diachi;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("sdt", sdt);
        hashMap.put("diachi",diachi);
        return hashMap;
    }

}
