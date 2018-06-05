package com.example.annualproject.influxApi;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "allPoints")
public class InfluxPoint {

    @Column(name = "ID")
    private String idUser;


    @Column(name = "ecg1")
    private double ecg1;
    @Column(name = "ecg2")
    private double ecg2;
    @Column(name = "ecg3")
    private double ecg3;


    @Column(name = "acceleroX")
    private double acceleroX;
    @Column(name = "acceleroY")
    private double acceleroY;
    @Column(name = "acceleroZ")
    private double acceleroZ;


    @Column(name = "magnetoX")
    private double magnetoX;
    @Column(name = "magnetoY")
    private double magnetoY;
    @Column(name = "magnetoZ")
    private double magnetoZ;


    @Column(name = "respiThorax")
    private double respiThorax;
    @Column(name = "respiAbdominal")
    private double respiAbdominal;


    @Column(name = "Spo2Chan1_1")
    private double Spo2Chan1_1;
    @Column(name = "Spo2Chan1_2")
    private double Spo2Chan1_2;

    @Column(name = "Spo2Chan2_1")
    private double Spo2Chan2_1;
    @Column(name = "Spo2Chan2_2")
    private double Spo2Chan2_2;

    @Column(name = "temp")
    private double temp;

    @Column(name = "timestamp")
    private long time;


    private Long longtime;

    public double getEcg2() {
        return ecg2;
    }

    public void setEcg2(double ecg2) {
        this.ecg2 = ecg2;
    }

    public double getEcg3() {
        return ecg3;
    }

    public void setEcg3(double ecg3) {
        this.ecg3 = ecg3;
    }

    public double getAcceleroX() {
        return acceleroX;
    }

    public void setAcceleroX(double acceleroX) {
        this.acceleroX = acceleroX;
    }

    public double getAcceleroY() {
        return acceleroY;
    }

    public void setAcceleroY(double acceleroY) {
        this.acceleroY = acceleroY;
    }

    public double getAcceleroZ() {
        return acceleroZ;
    }

    public void setAcceleroZ(double acceleroZ) {
        this.acceleroZ = acceleroZ;
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

    public double getRespiThorax() {
        return respiThorax;
    }

    public void setRespiThorax(double respiThorax) {
        this.respiThorax = respiThorax;
    }

    public double getRespiAbdominal() {
        return respiAbdominal;
    }

    public void setRespiAbdominal(double respiAbdominal) {
        this.respiAbdominal = respiAbdominal;
    }

    public double getSpo2Chan1_1() {
        return Spo2Chan1_1;
    }

    public void setSpo2Chan1_1(double spo2Chan1_1) {
        Spo2Chan1_1 = spo2Chan1_1;
    }

    public double getSpo2Chan1_2() {
        return Spo2Chan1_2;
    }

    public void setSpo2Chan1_2(double spo2Chan1_2) {
        Spo2Chan1_2 = spo2Chan1_2;
    }

    public double getSpo2Chan2_1() {
        return Spo2Chan2_1;
    }

    public void setSpo2Chan2_1(double spo2Chan2_1) {
        Spo2Chan2_1 = spo2Chan2_1;
    }

    public double getSpo2Chan2_2() {
        return Spo2Chan2_2;
    }

    public void setSpo2Chan2_2(double spo2Chan2_2) {
        Spo2Chan2_2 = spo2Chan2_2;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

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

    public double getEcg1() {
        return ecg1;
    }

    public void setEcg1(double ecg1) {
        this.ecg1 = ecg1;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
