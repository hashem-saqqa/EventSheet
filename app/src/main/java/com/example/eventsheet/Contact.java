package com.example.eventsheet;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private int image;
    private String name;
    private String number;

    public Contact(int image, String name, String number) {
        this.image = image;
        this.name = name;
        this.number = number;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    public static List<Contact> generateDummyContacts() {
//        List<Contact> contacts = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            contacts.add(new Contact(R.drawable.nopath___copy__79_, "Contact #" + i, i + "" + i + "" + i));
//        }
//        return contacts;
//    }

}

