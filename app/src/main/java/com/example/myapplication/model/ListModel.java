package com.example.myapplication.model;
public class ListModel {
    private String name, country, city, imgURL;
    public String getImgURL(){
        return imgURL;
    }
    public void setImgURL(String imgURL){
        this.imgURL = imgURL;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}