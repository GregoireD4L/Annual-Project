package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "allPoints")
public class TempPoint {
    @Column(name = "ID")
    private String idUser;
    @Column(name = "temp")
    private double temp;

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

    public double gettemp() {
        return temp;
    }

    public void settemp(double temp) {
        temp = temp;
    }





    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

