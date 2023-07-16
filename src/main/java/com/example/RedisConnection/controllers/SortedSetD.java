package com.example.RedisConnection.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.lang.String;
import java.util.Set;

@RestController
@RequestMapping("/sortedSet")
public class SortedSetD {
    @Autowired
    private RedisTemplate template;
    final Logger logger = LoggerFactory.getLogger(SortedSetD.class);
    @PostMapping("/{key}")
    public String addToZSet(@PathVariable String key, @RequestParam String value, @RequestParam double score) {
        logger.info("Inside addToZSet");
        template.opsForZSet().add(key, value, score);
        logger.info("addToZSet executed");
        return "Added";
    }

    @GetMapping("/{key}")
    public Set<String> getRangeFromZSet(@PathVariable String key, @RequestParam long start, @RequestParam long end) {

        logger.info("Inside getRangeFromZSet");
        Set<String> range = template.opsForZSet().range(key, start, end);
        logger.info("getRangeFromZSet executed");
        return range;
    }

    @GetMapping("/{key}/{value}/score")
    public Double getScoreFromZSet(@PathVariable String key, @PathVariable String value) {
        logger.info("Inside getScoreFromZSet");
        Double score = template.opsForZSet().score(key, value);
        logger.info("getScoreFromZSet executed");
        return score;
    }

    @DeleteMapping("/{key}")
    public Long removeValueFromZSet(@PathVariable String key, @RequestParam String[] values) {
        logger.info("Inside removeValueFromZSet");
        Long removedCount = template.opsForZSet().remove(key, values);
        logger.info("removeValueFromZSet executed");
        return removedCount;
    }
}
