package com.example.RedisConnection.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.lang.String;

@RestController
@RequestMapping("/hash")
public class HashD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(HashD.class);
    @PostMapping
    public String save(@RequestParam String key, @RequestParam String field, @RequestParam String value)
    {
        logger.info("Inside save");
        template.opsForHash().put(key, field, value);
        logger.info("Save executed");
        return field + " = " + value + " Saved in " + key + " Hash" ;
    }
    @GetMapping("/{field}")
    public String getValue(@RequestParam String key, @PathVariable String field)
    {
        logger.info("Inside save");
        logger.info("Save executed");
        return (String) template.opsForHash().get(key, field);
    }
    @GetMapping
    public Map<String, String> getValue(@RequestParam String key)
    {
        logger.info("Inside save");
        logger.info("Save executed");
        return template.opsForHash().entries(key);
    }
    @DeleteMapping("/{field}")
    public String deleteKey(@RequestParam String key, @PathVariable String field)
    {
        logger.info("Inside save");
        logger.info("Save executed");
        return template.opsForHash().delete(key, field) + " Fields Deleted";
    }
}
