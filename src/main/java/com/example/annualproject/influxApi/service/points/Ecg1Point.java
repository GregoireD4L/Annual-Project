package com.example.annualproject.influxApi.service.points;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;
/***
 *  Created by Nicolas Sirac
 ****/
@Measurement(name = "allPoints")
public class Ecg1Point implements Point {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "ecg1")
    private double ecg1;
    @Column(name = "time")
    private Instant time;


    private long longtime;


    public long getLongtime() {
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
        if (ecg1Point.ecg1 != this.ecg1) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Double.valueOf(this.ecg1).hashCode();
        return hash;
    }


}
