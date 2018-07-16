package com.example.annualproject.influxApi.service.influx;

/***
 *  Created by Nicolas Sirac
 ****/

import com.example.annualproject.influxApi.service.points.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.annualproject.influxApi.service.points.InfluxField.*;

@CrossOrigin
@Controller
@RequestMapping("/data")
@RestController
public class InfluxController {

    private final InfluxDBReader influxDBReader;

    @Autowired
    public InfluxController(InfluxDBReader influxDBReader) {
        this.influxDBReader = influxDBReader;
    }

    /** GetPoints from ECG beetween 2 times in nano
     *
     * @param id
     * @param beginning
     * @param ending
     * @return list of result points
     * @throws Exception
     */

    @GetMapping(value = "/getECG1PastMilli")
    public List<Point> getPointsBetweenTimeMilli(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Point> points = influxDBReader.readPointBeetweenTime(id, beginning, ending, ECG);

        return points;

    }

    /** GetPoints from Respi beetween 2 times in nano
     *
      * @param id
     * @param beginning
     * @param ending
     * @return list of result points
     * @throws Exception
     */
    @GetMapping(value = "/getRespiPastMilli")
    public List<Point> getRespiPointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Point> points = influxDBReader.readPointBeetweenTime(id, beginning, ending, RESPI);

        return points;
    }

    /** GetPoints from SPo2 beetween 2 times in nano
     *
     * @param id
     * @param beginning
     * @param ending
     * @return list of result points
     * @throws Exception
     */
    @GetMapping(value = "/getSpo2PastMilli")
    public List<Point> getSpo2BetweenTimeMilli(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Point> points = influxDBReader.readPointBeetweenTime(id, beginning, ending, SPO2);

        return points;


    }

    /** GetPoints from Accelero beetween 2 times in nano
     *
     * @param id
     * @param beginning
     * @param ending
     * @return list of result points
     * @throws Exception
     */
    @GetMapping(value = "/getAcceleroPastMilli")
    public List<Point> getAcceleroPointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Point> points = influxDBReader.readPointBeetweenTime(id, beginning, ending, ACCELERO);

        return points;

    }

    /** GetPoints from Temperature beetween 2 times in nano
     *
     * @param id
     * @param beginning
     * @param ending
     * @return list of result points
     * @throws Exception
     */
    @GetMapping(value = "/getTempPastMilli")
    public List<Point> getTempPointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Point> points = influxDBReader.readPointBeetweenTime(id, beginning, ending, TEMP);

        return points;

    }

    /** GetPoints from Magneto beetween 2 times in nano
     *
     * @param id
     * @param beginning
     * @param ending
     * @return list of result points
     * @throws Exception
     */
    @GetMapping(value = "/getMagnetoPastMilli")
    public List<Point> getMagnetoPointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Point> points = influxDBReader.readPointBeetweenTime(id, beginning, ending, MAGNETO);

        return points;

    }

    /** GetPoints from Gyro
     * @param id
     * @param beginning
     * @param ending
     * @return list of result points
     * @throws Exception
     */
    @GetMapping(value = "/getGyroPastMilli")
    public List<Point> getGyroPointsBetweenTime(@RequestParam String id, @RequestParam long beginning, @RequestParam long ending) throws Exception {
        List<Point> points = influxDBReader.readPointBeetweenTime(id, beginning, ending, GYRO);

        return points;

    }


}
