package com.example.RedisConnection.controllers;

import com.example.RedisConnection.POJO.HashPOJO;
import com.example.RedisConnection.POJO.HyperLogLogPOJO;
import com.example.RedisConnection.configuration.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hyperLogLog")
public class HyperLogLogD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(HyperLogLogD.class);
    @PostMapping
    public String save(@RequestBody HyperLogLogPOJO hyperLogLogPOJO)
    {
        logger.info("New HyperLogLog-Values about to be saved");
        template.opsForHyperLogLog().add(hyperLogLogPOJO.getKey(),hyperLogLogPOJO.getValues());
        if(hyperLogLogPOJO.getTimeDuration() != null)
            template.expire(hyperLogLogPOJO.getKey(),hyperLogLogPOJO.getTimeDuration(), TimeUnit.valueOf(hyperLogLogPOJO.getUnitOfTime()));
        logger.info("HyperLogLog-Value saved");
        return "Values added Successfully";
    }
    @GetMapping("/keyCardinality")
    public Long getValue(@RequestBody HashPOJO hashPOJO)
    {
        logger.info("Retrieving the Cardinality");
        logger.info("Cardinality Retrieved");
        return template.opsForHyperLogLog().size(hashPOJO.getKey());
    }
    @DeleteMapping
    public String deleteKey(@RequestBody HashPOJO hashPOJO)
    {
        logger.info("Process of HyperLogLog Variable Deletion Begins");
        template.opsForHyperLogLog().delete(hashPOJO.getKey());
        logger.info("HyperLogLog Variable Deleted");
        return hashPOJO.getKey() + " Fields Deleted";
    }
}