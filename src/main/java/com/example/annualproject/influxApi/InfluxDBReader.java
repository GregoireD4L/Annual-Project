package com.example.annualproject.influxApi;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class InfluxDBReader {
    public List<Ecg1Point> readECGChannel1(String id) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        long instant = Instant.now().minusSeconds(1).toEpochMilli();
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM allPoints where ID=" + id + "' and time" + instant, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Ecg1Point> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, Ecg1Point.class);
        for(Ecg1Point point : ecgChannelOnePoints)
            point.setLongtime(point.getTime());
        return ecgChannelOnePoints;
    }

    public List<Ecg1Point> readECGChannel1BeetweenTime(String id, Instant beginning, Instant ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query ="SELECT * FROM allPoints where ID='" + id + "' and timestamp>=" + beginning+" and time<"+ending;
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Ecg1Point> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, Ecg1Point.class);
        for(Ecg1Point point : ecgChannelOnePoints)
            point.setLongtime(point.getTime());
        return ecgChannelOnePoints;
    }

    public List<Ecg1Point> readECGChannel1BeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query ="SELECT * FROM allPoints where ID='" + id + "' and time>=" + beginning+" and time<"+ending;
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Ecg1Point> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, Ecg1Point.class);
        for(Ecg1Point point : ecgChannelOnePoints)
            point.setLongtime(point.getTime());
        return ecgChannelOnePoints;
    }
}