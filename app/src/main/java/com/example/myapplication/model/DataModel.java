package com.example.myapplication.model;

public class DataModel {

    private String country, city, imgURL;

    public String getImgUrl(){
        return imgURL;
    }

    public void setImgUrl(String imgURL){
        this.imgURL = imgURL;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

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
}
