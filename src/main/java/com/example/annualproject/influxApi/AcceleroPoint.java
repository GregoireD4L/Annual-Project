package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

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
    @Column(name = "time")
    private Instant time;


    private Long longtime;


    public void setLongtime(Instant longtime) {
        this.longtime = longtime.toEpochMilli();
    }

    public void setLongtime(Long longtime) {
        this.longtime = longtime;
    }

    public Long getLongtime() {
        return longtime;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
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
    @Override
    public boolean equals(Object obj) {
        AcceleroPoint acceleroPoint = (AcceleroPoint) obj;
        if(acceleroPoint.acceleroX!=this.acceleroX){
            return false;
        }
        if(acceleroPoint.acceleroY!=this.acceleroY){
            return false;
        }
        if(acceleroPoint.acceleroZ!=this.acceleroZ){
            return false;
        }
        return true;
    }
}
