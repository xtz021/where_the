package com.example.oxygen.m;

import java.util.Date;

public class detail {
    private String CityName, temp, clouds;
    private float lat, lon;
    private String Time;
    public detail(String ha_noi, String temp, String no_clouds, int lat, int lon, int i) {
        super();
    }

    public detail(String cityName, String temp, String clouds, float lat, float lon, String time) {
        super();
        CityName = cityName;
        this.temp = temp;
        this.clouds = clouds;
        this.lat = lat;
        this.lon = lon;
        this.Time = time;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
