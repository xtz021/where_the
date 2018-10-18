package com.example.oxygen.m;

import java.util.ArrayList;

public class WeatherObject {
    private String lat,lon, date, city, temp, humid, wind, cloud;
    private ArrayList<NextDaysObject> list5Ngay = new ArrayList<>();

    public WeatherObject(String lat, String lon, String date, String city, String temp,
                         String humid, String wind, String cloud, ArrayList<NextDaysObject> list5Ngay) {
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.city = city;
        this.temp = temp;
        this.humid = humid;
        this.wind = wind;
        this.cloud = cloud;
        this.list5Ngay = list5Ngay;
    }

    public WeatherObject(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public WeatherObject(String date, String city, String temp, String humid, String wind,
                         String cloud, ArrayList<NextDaysObject> list5Ngay) {
        this.date = date;
        this.city = city;
        this.temp = temp;
        this.humid = humid;
        this.wind = wind;
        this.cloud = cloud;
        this.list5Ngay = list5Ngay;
    }

    public WeatherObject() {
        this.date = "";
        this.city = "";
        this.temp = "";
        this.humid = "";
        this.wind = "";
        this.cloud = "";
    }

    public ArrayList<NextDaysObject> getList5Ngay() {
        return list5Ngay;
    }

    public void setList5Ngay(ArrayList<NextDaysObject> list5Ngay) {
        this.list5Ngay = list5Ngay;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumid() {
        return humid;
    }

    public void setHumid(String humid) {
        this.humid = humid;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }


}
