package com.example.oxygen.m;

public class detail {
    private String tenThanhPho, toaDo, anh;

    public detail(String tenThanhPho, String toaDo, String anh)
    {
        this.tenThanhPho = tenThanhPho;
        this.toaDo = toaDo;
        this.anh = anh;
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
        return "["+this.toaDo + " - " + this.tenThanhPho+"]";
    }
}
