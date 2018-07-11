package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "allPoints")
public class TempPoint {
    @Column(name = "ID")
    private String idUser;
    @Column(name = "temp")
    private double temp;

    @Column(name = "time")
    private Instant time;


    private Long longtime;


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Long getLongtime() {
        return longtime;
    }

    public void setLongtime(Instant longtime) {
        this.longtime = longtime.toEpochMilli();
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

    public double gettemp() {
        return temp;
    }

    public void settemp(double temp) {
        temp = temp;
    }



    @Override
    public boolean equals(Object obj) {
        TempPoint tempPoint = (TempPoint) obj;
        if(tempPoint.temp!=this.temp){
            return false;
        }

        return true;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}

