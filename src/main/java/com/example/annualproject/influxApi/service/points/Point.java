package com.example.annualproject.influxApi.service.points;

import java.time.Instant;

public interface Point {
    public long getLongtime();

    public void setLongtime(Instant longtime);

    public Instant getTime();


}
