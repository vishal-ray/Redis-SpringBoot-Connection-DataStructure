package com.example.RedisConnection.controllers;
import com.example.RedisConnection.POJO.SortedSetPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sortedSet")
public class SortedSetD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(SortedSetD.class);
    @PostMapping("/{key}")
    public String addToZSet(@RequestBody SortedSetPOJO sortedSetPOJO) {
        logger.info("New SortedSet-Value about to be saved");
        template.opsForZSet().add(sortedSetPOJO.getKey(), sortedSetPOJO.getValue(), sortedSetPOJO.getScore());
        if(sortedSetPOJO.getTimeDuration() != null)
            template.expire(sortedSetPOJO.getKey(),sortedSetPOJO.getTimeDuration(), TimeUnit.valueOf(sortedSetPOJO.getUnitOfTime()));
        logger.info("SortedSet-Value saved");
        return "Added";
    }

    @GetMapping("/{key}")
    public Set<String> getRangeFromZSet(@RequestBody SortedSetPOJO sortedSetPOJO) {
        logger.info("Retrieving the Values");
        Set<String> range = template.opsForZSet().range(sortedSetPOJO.getKey(), sortedSetPOJO.getStart(), sortedSetPOJO.getEnd());
        logger.info("Values Retrieved");
        return range;
    }

    @GetMapping("/{key}/{value}/score")
    public Double getScoreFromZSet(@RequestBody SortedSetPOJO sortedSetPOJO) {
        logger.info("Retrieving the Scores");
        Double score = template.opsForZSet().score(sortedSetPOJO.getKey(), sortedSetPOJO.getValue());
        logger.info("Scores Retrieved");
        return score;
    }

    @DeleteMapping("/{key}")
    public Long removeValueFromZSet(@RequestBody SortedSetPOJO sortedSetPOJO) {
        logger.info("Process of SortedSet Values Deletion Begun");
        Long removedCount = template.opsForZSet().remove(sortedSetPOJO.getKey(), (Object) sortedSetPOJO.getValues());
        logger.info("SortedSet Values Deleted");
        return removedCount;
    }
}
