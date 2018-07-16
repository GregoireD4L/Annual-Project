package com.example.annualproject.influxApi.service.influx;

import com.example.annualproject.influxApi.service.points.*;
import com.example.annualproject.security.Decrypter;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static com.example.annualproject.influxApi.service.points.InfluxField.*;
import static org.junit.Assert.*;

public class InfluxDBReaderTest {

    @Test
    public void generateQueryECG() {
        String id="a";
        long beginning=10;
        long ending=110;

        InfluxField field= ECG;

        try {
            String queryResult="SELECT ecg1,time FROM allPoints where ID='"+ Decrypter.encrypt(id) + "' and time>=10 and time<110";
            InfluxDBReader.QueryGenerator queryGenerator = new InfluxDBReader.QueryGenerator(id, beginning, ending, field).invoke();

            assertEquals(queryGenerator.getQuery(),queryResult);
            assertEquals(queryGenerator.getCl(), Ecg1Point.class);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void generateQueryAccelero() {
        String id="a";
        long beginning=10;
        long ending=110;

        InfluxField field= ACCELERO;

        try {
            String queryResult="SELECT acceleroX,acceleroY,acceleroZ,time FROM allPoints where ID='"+ Decrypter.encrypt(id) + "' and time>=10 and time<110";
            InfluxDBReader.QueryGenerator queryGenerator = new InfluxDBReader.QueryGenerator(id, beginning, ending, field).invoke();

            assertEquals(queryGenerator.getQuery(),queryResult);
            assertEquals(queryGenerator.getCl(), AcceleroPoint.class);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void generateQueryGyro() {
        String id="a";
        long beginning=10;
        long ending=110;

        InfluxField field= GYRO;

        try {
            String queryResult="SELECT GyrosX,GyrosY,GyrozZ,time FROM allPoints where ID='"+ Decrypter.encrypt(id) + "' and time>=10 and time<110";
            InfluxDBReader.QueryGenerator queryGenerator = new InfluxDBReader.QueryGenerator(id, beginning, ending, field).invoke();

            assertEquals(queryGenerator.getQuery(),queryResult);
            assertEquals(queryGenerator.getCl(), GyroPoint.class);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void generateQueryMagneto() {
        String id="a";
        long beginning=10;
        long ending=110;

        InfluxField field= MAGNETO;

        try {
            String queryResult="SELECT magnetoX,magnetoY,magnetoZ,time FROM allPoints where ID='"+ Decrypter.encrypt(id) + "' and time>=10 and time<110";
            InfluxDBReader.QueryGenerator queryGenerator = new InfluxDBReader.QueryGenerator(id, beginning, ending, field).invoke();

            assertEquals(queryGenerator.getQuery(),queryResult);
            assertEquals(queryGenerator.getCl(), MagnetoPoint.class);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void generateQueryRespi() {
        String id="a";
        long beginning=10;
        long ending=110;

        InfluxField field= RESPI;

        try {
            String queryResult="SELECT respiAbdominal,respiThorax,time FROM allPoints where ID='"+ Decrypter.encrypt(id) + "' and time>=10 and time<110";
            InfluxDBReader.QueryGenerator queryGenerator = new InfluxDBReader.QueryGenerator(id, beginning, ending, field).invoke();

            assertEquals(queryGenerator.getQuery(),queryResult);
            assertEquals(queryGenerator.getCl(), RespiPoint.class);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void generateQuerySpo2() {
        String id="a";
        long beginning=10;
        long ending=110;

        InfluxField field= SPO2;

        try {
            String queryResult="SELECT \"Spo2Chan1-1\",time FROM allPoints where ID='"+ Decrypter.encrypt(id) + "' and time>=10 and time<110";
            InfluxDBReader.QueryGenerator queryGenerator = new InfluxDBReader.QueryGenerator(id, beginning, ending, field).invoke();

            assertEquals(queryGenerator.getQuery(),queryResult);
            assertEquals(queryGenerator.getCl(), Spo2Point.class);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void generateQueryTemp() {
        String id="a";
        long beginning=10;
        long ending=110;

        InfluxField field= TEMP;

        try {
            String queryResult="SELECT temp,time FROM allPoints where ID='"+ Decrypter.encrypt(id) + "' and time>=10 and time<110";
            InfluxDBReader.QueryGenerator queryGenerator = new InfluxDBReader.QueryGenerator(id, beginning, ending, field).invoke();

            assertEquals(queryGenerator.getQuery(),queryResult);
            assertEquals(queryGenerator.getCl(), TempPoint.class);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }



}