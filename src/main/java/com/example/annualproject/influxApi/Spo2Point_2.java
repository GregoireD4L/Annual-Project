package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "allPoints")
public class Spo2Point_2 {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "Spo2Chan2-1")
    private double spo2Chan2_1;
    @Column(name = "Spo2Chan2-2")
    private double spo2Chan2_2;
    @Column(name = "time")
    private Instant time;


    private long longtime;


    public double getSpo2Chan2_1() {
        return spo2Chan2_1;
    }

    public void setSpo2Chan2_1(double spo2Chan2_1) {
        this.spo2Chan2_1 = spo2Chan2_1;
    }

    public double getSpo2Chan2_2() {
        return spo2Chan2_2;
    }

    public void setSpo2Chan2_2(double spo2Chan2_2) {
        this.spo2Chan2_2 = spo2Chan2_2;
    }

    public long getLongtime() {
        return longtime;
    }

    public void setLongtime(long longtime) {
        this.longtime = longtime;
    }

    public void setLongtime(Instant longtime) {
        this.longtime = longtime.toEpochMilli();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public double getspo2Chan2_1() {
        return spo2Chan2_1;
    }

    public void setspo2Chan2_1(double spo2Chan2_1) {
        spo2Chan2_1 = spo2Chan2_1;
    }

    public double getspo2Chan2_2() {
        return spo2Chan2_2;
    }

    public void setspo2Chan2_2(double spo2Chan2_2) {
        spo2Chan2_2 = spo2Chan2_2;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        Spo2Point_2 spo2point = (Spo2Point_2) obj;
        if(spo2point.spo2Chan2_1!=this.spo2Chan2_2){
            return false;
        }
        if(spo2point.spo2Chan2_2!=this.spo2Chan2_2){
            return false;
        }
        return true;
    }
}
