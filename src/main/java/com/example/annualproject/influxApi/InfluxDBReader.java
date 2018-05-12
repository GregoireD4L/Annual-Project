package com.example.annualproject.influxApi;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;

public class InfluxDBReader {
    public void readECGCHannel1(){
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName="dataforlifeDB";
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM ecgChannelOne", dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<EcgChannelOnePoint> cpuList = resultMapper.toPOJO(queryResult,EcgChannelOnePoint.class);
        System.out.println("in");
    }
}
