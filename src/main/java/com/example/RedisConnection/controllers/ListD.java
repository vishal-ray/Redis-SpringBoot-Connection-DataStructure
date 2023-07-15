package com.example.RedisConnection.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ListD {
    private final RedisTemplate<String, String> redisTemplate;
    private final ListOperations<String,String> listOperations;
    Logger logger = LoggerFactory.getLogger(ListD.class);
    @Autowired
    public ListD(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.listOperations = redisTemplate.opsForList();
    }

    @GetMapping("/index/{index}")
    public String getByIndex(@RequestParam String key, @RequestParam long index) {
        logger.info("Inside getByIndex");
        logger.info("getByIndex executed");
        return (String) listOperations.index(key, index);
    }

    @PostMapping("/left")
    public java.lang.String saveLeft(@RequestParam String key, @RequestParam String value)
    {
        logger.info("Inside getByIndex");
        listOperations.leftPush(key,value);
        logger.info("getByIndex executed");
        return key + " = " + value + " Saved";
    }
    @PostMapping("/right")
    public java.lang.String saveRight(@RequestParam String key, @RequestParam String value)
    {
        logger.info("Inside getByIndex");
        listOperations.rightPush(key,value);
        logger.info("getByIndex executed");
        return key + " = " + value + " Saved";
    }

    @DeleteMapping("/remove")
    public void removeValue(@RequestParam String key, @RequestParam String value) {
        logger.info("Inside getByIndex");
        listOperations.remove(key, 0, value);
        logger.info("getByIndex executed");
    }
    @DeleteMapping("/leftPop")
    public String popFromLeft(@RequestParam String key) {
        logger.info("Inside getByIndex");
        logger.info("getByIndex executed");
        return (String) listOperations.leftPop(key);
    }
    @DeleteMapping("/rightPop")
    public String popFromRight(@RequestParam String key) {
        logger.info("Inside getByIndex");
        logger.info("getByIndex executed");
        return (String) listOperations.rightPop(key);
    }
}
