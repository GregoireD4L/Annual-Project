package com.example.annualproject.influxApi.service.points;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;
/***
 *  Created by Nicolas Sirac
 ****/
@Measurement(name = "allPoints")
public class GyroPoint implements Point {
    private long longtime;
    @Column(name = "GyrosX")
    private double gyroX;
    @Column(name = "GyrosY")
    private double gyroY;
    @Column(name = "GyrozZ")
    private double gyroZ;
    @Column(name = "time")
    private Instant time;

    public long getLongtime() {
        return longtime;
    }

    public void setLongtime(long longtime) {
        this.longtime = longtime;
    }

    public void setLongtime(Instant longtime) {
        this.longtime = longtime.toEpochMilli();
    }

    public double getGyroX() {
        return gyroX;
    }

    public void setGyroX(double gyroX) {
        this.gyroX = gyroX;
    }

    public double getGyroY() {
        return gyroY;
    }

    public void setGyroY(double gyroY) {
        gyroY = gyroY;
    }

    public double getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(double gyroZ) {
        this.gyroZ = gyroZ;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        GyroPoint gyroPoint = (GyroPoint) obj;
        if (this.longtime / 20 != gyroPoint.longtime / 20) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Double.valueOf(this.gyroZ).hashCode() + Double.valueOf(this.gyroZ).hashCode() * 20 + Double.valueOf(this.gyroY).hashCode() * 300 + Double.valueOf(this.longtime / 20).hashCode();
        return hash;
    }
}
