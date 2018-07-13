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
public class TempPoint implements Point{
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash +  Double.valueOf(this.temp).hashCode();
        return hash;
    }

}

