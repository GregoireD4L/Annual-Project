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
    @Column(name = "time")
    private Instant time;


    private Long longtime;



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

    public double getEcg1() {
        return ecg1;
    }

    public void setEcg1(double ecg1) {
        this.ecg1 = ecg1;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
    @Override
    public boolean equals(Object obj) {
        Ecg1Point ecg1Point = (Ecg1Point) obj;
        if(ecg1Point.ecg1!=this.ecg1){
            return false;
        }
        
        return true;
    }
}
