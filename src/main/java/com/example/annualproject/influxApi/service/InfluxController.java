package com.example.annualproject.influxApi.service;

import com.example.annualproject.influxApi.EcgChannelOnePoint;
import com.example.annualproject.influxApi.InfluxDBReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/ecg1")

public class InfluxController {

        private final InfluxDBReader influxDBReader;

        @Autowired
        public InfluxController(InfluxDBReader influxDBReader) {
            this.influxDBReader=influxDBReader;
        }

        @RequestMapping(value = "/getPoints", method = RequestMethod.GET)
        public List<EcgChannelOnePoint> getPoints() throws Exception {
            System.out.println("in");
            return influxDBReader.readECGChannel1();

            //  return "redirect:/fichier";

        }


    }
