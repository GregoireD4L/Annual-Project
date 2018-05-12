package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name="EcgChannelOnePoint")
public class EcgChannelOnePoint {
    @Column(name = "idUser")
    private String idUser;
    @Column(name = "p1")
    private double p1;
    @Column(name = "time")
    private Instant time;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

}
