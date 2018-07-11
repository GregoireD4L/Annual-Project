package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "allPoints")
public class RespiPoint {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "respiAbdominal")
    private double respiAbdominal;
    @Column(name = "respiThorax")
    private double respiThorax;
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
        if(respiPoint.respiAbdominal!=this.respiAbdominal){
            return false;
        }
        if(respiPoint.respiThorax!=this.respiThorax){
            return false;
        }
        return true;
    }
}
