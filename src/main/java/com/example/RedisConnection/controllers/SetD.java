package com.example.RedisConnection.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/set")
public class SetD<S> {
    @Autowired
    private RedisTemplate template;
    Logger logger = LoggerFactory.getLogger(SetD.class);
    @PostMapping()
    public java.lang.String addToSet(@RequestParam String key, @RequestParam String value) {
        logger.info("Inside addToSet");
        template.opsForSet().add(key, value);
        logger.info("addToSet executed");
        return "Added";
    }
    @GetMapping()
    public Set getSetValues(@RequestParam String key) {
        logger.info("Inside getSetValues");
        java.util.Set values = template.opsForSet().members(key);
        logger.info("getSetValues executed");
        return  values;
    }

    @DeleteMapping
    public java.lang.String removeFromSet(@RequestParam String key, @RequestParam String value) {
        logger.info("Inside removeFromSet");
        template.opsForSet().remove(key, value);
        logger.info("removeFromSet executed");
        return "Removed";
    }

    @GetMapping("/size")
    public Long getSetSize(@RequestParam String key) {
        logger.info("Inside getSetSize");
        Long size = template.opsForSet().size(key);
        logger.info("getSetSize executed");
        return size;
    }
}