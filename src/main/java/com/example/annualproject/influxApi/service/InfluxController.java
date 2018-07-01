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
        int cpt=0;
        for(RespiPoint ap:respiPoints){
            if(cpt%10==0){
                toReturn.add(ap);
            }
            cpt++;
        }
        return toReturn;
    }
    @GetMapping(value = "/getSpo2PastMilli")
    public List<Spo2Point> getSpo2BetweenTimeMilli(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending ) throws Exception {
        List<Spo2Point> spo2Points=influxDBReader.readSpo2BeetweenTime(id,beginning,ending);
        List<Spo2Point> toReturn = new ArrayList<>();
        int cpt=0;
        for(Spo2Point ap:spo2Points){
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
    @GetMapping(value = "/getAccelero2PastMilli")
    public List<Accelero2Point> getAccelero2PointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Accelero2Point> acceleroPoints=influxDBReader.readAcceleroChannel2(id,beginning,ending);
        List<Accelero2Point> toReturn = new ArrayList<>();
        int cpt=0;
        for(Accelero2Point ap:acceleroPoints){
            if(cpt%10==0){
                toReturn.add(ap);
            }
            cpt++;
        }
        return toReturn;

    }


}
