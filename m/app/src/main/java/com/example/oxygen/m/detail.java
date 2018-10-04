package com.example.oxygen.m;

import java.util.Date;

public class detail {
    private String tenThanhPho, toaDo, anh, huongGio, may;
    private int nhietDoSang, nhietDoToi, doAm, luongMua;
    private Date time_update;

    public detail() {

    }

    public detail(String tenThanhPho, String toaDo, String anh, String huongGio, String may, int nhietDoSang, int nhietDoToi, int doAm, int luongMua, Date time_update) {
        this.tenThanhPho = tenThanhPho;
        this.toaDo = toaDo;
        this.anh = anh;
        this.huongGio = huongGio;
        this.may = may;
        this.nhietDoSang = nhietDoSang;
        this.nhietDoToi = nhietDoToi;
        this.doAm = doAm;
        this.luongMua = luongMua;
        this.time_update = time_update;
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

    public int getNhietDoSang() {
        return nhietDoSang;
    }

    public void setNhietDoSang(int nhietDoSang) {
        this.nhietDoSang = nhietDoSang;
    }

    public int getNhietDoToi() {
        return nhietDoToi;
    }

    public void setNhietDoToi(int nhietDoToi) {
        this.nhietDoToi = nhietDoToi;
    }

    public String getHuongGio() {
        return huongGio;
    }

    public void setHuongGio(String huongGio) {
        this.huongGio = huongGio;
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

    public String getToaDo() {
        return toaDo;
    }

    public void setToaDo(String toaDo) {
        this.toaDo = toaDo;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    @Override
    public String toString() {
        return "[" + this.toaDo + " - " + this.tenThanhPho + "]";
    }
}
