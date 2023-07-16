package com.example.RedisConnection.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;

@RestController
@RequestMapping("/list")
public class ListD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(ListD.class);

    @GetMapping("/index/{index}")
    public String getByIndex(@RequestParam String key, @RequestParam long index) {
        logger.info("Inside getByIndex");
        logger.info("getByIndex executed");
        return (String) template.opsForList().index(key,index);
    }

    @PostMapping("/left")
    public String saveLeft(@RequestParam String key, @RequestParam String value)
    {
        logger.info("Inside getByIndex");
        template.opsForList().leftPush(key,value);
        logger.info("getByIndex executed");
        return key + " = " + value + " Saved";
    }
    @PostMapping("/right")
    public String saveRight(@RequestParam String key, @RequestParam String value)
    {
        logger.info("Inside getByIndex");
        template.opsForList().rightPush(key,value);
        logger.info("getByIndex executed");
        return key + " = " + value + " Saved";
    }

    @DeleteMapping("/remove")
    public void removeValue(@RequestParam String key, @RequestParam String value) {
        logger.info("Inside getByIndex");
        template.opsForList().remove(key, 0, value);
        logger.info("getByIndex executed");
    }
    @DeleteMapping("/leftPop")
    public String popFromLeft(@RequestParam String key) {
        logger.info("Inside getByIndex");
        logger.info("getByIndex executed");
        return (String) template.opsForList().leftPop(key);
    }
    @DeleteMapping("/rightPop")
    public String popFromRight(@RequestParam String key) {
        logger.info("Inside getByIndex");
        logger.info("getByIndex executed");
        return (String) template.opsForList().rightPop(key);
    }
}
