package com.example.RedisConnection.controllers;

import com.example.RedisConnection.modules.Student;
import com.example.RedisConnection.repositories.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@EnableCaching
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;
    Logger logger = LoggerFactory.getLogger(StudentController.class);
    @PostMapping
    public Student save(@RequestBody Student student) {
        logger.info("Inside save");
        logger.info("Save executed");
        return studentRepo.save(student);
    }
    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("Inside getAllStudents");
        logger.info("getAllStudents executed");
        return studentRepo.findAll();
    }
    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "Student")
    public Student findStudent(@PathVariable int id) {
        logger.info("Inside findStudent");
        System.out.println("DB Called");
        logger.info("findStudent executed");
        return studentRepo.findProductById(id);
    }
}
