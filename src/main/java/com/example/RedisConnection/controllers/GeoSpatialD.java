package com.example.RedisConnection.controllers;

import com.example.RedisConnection.POJO.GeoSpatialPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/geoSpatial")
public class GeoSpatialD {

    final Logger logger = LoggerFactory.getLogger(GeoSpatialD.class);
    @Autowired
    private RedisTemplate template;

    @PostMapping
    public String addGeoLocation(@RequestBody GeoSpatialPOJO geoSpatialPOJO){
        logger.info("New GeoSpatial-Values about to be saved");
        Point point = new Point(geoSpatialPOJO.getLongitude(), geoSpatialPOJO.getLatitude());
        template.opsForGeo().add(geoSpatialPOJO.getKey(), point, geoSpatialPOJO.getMember1());
        if(geoSpatialPOJO.getTimeDuration() != null)
            template.expire(geoSpatialPOJO.getKey(),geoSpatialPOJO.getTimeDuration(), TimeUnit.valueOf(geoSpatialPOJO.getUnitOfTime()));
        logger.info("GeoSpatial-Value saved");
        return "Added";
    }
    @GetMapping("/inBetweenDistance")
    public Distance calculateDistance(@RequestBody GeoSpatialPOJO geoSpatialPOJO) {
        logger.info("Retrieving the In Between Distance");
        logger.info("In Between Distance Retrieved");
        return template.opsForGeo().distance(geoSpatialPOJO.getKey(), geoSpatialPOJO.getMember1(), geoSpatialPOJO.getMember2());
    }
    @DeleteMapping()
    public String removeGeoLocation(@RequestBody GeoSpatialPOJO geoSpatialPOJO) {
        logger.info("Process of GeoSpatial Variable->Members Deletion Begins");

        logger.info("GeoSpatial Variable->Members Deleted");
        template.opsForGeo().remove(geoSpatialPOJO.getKey(), geoSpatialPOJO.getMembers());
        return "Deleted";
    }
}
