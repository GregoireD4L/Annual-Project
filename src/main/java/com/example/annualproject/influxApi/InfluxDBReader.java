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
        for(Ecg1Point point : ecgChannelOnePoints) {
            point.setLongtime(point.getTime());
            try {
                point.setIdUser(Decrypter.encrypt(point.getIdUser()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        for(Ecg1Point point : ecgChannelOnePoints) {
            point.setLongtime(point.getTime());
            try {
                point.setIdUser(Decrypter.decrypt(point.getIdUser().getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        for(Ecg1Point point : ecgChannelOnePoints) {
            point.setLongtime(point.getTime());
            try {
                point.setIdUser(Decrypter.encrypt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ecgChannelOnePoints;
    }
}