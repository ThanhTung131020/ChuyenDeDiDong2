package com.example.chuyendedidong2.Model;

public class ImageSlider {
    private String img_slider_id;
    private String img_url;

    public ImageSlider() {
    }

    public ImageSlider(String img_slider_id, String img_url) {
        this.img_slider_id = img_slider_id;
        this.img_url = img_url;
    }

    public String getImg_slider_id() {
        return img_slider_id;
    }

    public void setImg_slider_id(String img_slider_id) {
        this.img_slider_id = img_slider_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
