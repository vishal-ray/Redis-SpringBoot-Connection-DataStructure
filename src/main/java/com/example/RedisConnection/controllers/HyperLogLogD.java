package com.example.RedisConnection.controllers;

import com.example.RedisConnection.configuration.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hyperLogLog")
public class HyperLogLogD {
    @Autowired
    private RedisTemplate template;
    Logger logger = LoggerFactory.getLogger(HyperLogLogD.class);
    @PostMapping
    public String save(@RequestParam String key, @RequestParam String[] values)
    {
        logger.info("Inside save");
        template.opsForHyperLogLog().add(key,values);
        logger.info("Save executed");
        return "Values added Successfully";
    }
    @GetMapping("/keyCardinality")
    public Long getValue(@RequestParam String key)
    {
        logger.info("Inside getValue");
        logger.info("getValue executed");
        return template.opsForHyperLogLog().size(key);
    }
    @DeleteMapping
    public java.lang.String deleteKey(@RequestParam String key)
    {
        logger.info("Inside deleteKey");
        template.opsForHyperLogLog().delete(key);
        logger.info("deleteKey executed");
        return key + " Fields Deleted";
    }
}