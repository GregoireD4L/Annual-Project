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
    public List<InfluxPoint> readPoints(String id) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        long instant = Instant.now().minusSeconds(1).toEpochMilli();
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM allPoints where ID=" + id + "' and timestamp>=" + instant, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<InfluxPoint> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, InfluxPoint.class);
        for(InfluxPoint point : ecgChannelOnePoints)
            point.setLongtime(point.getTime());
        return ecgChannelOnePoints;
    }

    public List<InfluxPoint> readPointsBeetweenTime(String id, Instant beginning, Instant ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query ="SELECT * FROM allPoints where ID='" + id + "' and timestamp>=" + beginning+" and timestamp<="+ending;
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<InfluxPoint> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, InfluxPoint.class);
        for(InfluxPoint point : ecgChannelOnePoints)
            point.setLongtime(point.getTime());
        return ecgChannelOnePoints;
    }

    public List<InfluxPoint> readPointsBeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query ="SELECT * FROM allPoints where ID='" + id + "' and timestamp>=" + beginning+" and timestamp<="+ending;
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<InfluxPoint> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, InfluxPoint.class);
        for(InfluxPoint point : ecgChannelOnePoints)
            point.setLongtime(point.getTime());
        return ecgChannelOnePoints;
    }
}