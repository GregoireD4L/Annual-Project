package com.example.annualproject.influxApi;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class InfluxDBReader {
    public List<EcgChannelOnePoint> readECGChannel1(){
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName="dataforlifeDB";
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM ecgChannelOne", dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<EcgChannelOnePoint> ecgChannelOnePoints = resultMapper.toPOJO(queryResult,EcgChannelOnePoint.class);
       return ecgChannelOnePoints;
    }
}
