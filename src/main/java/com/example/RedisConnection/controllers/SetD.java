package com.example.RedisConnection.controllers;

import com.example.RedisConnection.POJO.SetPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/set")
public class SetD<S> {
    @Autowired
    private RedisTemplate<String, String> template;
    final Logger logger = LoggerFactory.getLogger(SetD.class);
    @PostMapping()
    public String addToSet(@RequestBody SetPOJO setPOJO) {
        logger.info("New Set-Value about to be saved");
        template.opsForSet().add(setPOJO.getKey(), setPOJO.getValue());
        if(setPOJO.getTimeDuration() != null)
            template.expire(setPOJO.getKey(),setPOJO.getTimeDuration(), TimeUnit.valueOf(setPOJO.getUnitOfTime()));
        logger.info("Set-Value saved");
        return "Added";
    }
    @GetMapping()
    public Set<String> getSetValues(@RequestBody SetPOJO setPOJO) {
        logger.info("Retrieving the Values");
        Set<String> values = template.opsForSet().members(setPOJO.getKey());
        logger.info("Values Retrieved");
        return values;
    }

    @DeleteMapping
    public String removeFromSet(@RequestBody SetPOJO setPOJO) {
        logger.info("Process of Set Value Deletion Begins");
        template.opsForSet().remove(setPOJO.getKey(), setPOJO.getValue());
        logger.info("Set->Value Deleted");
        return "Removed";
    }

    @GetMapping("/size")
    public Long getSetSize(@RequestBody SetPOJO setPOJO) {
        logger.info("Retrieving the Size");
        Long size = template.opsForSet().size(setPOJO.getKey());
        logger.info("Size Retrieved");
        return size;
    }
}