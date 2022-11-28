package com.example.chuyendedidong2.Model;

public class Star {
    String uid;
    float numStar;

    public Star(String uid, float numStar) {
        this.uid = uid;
        this.numStar = numStar;
    }

    public Star(float numStar) {
        this.numStar = numStar;
    }

    public Star() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getNumStar() {
        return numStar;
    }

    public void setNumStar(float numStar) {
        this.numStar = numStar;
    }
}
