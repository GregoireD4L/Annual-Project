package com.example.annualproject.influxApi.service.points;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "allPoints")
public class TempPoint implements Point {
    @Column(name = "temp")
    private double temp;

    @Column(name = "time")
    private Instant time;


    private long longtime;


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public long getLongtime() {
        return longtime;
    }

    public void setLongtime(Instant longtime) {
        this.longtime = longtime.toEpochMilli();
    }


    @Override
    public boolean equals(Object obj) {
        TempPoint tempPoint = (TempPoint) obj;
        if (this.longtime / 20 != tempPoint.longtime / 20) {
            return false;
        }

        return true;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Double.valueOf(this.temp).hashCode() + Double.valueOf(this.longtime / 20).hashCode();
        return hash;
    }

}

