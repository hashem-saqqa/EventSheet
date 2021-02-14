package com.example.eventsheet;

public class Event_model {
    private int image;
    private String Main_text;
    private String Location_text;
    private String detail_text;
    private String start_date_text;
    private String End_date_text;
    private String Auther_text;

    public Event_model(int image, String main_text, String location_text,
                       String detail_text, String start_date_text,
                       String end_date_text, String auther_text) {
        this.image = image;
        Main_text = main_text;
        Location_text = location_text;
        this.detail_text = detail_text;
        this.start_date_text = start_date_text;
        End_date_text = end_date_text;
        Auther_text = auther_text;
    }

    public Event_model(int image, String main_text, String location_text,
                       String start_date_text, String end_date_text) {
        this.image = image;
        Main_text = main_text;
        Location_text = location_text;
        this.start_date_text = start_date_text;
        End_date_text = end_date_text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMain_text() {
        return Main_text;
    }

    public void setMain_text(String main_text) {
        Main_text = main_text;
    }

    public String getLocation_text() {
        return Location_text;
    }

    public void setLocation_text(String location_text) {
        Location_text = location_text;
    }

    public String getDetail_text() {
        return detail_text;
    }

    public void setDetail_text(String detail_text) {
        this.detail_text = detail_text;
    }

    public String getStart_date_text() {
        return start_date_text;
    }

    public void setStart_date_text(String start_date_text) {
        this.start_date_text = start_date_text;
    }

    public String getEnd_date_text() {
        return End_date_text;
    }

    public void setEnd_date_text(String end_date_text) {
        End_date_text = end_date_text;
    }

    public String getAuther_text() {
        return Auther_text;
    }

    public void setAuther_text(String auther_text) {
        Auther_text = auther_text;
    }
}

