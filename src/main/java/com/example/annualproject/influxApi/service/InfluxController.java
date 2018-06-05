package com.example.annualproject.influxApi.service;

import com.example.annualproject.influxApi.InfluxPoint;
import com.example.annualproject.influxApi.InfluxDBReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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

    @GetMapping(value = "/getPointsPast")
    public List<InfluxPoint> getPointsBetweenTime(@RequestParam String id, @RequestParam Instant beginning, @RequestParam Instant ending ) throws Exception {
        return influxDBReader.readPointsBeetweenTime(id, beginning,ending);

        //  return "redirect:/fichier";

    }
    @GetMapping(value = "/getPointsPastMilli")
    public List<InfluxPoint> getPointsBetweenTimeMilli(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending ) throws Exception {
        return influxDBReader.readPointsBeetweenTime(id, beginning,ending);

        //  return "redirect:/fichier";

    }
    @GetMapping(value = "/getPoints")
    public List<InfluxPoint> getPoints(@RequestParam String id) throws Exception {
        return influxDBReader.readPoints(id);

        //  return "redirect:/fichier";

    }


}
