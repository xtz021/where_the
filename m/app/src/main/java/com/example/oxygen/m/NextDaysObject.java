package com.example.oxygen.m;

public class NextDaysObject {
    private String date, tempDay, tempNight, status, icon;

    public NextDaysObject(String date, String tempDay, String tempNight, String status, String icon) {
        this.date = date;
        this.tempDay = tempDay;
        this.tempNight = tempNight;
        this.status = status;
        this.icon = icon;
    }

    public NextDaysObject() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempDay() {
        return tempDay;
    }

    public void setTempDay(String tempDay) {
        this.tempDay = tempDay;
    }

    public String getTempNight() {
        return tempNight;
    }

    public void setTempNight(String tempNight) {
        this.tempNight = tempNight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
