package com.example.annualproject.influxApi.service.points;

import java.time.Instant;

public interface Point {
    public long getLongtime();
    public Instant getTime();
    public void setLongtime(Instant longtime);


}
