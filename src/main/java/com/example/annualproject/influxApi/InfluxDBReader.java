package com.example.annualproject.influxApi;

import com.example.annualproject.security.Decrypter;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class InfluxDBReader {
    public List<Ecg1Point> readECGChannel1(String id, long time ) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";

        long instant = Instant.now().minusMillis(time).toEpochMilli();
        QueryResult queryResult = null;

        try {
            String query="SELECT ecg1, timestamp FROM allPoints where ID='" +  Decrypter.encrypt(id) + "' and timestamp>" + instant+" and timestamp<"+(instant+time);
            System.out.println(query);
            System.out.println(time);
            //queryResult = influxDB.query(new Query("SELECT * FROM allPoints where ID=" +  Decrypter.decrypt(id.getBytes()) + "' and time" + instant, dbName));
            queryResult = influxDB.query(new Query(query, dbName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Ecg1Point> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, Ecg1Point.class);

        return ecgChannelOnePoints;
    }

    public List<Ecg1Point> readECGChannel1BeetweenTime(String id, Instant beginning, Instant ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
        //    query = "SELECT * FROM allPoints where ID='" + Decrypter.decrypt(id.getBytes()) + "' and timestamp>=" + beginning+" and time<"+ending;
            query = "SELECT * FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and timestamp>" + beginning+" and timestamp<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Ecg1Point> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, Ecg1Point.class);

        return ecgChannelOnePoints;
    }

    public List<Ecg1Point> readECGChannel1BeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
        //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT ecg1,timestamp FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and timestamp>" + beginning+" and timestamp<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Ecg1Point> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, Ecg1Point.class);

        return ecgChannelOnePoints;
    }

    public List<RespiPoint> readRespiBeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT respiAbdominal,respiThorax,timestamp FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and timestamp>" + beginning+" and timestamp<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<RespiPoint> respiPoints = resultMapper.toPOJO(queryResult, RespiPoint.class);

        return respiPoints;
    }

    public List<Spo2Point_1> readSpo2BeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT Spo2Chan1-1,Spo2Chan1-2,timestamp FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and timestamp>" + beginning+" and timestamp<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Spo2Point_1> spo2Points = resultMapper.toPOJO(queryResult, Spo2Point_1.class);

        return spo2Points;
    }

    public List<AcceleroPoint> readAcceleroChannel1(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT acceleroX,acceleroY,acceleroZ,timestamp FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and timestamp>" + beginning+" and timestamp<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<AcceleroPoint> acceleroPoints = resultMapper.toPOJO(queryResult, AcceleroPoint.class);

        return acceleroPoints;
    }






    public List<TempPoint> getTempPoints(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT temp,timestamp FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and timestamp>" + beginning+" and timestamp<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, TempPoint.class);
    }
}