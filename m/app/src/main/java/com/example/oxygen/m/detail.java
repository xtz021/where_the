package com.example.oxygen.m;

import java.util.Date;

public class detail {
    private String tenThanhPho, gio, may, img;
    private int nhietdo, doAm, luongMua;
    private float lan, lon;
    private Date time_update;

    public detail() {

    }

    public detail(String tenThanhPho, String gio, String may, int nhietdo, int doAm, int luongMua, Date time_update) {
        this.tenThanhPho = tenThanhPho;
        this.gio = gio;
        this.may = may;
        this.nhietdo = nhietdo;
        this.doAm = doAm;
        this.luongMua = luongMua;
        this.time_update = time_update;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setTime_update(Date time_update) {
        this.time_update = time_update;
    }

    public Date getTime_update() {
        return time_update;
    }

    public int getDoAm() {
        return doAm;
    }

    public void setDoAm(int doAm) {
        this.doAm = doAm;
    }

    public int getLuongMua() {
        return luongMua;
    }

    public void setLuongMua(int luongMua) {
        this.luongMua = luongMua;
    }

    public int getNhietdo() {
        return nhietdo;
    }

    public void setNhietdo(int nhietdo) {
        this.nhietdo = nhietdo;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getGio() {
        return gio;
    }

    public String getMay() {
        return may;
    }

    public void setMay(String may) {
        this.may = may;
    }

    public String getTenThanhPho() {
        return tenThanhPho;
    }

    public void setTenThanhPho(String tenThanhPho) {
        this.tenThanhPho = tenThanhPho;
    }

    public float getLan() {
        return lan;
    }

    public void setLan(float lan) {
        this.lan = lan;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

}
