package com.ssafy.trip.model.dto;

public class FestivalDto {

    private int num;
    private String address;
    private String date;
    private String place;
    private String name;

    public FestivalDto(int num){
        this.num = num;
    }

    public int getNum(){
        return num;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
