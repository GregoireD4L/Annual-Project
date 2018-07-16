package com.example.annualproject.influxApi.service.points;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;
/***
 *  Created by Nicolas Sirac
 ****/
@Measurement(name = "allPoints")
public class RespiPoint implements Point {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "respiAbdominal")
    private double respiAbdominal;
    @Column(name = "respiThorax")
    private double respiThorax;
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

    public double getRespiAbdominal() {
        return respiAbdominal;
    }

    public void setRespiAbdominal(double respiAbdominal) {
        this.respiAbdominal = respiAbdominal;
    }

    public double getRespiThorax() {
        return respiThorax;
    }

    public void setRespiThorax(double respiThorax) {
        this.respiThorax = respiThorax;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        RespiPoint respiPoint = (RespiPoint) obj;
        if (this.longtime / 20 != respiPoint.longtime / 20) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Double.valueOf(this.respiThorax).hashCode() + Double.valueOf(this.longtime / 20).hashCode();
        return hash;
    }

}
