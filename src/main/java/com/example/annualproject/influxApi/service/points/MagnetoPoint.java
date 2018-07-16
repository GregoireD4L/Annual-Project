package com.example.annualproject.influxApi.service.points;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;
/***
 *  Created by Nicolas Sirac
 ****/
@Measurement(name = "allPoints")
public class MagnetoPoint implements Point {

    private long longtime;
    @Column(name = "magnetoX")
    private double magnetoX;
    @Column(name = "magnetoY")
    private double magnetoY;
    @Column(name = "magnetoZ")
    private double magnetoZ;
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

    public double getMagnetoX() {
        return magnetoX;
    }

    public void setMagnetoX(double magnetoX) {
        this.magnetoX = magnetoX;
    }

    public double getMagnetoY() {
        return magnetoY;
    }

    public void setMagnetoY(double magnetoY) {
        this.magnetoY = magnetoY;
    }

    public double getMagnetoZ() {
        return magnetoZ;
    }

    public void setMagnetoZ(double magnetoZ) {
        this.magnetoZ = magnetoZ;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        MagnetoPoint magnetoPoint = (MagnetoPoint) obj;
        if (this.longtime / 20 != magnetoPoint.longtime / 20) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Double.valueOf(this.magnetoZ).hashCode() + Double.valueOf(this.magnetoZ).hashCode() * 20 + Double.valueOf(this.magnetoY).hashCode() * 300 + Double.valueOf(this.longtime / 20).hashCode();
        return hash;
    }


}

