package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "allPoints")
public class Spo2Point {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "Spo2Chan1-1")
    private double Spo2Chan1_1;
    @Column(name = "Spo2Chan1-2")
    private double Spo2Chan1_2;
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

    public double getSpo2Chan1_1() {
        return Spo2Chan1_1;
    }

    public void setSpo2Chan1_1(double spo2Chan1_1) {
        Spo2Chan1_1 = spo2Chan1_1;
    }

    public double getSpo2Chan1_2() {
        return Spo2Chan1_2;
    }

    public void setSpo2Chan1_2(double spo2Chan1_2) {
        Spo2Chan1_2 = spo2Chan1_2;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
