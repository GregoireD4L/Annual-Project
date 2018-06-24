package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "allPoints")
public class Ecg1Point {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "ecg1")
    private double ecg1;
    @Column(name = "timestamp")
    private long time;
    @Column(name = "acceleroX1")
    private long acceleroX1;

    private Long longtime;

    public long getAcceleroX1() {
        return acceleroX1;
    }

    public void setAcceleroX1(long acceleroX1) {
        this.acceleroX1 = acceleroX1;
    }

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

    public double getEcg1() {
        return ecg1;
    }

    public void setEcg1(double ecg1) {
        this.ecg1 = ecg1;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
