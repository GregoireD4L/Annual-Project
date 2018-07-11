package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "allPoints")
public class TempPoint {
    @Column(name = "temp")
    private double temp;

    @Column(name = "time")
    private Instant time;


    private Long longtime;


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Long getLongtime() {
        return longtime;
    }

    public void setLongtime(Instant longtime) {
        this.longtime = longtime.toEpochMilli();
    }










    @Override
    public boolean equals(Object obj) {
        TempPoint tempPoint = (TempPoint) obj;
        if(tempPoint.temp!=this.temp){
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
}

