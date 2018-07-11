package com.example.annualproject.influxApi.service;

import com.example.annualproject.influxApi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/data")
@RestController
public class InfluxController {

    private final InfluxDBReader influxDBReader;

    @Autowired
    public InfluxController(InfluxDBReader influxDBReader) {
        this.influxDBReader=influxDBReader;
    }


    @GetMapping(value = "/getECG1PastMilli")
    public List<Ecg1Point> getPointsBetweenTimeMilli(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending ) throws Exception {
        List<Ecg1Point> ecgPoints=influxDBReader.readECGChannel1BeetweenTime(id,beginning,ending);
        List<Ecg1Point> toReturn = new ArrayList<>();
        if(ecgPoints.size()>0) {
            Ecg1Point ecgPoint = ecgPoints.get(0);
            toReturn.add(ecgPoint);
            for (Ecg1Point rp : ecgPoints) {
                if (!rp.equals(ecgPoint)) {
                    toReturn.add(rp);
                }
                ecgPoint = rp;
            }
        }
        return toReturn;

    }
    

    @GetMapping(value = "/getRespiPastMilli")
    public List<RespiPoint> getRespiPointsBetweenTime(@RequestParam String id,@RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<RespiPoint> respiPoints=influxDBReader.readRespiBeetweenTime(id,beginning,ending);
        List<RespiPoint> toReturn = new ArrayList<>();
        RespiPoint respiPoint=respiPoints.get(0);
        toReturn.add(respiPoint);
        for(RespiPoint rp:respiPoints){
            if(!rp.equals(respiPoint)){
                toReturn.add(rp);
            }
            respiPoint=rp;
        }
        return toReturn;
    }
    @GetMapping(value = "/getSpo2PastMilli")
    public List<Spo2Point_1> getSpo2BetweenTimeMilli(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending ) throws Exception {
        List<Spo2Point_1> spo2Points =influxDBReader.readSpo2BeetweenTime(id,beginning,ending);
        List<Spo2Point_1> toReturn = new ArrayList<>();
        Spo2Point_1 spo2Point_1= spo2Points.get(0);
        toReturn.add(spo2Point_1);
        for(Spo2Point_1 sp:spo2Points){
            if(!sp.equals(spo2Point_1)){
                toReturn.add(sp);
            }
            spo2Point_1=sp;
        }
        return toReturn;


    }
    @GetMapping(value = "/getAcceleroPastMilli")
    public List<AcceleroPoint> getAcceleroPointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<AcceleroPoint> acceleroPoints=influxDBReader.readAcceleroChannel1(id,beginning,ending);
        List<AcceleroPoint> toReturn = new ArrayList<>();
        int cpt=0;
        for(AcceleroPoint ap:acceleroPoints){
            if(cpt%10==0){
                toReturn.add(ap);
            }
            cpt++;
        }
        return toReturn;

    }
    @GetMapping(value = "/getTempPastMilli")
    public List<TempPoint> getTempPointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<TempPoint> tempPoints=influxDBReader.readTemp(id,beginning,ending);
        List<TempPoint> toReturn = new ArrayList<>();
        int cpt=0;
        for(TempPoint ap:tempPoints){
            if(cpt%10==0){
                toReturn.add(ap);
            }
            cpt++;
        }
        return toReturn;

    }



}
