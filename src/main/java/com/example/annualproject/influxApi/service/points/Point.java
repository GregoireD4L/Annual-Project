package com.example.annualproject.influxApi.service.points;

import java.time.Instant;
/***
 *  Created by Nicolas Sirac
 ****/
public interface Point {
    public long getLongtime();

    public void setLongtime(Instant longtime);

    public Instant getTime();


}
