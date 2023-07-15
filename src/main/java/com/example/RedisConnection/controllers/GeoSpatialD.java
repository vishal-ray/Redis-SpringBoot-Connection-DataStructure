package com.example.RedisConnection.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/geoSpatial")
public class GeoSpatialD {

    Logger logger = LoggerFactory.getLogger(GeoSpatialD.class);
    @Autowired
    private RedisTemplate template;

    @PostMapping
    public java.lang.String addGeoLocation(@RequestParam java.lang.String key, @RequestParam double longitude, @RequestParam double latitude, @RequestParam String member) {
        logger.info("Inside addGeoLocation");
        Point point = new Point(longitude, latitude);
        template.opsForGeo().add(key, point, member);
        logger.info("addGeoLocation executed");
        return "Added";
    }
    @GetMapping("/inBetweenDistance")
    public Distance calculateDistance(@RequestParam String  key, @RequestParam String member1, @RequestParam String member2) {
        logger.info("Inside inBetweenDistance");

        logger.info("inBetweenDistance executed");
        return template.opsForGeo().distance(key, member1, member2);
    }
    @DeleteMapping()
    public void removeGeoLocation(@RequestParam String key, @RequestParam String[] members) {
        logger.info("Inside removeGeoLocation");

        logger.info("removeGeoLocation executed");
        template.opsForGeo().remove(key, members);
    }
}
