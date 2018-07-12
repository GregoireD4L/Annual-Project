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
public class Spo2Point implements Point{

    @Column(name = "ID")
    private String idUser;
    @Column(name = "Spo2Chan1-1")
    private double spo2;
   
    @Column(name = "time")
    private Instant time;


    private long longtime;

    public double getSpo2Chan1_1() {
        return spo2;
    }

    public void setSpo2Chan1_1(double spo2) {
        this.spo2 = spo2;
    }

   

    public void setLongtime(long longtime) {
        this.longtime = longtime;
    }

    public long getLongtime() {
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

    public double getspo2() {
        return spo2;
    }

    public void setspo2(double spo2) {
        spo2 = spo2;
    }



    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
    @Override
    public boolean equals(Object obj) {
        Spo2Point spo2point = (Spo2Point) obj;
        if(spo2point.spo2!=this.spo2){
            return false;
        }

        return true;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash +  Double.valueOf(this.spo2).hashCode();
        return hash;
    }



}
