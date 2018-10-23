package com.example.oxygen.weather;

import java.io.Serializable;

public class weatherObject implements Serializable {
    private String lat,lon, date, city, temp, humid, wind, cloud;

    public weatherObject() {
    }

    public weatherObject(String lat, String lon, String date, String city, String temp, String humid, String wind, String cloud) {
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.city = city;
        this.temp = temp;
        this.humid = humid;
        this.wind = wind;
        this.cloud = cloud;
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
