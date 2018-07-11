package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "allPoints")
public class Spo2Point_1 {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "Spo2Chan1-1")
    private double spo2Chan1_1;
    @Column(name = "Spo2Chan1-2")
    private double spo2Chan1_2;
    @Column(name = "time")
    private Instant time;


    private Long longtime;

    public double getSpo2Chan1_1() {
        return spo2Chan1_1;
    }

    public void setSpo2Chan1_1(double spo2Chan1_1) {
        this.spo2Chan1_1 = spo2Chan1_1;
    }

    public double getSpo2Chan1_2() {
        return spo2Chan1_2;
    }

    public void setSpo2Chan1_2(double spo2Chan1_2) {
        this.spo2Chan1_2 = spo2Chan1_2;
    }

    public void setLongtime(Long longtime) {
        this.longtime = longtime;
    }

    public Long getLongtime() {
        return longtime;
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

    public double getspo2Chan1_1() {
        return spo2Chan1_1;
    }

    public void setspo2Chan1_1(double spo2Chan1_1) {
        spo2Chan1_1 = spo2Chan1_1;
    }

    public double getspo2Chan1_2() {
        return spo2Chan1_2;
    }

    public void setspo2Chan1_2(double spo2Chan1_2) {
        spo2Chan1_2 = spo2Chan1_2;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
    @Override
    public boolean equals(Object obj) {
        Spo2Point_1 spo2point = (Spo2Point_1) obj;
        if(spo2point.spo2Chan1_1!=this.spo2Chan1_2){
            return false;
        }
        if(spo2point.spo2Chan1_2!=this.spo2Chan1_2){
            return false;
        }
        return true;
    }
}
