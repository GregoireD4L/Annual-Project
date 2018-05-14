package com.example.annualproject.influxApi.service;

import com.example.annualproject.influxApi.EcgChannelOnePoint;
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

        @GetMapping(value = "/getECG1Past")
        public List<EcgChannelOnePoint> getPointsBetweenTime(@RequestParam String id, @RequestParam Instant beginning, @RequestParam Instant ending ) throws Exception {
            return influxDBReader.readECGChannel1BeetweenTime(id, beginning,ending);

            //  return "redirect:/fichier";

        }
    @GetMapping(value = "/getECG1")
    public List<EcgChannelOnePoint> getPoints(@RequestParam String id) throws Exception {
        return influxDBReader.readECGChannel1(id);

        //  return "redirect:/fichier";

    }


    }
