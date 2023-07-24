package com.example.RedisConnection.controllers;

import com.example.RedisConnection.POJO.HashPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.lang.String;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hash")
public class HashD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(HashD.class);
    @PostMapping
    public String save(@RequestBody HashPOJO hashPOJO)
    {
        logger.info("New Hash-Values about to be saved");
        template.opsForHash().put(hashPOJO.getKey(), hashPOJO.getField(), hashPOJO.getValue());
        if(hashPOJO.getTimeDuration() != null)
            template.expire(hashPOJO.getKey(),hashPOJO.getTimeDuration(), TimeUnit.valueOf(hashPOJO.getUnitOfTime()));
        logger.info("Hash-Value saved");
        return hashPOJO.getField() + " = " + hashPOJO.getValue() + " Saved in " + hashPOJO.getKey() + " Hash" ;
    }
    @GetMapping("/{field}")
    public String getValue(@RequestBody HashPOJO hashPOJO)
    {
        logger.info("Retrieving the Values");
        logger.info("Values Retrieved");
        return (String) template.opsForHash().get(hashPOJO.getKey(), hashPOJO.getField());
    }
    @GetMapping
    public Map<String, String> getAllValue(@RequestBody HashPOJO hashPOJO)
    {
        logger.info("Retrieving All Values");
        logger.info("All Values retrieved");
        return template.opsForHash().entries(hashPOJO.getKey());
    }
    @DeleteMapping("/{field}")
    public String deleteKey(@RequestBody HashPOJO hashPOJO)
    {
        logger.info("Process of Hash Variable->Fields Deletion Begins");
        logger.info("Hash Variable->Fields Variable Deleted");
        return template.opsForHash().delete(hashPOJO.getKey(), hashPOJO.getField()) + " Fields Deleted";
    }
}
