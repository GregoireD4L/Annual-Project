package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "allPoints")
public class AcceleroPoint {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "acceleroX")
    private double acceleroX;
    @Column(name = "acceleroY")
    private double acceleroY;
    @Column(name = "acceleroZ")
    private double acceleroZ;
    @Column(name = "timestamp")
    private long time;


    private Long longtime;


    public Long getLongtime() {
        return longtime;
    }

    public void setLongtime(Long longtime) {
        this.longtime = longtime;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getAcceleroX() {
        return acceleroX;
    }

    public void setAcceleroX(double acceleroX) {
        this.acceleroX = acceleroX;
    }

    public double getAcceleroY() {
        return acceleroY;
    }

    public void setAcceleroY(double acceleroY) {
        this.acceleroY = acceleroY;
    }

    public double getAcceleroZ() {
        return acceleroZ;
    }

    public void setAcceleroZ(double acceleroZ) {
        this.acceleroZ = acceleroZ;
    }
}
