package com.example.eventsheet;

public class Event_model {
    private int Image;
    private String Main_text;
    private String Location_text;
    private String Detail_text;
    private String Start_date_text;
    private String End_date_text;
    private String Auther_text;
    private String My_created_event_status;

    public Event_model(int image, String main_text, String location_text,
                       String detail_text, String start_date_text,
                       String end_date_text, String auther_text) {
        Image = image;
        Main_text = main_text;
        Location_text = location_text;
        Detail_text = detail_text;
        Start_date_text = start_date_text;
        End_date_text = end_date_text;
        Auther_text = auther_text;
    }

    public Event_model(int image, String main_text, String location_text,
                       String start_date_text, String end_date_text) {
        Image = image;
        Main_text = main_text;
        Location_text = location_text;
        Start_date_text = start_date_text;
        End_date_text = end_date_text;
    }

    public Event_model(int image, String main_text, String location_text) {
        Image = image;
        Main_text = main_text;
        Location_text = location_text;
    }

    public Event_model(int image, String main_text, String location_text, String start_date_text) {
        Image = image;
        Main_text = main_text;
        Location_text = location_text;
        Start_date_text = start_date_text;
    }

    public Event_model(int image, String main_text, String location_text,
                       String start_date_text, String end_date_text,
                       String my_created_event_status) {
        Image = image;
        Main_text = main_text;
        Location_text = location_text;
        Start_date_text = start_date_text;
        End_date_text = end_date_text;
        My_created_event_status = my_created_event_status;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        this.Image = image;
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
        return Detail_text;
    }

    public void setDetail_text(String detail_text) {
        this.Detail_text = detail_text;
    }

    public String getStart_date_text() {
        return Start_date_text;
    }

    public void setStart_date_text(String start_date_text) {
        this.Start_date_text = start_date_text;
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

    public String getMy_created_event_status() {
        return My_created_event_status;
    }

    public void setMy_created_event_status(String my_created_event_status) {
        My_created_event_status = my_created_event_status;
    }
}

