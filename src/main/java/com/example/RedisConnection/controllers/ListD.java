package com.example.RedisConnection.controllers;

import com.example.RedisConnection.POJO.ListPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/list")
public class ListD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(ListD.class);

    @GetMapping("/index/{index}")
    public String getByIndex(@RequestBody ListPOJO listPOJO) {
        logger.info("Retrieving the Values");
        logger.info("Values Retrieved");
        return (String) template.opsForList().index(listPOJO.getKey(), listPOJO.getIndex());
    }

    @PostMapping("/left")
    public String saveLeft(@RequestBody ListPOJO listPOJO)
    {
        logger.info("New List-Value about to be saved in Left");
        template.opsForList().leftPush(listPOJO.getKey(),listPOJO.getValue());
        if(listPOJO.getTimeDuration() != null)
            template.expire(listPOJO.getKey(),listPOJO.getTimeDuration(), TimeUnit.valueOf(listPOJO.getUnitOfTime()));
        logger.info("List-Value saved in Left");
        return listPOJO.getKey() + " = " + listPOJO.getValue() + " Saved";
    }
    @PostMapping("/right")
    public String saveRight(@RequestBody ListPOJO listPOJO)
    {
        logger.info("New List-Value about to be saved in Right");
        template.opsForList().rightPush(listPOJO.getKey(),listPOJO.getValue());
        logger.info("List-Value saved in Right");
        return listPOJO.getKey() + " = " + listPOJO.getValue() + " Saved";
    }

    @DeleteMapping("/remove")
    public void removeValue(@RequestBody ListPOJO listPOJO)
    {
        logger.info("Process of List Variable Deletion Begins");
        template.opsForList().remove(listPOJO.getKey(), 0, listPOJO.getValue());
        logger.info("List Variable Deleted");
    }
    @DeleteMapping("/leftPop")
    public String popFromLeft(@RequestBody ListPOJO listPOJO)
    {
        logger.info("Process of List->Value Deletion From Left Side Begins");
        logger.info("List->Value Deleted From Left Side");
        return (String) template.opsForList().leftPop(listPOJO.getKey());
    }
    @DeleteMapping("/rightPop")
    public String popFromRight(@RequestBody ListPOJO listPOJO)
    {
        logger.info("Process of List->Value Deletion From Right Side Begins");
        logger.info("List->Value Deleted From Right Side");
        return (String) template.opsForList().rightPop(listPOJO.getKey());
    }
}