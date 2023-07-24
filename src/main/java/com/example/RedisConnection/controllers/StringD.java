package com.example.RedisConnection.controllers;

import com.example.RedisConnection.POJO.StringPOJO;
import com.example.RedisConnection.POJO.UnitOfTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/string")
public class StringD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(StringD.class);
    @PostMapping
    public String save(@RequestBody StringPOJO stringPOJO)
    {
        logger.info("New String Value about to be saved");
        if(stringPOJO.getTimeDuration() == null)
            template.opsForValue().set(stringPOJO.getKey(),stringPOJO.getValue());
        else
        {
            UnitOfTime unit = stringPOJO.getUnitOfTime();
            TimeUnit timeUnit = TimeUnit.valueOf(String.valueOf(unit));
            template.opsForValue().set(stringPOJO.getKey(),stringPOJO.getValue(),stringPOJO.getTimeDuration(),timeUnit);
        }

        logger.info("Value saved");
        return stringPOJO.getKey() + " = " + stringPOJO.getValue() + " Saved";
    }
    @GetMapping
    public StringPOJO getValue(@RequestBody StringPOJO stringPOJO)
    {
        logger.info("Retrieving the Value");
        StringPOJO newPojo = new StringPOJO();
        newPojo.setKey(stringPOJO.getKey());
        newPojo.setValue((String) template.opsForValue().get(stringPOJO.getKey()));
        logger.info("Value Retrieved");
        return newPojo;
    }
    @DeleteMapping
    public String deleteKey(@RequestBody StringPOJO stringPOJO)
    {
        logger.info("Process of String Variable Deletion Begins");
        logger.info("String Variable Deleted");
        return template.opsForValue().getAndDelete(stringPOJO.getKey()) + " Deleted";
    }
    @PutMapping
    public String updateKeyValue(@RequestBody StringPOJO stringPOJO)
    {
        logger.info("String Variable Value Updation Process Begins");
//        It updates existing value. If non-existing data is passed here, the key gets saved, but it doesn't store its value.
        template.opsForValue().setIfPresent(stringPOJO.getKey(),stringPOJO.getValue());
        logger.info("String Variable Value Updated");
        return "Updated Value : " + stringPOJO.getKey() + " =  "+ template.opsForValue().get(stringPOJO.getKey());
    }
}