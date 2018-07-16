package com.example.annualproject.influxApi.service.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
/***
 *  Created by Nicolas Sirac
 ****/
public class InfluxDBSingleton {
    private static InfluxDB instance = InfluxDBFactory.connect("http://51.38.185.205:8086", "dataforlife", "dataforlife2018");

    public static InfluxDB getInstance() {
        return instance;
    }
}
