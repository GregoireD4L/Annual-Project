package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "allPoints")
public class Spo2Point_2 {

    @Column(name = "ID")
    private String idUser;
    @Column(name = "Spo2Chan2-1")
    private double spo2Chan2_1;
    @Column(name = "Spo2Chan2-2")
    private double spo2Chan2_2;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
