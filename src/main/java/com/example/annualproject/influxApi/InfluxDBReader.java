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
    public List<EcgChannelOnePoint> readECGChannel1(String id) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        Instant instant = Instant.now().minusSeconds(1);
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM ecgChannelOne where idUser=" + id + " and time>='" + instant+"'", dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<EcgChannelOnePoint> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, EcgChannelOnePoint.class);
        return ecgChannelOnePoints;
    }

    public List<EcgChannelOnePoint> readECGChannel1BeetweenTime(String id, Instant beginning, Instant ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        //String query ="SELECT * FROM ecgChannelOne where idUser='" + id + "' and time>='" + beginning+"' and time<='"+ending+"'";
        String query ="SELECT * FROM ecgChannelOne";/*WHERE idUser=\"1\" and time>='2018-01-01T00:00:00Z' and time<='2019-01-01T00:00:00Z'";*/
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<EcgChannelOnePoint> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, EcgChannelOnePoint.class);
        return ecgChannelOnePoints;
    }
}
