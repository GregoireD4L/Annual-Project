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

    @GetMapping(value = "/getECG1Past")
    public List<Ecg1Point> getPointsBetweenTime(@RequestParam String id, @RequestParam Instant beginning, @RequestParam Instant ending ) throws Exception {
        return influxDBReader.readECGChannel1BeetweenTime(id, beginning,ending);
    }
    @GetMapping(value = "/getECG1PastMilli")
    public List<Ecg1Point> getPointsBetweenTimeMilli(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending ) throws Exception {
        return influxDBReader.readECGChannel1BeetweenTime(id, beginning,ending);


    }
    @GetMapping(value = "/getECG1")
    public List<Ecg1Point> getPoints(@RequestParam String id, @RequestParam long time) throws Exception {
        return influxDBReader.readECGChannel1(id,time);

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
        List<Spo2Point_1> spo2Point1s =influxDBReader.readSpo2BeetweenTime(id,beginning,ending);
        List<Spo2Point_1> toReturn = new ArrayList<>();
        int cpt=0;
        for(Spo2Point_1 ap: spo2Point1s){
            if(cpt%10==0){
                toReturn.add(ap);
            }
            cpt++;
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
