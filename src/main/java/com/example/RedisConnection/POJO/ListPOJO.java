package com.example.RedisConnection.POJO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ListPOJO {
    private String key;
    private String value;
    private Long index;
    private UnitOfTime unitOfTime;
    private Integer timeDuration;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getUnitOfTime() {
        return String.valueOf(unitOfTime);
    }

    public void setUnitOfTime(UnitOfTime unitOfTime) {
        this.unitOfTime = unitOfTime;
    }

    public Integer getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(Integer timeDuration) {
        this.timeDuration = timeDuration;
    }
}
