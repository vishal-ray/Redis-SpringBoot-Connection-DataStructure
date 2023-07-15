package com.example.RedisConnection.modules;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
public class Student implements Serializable {
    @Id
    private int id;
    private String name;
    private int Standard;

    public Student() {
    }

    public Student(int id, String name, int standard) {
        this.id = id;
        this.name = name;
        this.Standard = standard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return Standard;
    }

    public void setStandard(int standard) {
        this.Standard = standard;
    }
}
