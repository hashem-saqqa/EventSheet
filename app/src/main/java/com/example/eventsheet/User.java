package com.example.eventsheet;

public class User {
    private String name;
    private String email;
    private String phone;
    private String country;
    private String photo;


//    public User(String name, String email, String phone, String country, String photo) {
//        this.photo = photo;
//        this.name = name;
//        this.email = email;
//        this.phone = phone;
//        this.country = country;
//    }

    public User(String country, String name, String photo, String phone, String email) {
        this.photo = photo;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
