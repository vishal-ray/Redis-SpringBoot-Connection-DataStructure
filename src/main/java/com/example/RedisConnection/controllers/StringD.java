package com.example.RedisConnection.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;

@RestController
@RequestMapping("/string")
public class StringD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(StringD.class);
    @PostMapping
    public String save(@RequestParam String key, @RequestParam String value)
    {
        logger.info("Inside save");
        template.opsForValue().set(key,value);
        logger.info("Save executed");
        return key + " = " + value + " Saved";
    }
    @GetMapping
    public String getValue(@RequestParam String key)
    {
        logger.info("Inside getValue");
        logger.info("getValue executed");
        return (String) template.opsForValue().get(key);
    }
    @DeleteMapping
    public String deleteKey(@RequestParam String key)
    {
        logger.info("Inside deleteKey");
        logger.info("deleteKey executed");
        return (String) template.opsForValue().getAndDelete(key) + " Deleted";
    }
    @PutMapping
    public String updateKeyValue(@RequestParam String key, @RequestParam String value)
    {
        logger.info("Inside updateKeyValue");
        template.opsForValue().setIfPresent(key,value);
        logger.info("updateKeyValue executed");
        return "Updated Value : Key = " + template.opsForValue().get(key);
    }
}
