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




    public List<Ecg1Point> readECGChannel1BeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
        //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT ecg1,time FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and time>" + beginning+" and time<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Ecg1Point> ecgChannelOnePoints = resultMapper.toPOJO(queryResult, Ecg1Point.class);
        for(Ecg1Point ecg :ecgChannelOnePoints){
            ecg.setLongtime(ecg.getTime());
        }
        return ecgChannelOnePoints;
    }

    public List<RespiPoint> readRespiBeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT respiAbdominal,respiThorax,time FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and time>" + beginning+" and time<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<RespiPoint> respiPoints = resultMapper.toPOJO(queryResult, RespiPoint.class);
        for(RespiPoint point : respiPoints) {
            point.setLongtime(point.getTime());
            try {
                point.setIdUser(Decrypter.encrypt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return respiPoints;
    }

    public List<Spo2Point_1> readSpo2BeetweenTime(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT Spo2Chan1-1,Spo2Chan1-2,time FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and time>" + beginning+" and time<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Spo2Point_1> spo2Points = resultMapper.toPOJO(queryResult, Spo2Point_1.class);
        for(Spo2Point_1 point : spo2Points) {
            point.setLongtime(point.getTime());

        }
        return spo2Points;
    }

    public List<AcceleroPoint> readAcceleroChannel1(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT acceleroX,acceleroY,acceleroZ,time FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and time>" + beginning+" and time<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<AcceleroPoint> acceleroPoints = resultMapper.toPOJO(queryResult, AcceleroPoint.class);
        setUsers(id, acceleroPoints);
        return acceleroPoints;
    }


    private void setUsers(String id, List<AcceleroPoint> acceleroPoints) {
        for(AcceleroPoint point : acceleroPoints) {
            point.setLongtime(point.getTime());
            try {
                point.setIdUser(Decrypter.encrypt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<TempPoint> readTemp(String id, long beginning, long ending) {
        List<TempPoint> tempPoints = getTempPoints(id, beginning, ending);
        for(TempPoint point : tempPoints) {
            point.setLongtime(point.getTime());
            try {
                point.setIdUser(Decrypter.encrypt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tempPoints;
        
    }

    private List<TempPoint> getTempPoints(String id, long beginning, long ending) {
        InfluxDB influxDB = InfluxDBSingleton.getInstance();
        String dbName = "dataforlifeDB";
        String query = null;
        try {
            //    query = "SELECT * FROM allPoints where ID='" +  Decrypter.decrypt(id.getBytes())+ "' and time>=" + beginning+" and time<"+ending;
            query = "SELECT temp,time FROM allPoints where ID='" +  Decrypter.encrypt(id)+ "' and time>" + beginning+" and time<"+ending;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(query);
        QueryResult queryResult = influxDB.query(new Query(query, dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, TempPoint.class);
    }
}