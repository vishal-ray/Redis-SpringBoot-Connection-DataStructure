package com.example.RedisConnection.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/string")
public class StringD {
    @Autowired
    private RedisTemplate template;
    Logger logger = LoggerFactory.getLogger(StringD.class);
    @PostMapping
    public java.lang.String save(@RequestParam java.lang.String key, @RequestParam java.lang.String value)
    {
        logger.info("Inside save");
        template.opsForValue().set(key,value);
        logger.info("Save executed");
        return key + " = " + value + " Saved";
    }
    @GetMapping
    public java.lang.String getValue(@RequestParam java.lang.String key)
    {
        logger.info("Inside getValue");
        logger.info("getValue executed");
        return (java.lang.String) template.opsForValue().get(key);
    }
    @DeleteMapping
    public java.lang.String deleteKey(@RequestParam java.lang.String key)
    {
        logger.info("Inside deleteKey");
        logger.info("deleteKey executed");
        return (java.lang.String) template.opsForValue().getAndDelete(key) + " Deleted";
    }
    @PutMapping
    public java.lang.String updateKeyValue(@RequestParam java.lang.String key, @RequestParam java.lang.String value)
    {
        logger.info("Inside updateKeyValue");
        template.opsForValue().setIfPresent(key,value);
        logger.info("updateKeyValue executed");
        return "Updated Value : Key = " + template.opsForValue().get(key);
    }
}
