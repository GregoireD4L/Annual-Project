package com.example.annualproject.influxApi.service.points;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Measurement(name = "allPoints")
public class GyroPoint implements Point{
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

    public void setLongtime(Instant longtime) {
        this.longtime = longtime.toEpochMilli();
    }


    public void setLongtime(long longtime) {
        this.longtime = longtime;
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
        if(gyroPoint.gyroX!=this.gyroX){
            return false;
        }
        if(gyroPoint.gyroY!=this.gyroY){
            return false;
        }
        if(gyroPoint.gyroZ!=this.gyroZ){
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash +  Double.valueOf(this.gyroZ).hashCode()+Double.valueOf(this.gyroZ).hashCode()*20+Double.valueOf(this.gyroY).hashCode()*300;
        return hash;
    }
}
