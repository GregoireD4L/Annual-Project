package com.example.annualproject.influxApi.service.influx;

import com.example.annualproject.influxApi.service.points.*;
import com.example.annualproject.security.Decrypter;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.example.annualproject.influxApi.service.points.InfluxField.*;
/***
 *  Created by Nicolas Sirac
 ****/
@Service
public class InfluxDBReader {


    public List<Point> readPointBeetweenTime(String id, long beginning, long ending, InfluxField field) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        QueryGenerator queryGenerator = new QueryGenerator(id, beginning, ending, field).invoke();
        String query = queryGenerator.getQuery();
        Class cl = queryGenerator.getCl();
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Point> points = resultMapper.toPOJO(queryResult, cl);
        for (Point point : points) {
            point.setLongtime(point.getTime());

        }
        return points;


    }




    /** Inner Class that generate query
     *
     */
    public static class QueryGenerator {
        private String id;
        private long beginning;
        private long ending;
        private InfluxField field;
        private String query;
        private Class cl;

        public QueryGenerator(String id, long beginning, long ending, InfluxField field) {
            this.id = id;
            this.beginning = beginning;
            this.ending = ending;
            this.field = field;
        }

        public String getQuery() {
            return query;
        }

        public Class getCl() {
            return cl;
        }

        /**
         * Generate query
         *
         */
        public QueryGenerator invoke() {
            query = null;
            cl = Point.class;

            try {
                if (field == ECG) {

                    query = "SELECT ecg1,time FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and time>=" + beginning + " and time<" + ending;

                    cl = Ecg1Point.class;
                } else if (field == SPO2) {
                    query = "SELECT \"Spo2Chan1-1\",time FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and time>=" + beginning + " and time<" + ending;
                    cl = Spo2Point.class;
                } else if (field == ACCELERO) {
                    query = "SELECT acceleroX,acceleroY,acceleroZ,time FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and time>=" + beginning + " and time<" + ending;
                    cl = AcceleroPoint.class;
                } else if (field == GYRO) {
                    query = "SELECT GyrosX,GyrosY,GyrozZ,time FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and time>=" + beginning + " and time<" + ending;
                    cl = GyroPoint.class;
                } else if (field == MAGNETO) {
                    query = "SELECT magnetoX,magnetoY,magnetoZ,time FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and time>=" + beginning + " and time<" + ending;
                    cl = MagnetoPoint.class;
                } else if (field == RESPI) {
                    query = "SELECT respiAbdominal,respiThorax,time FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and time>=" + beginning + " and time<" + ending;
                    cl = RespiPoint.class;
                } else if (field == TEMP) {
                    query = "SELECT temp,time FROM allPoints where ID='" + Decrypter.encrypt(id) + "' and time>=" + beginning + " and time<" + ending;

                    cl = TempPoint.class;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return this;
        }
    }
}