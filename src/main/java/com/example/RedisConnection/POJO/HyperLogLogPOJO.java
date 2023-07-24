package com.example.RedisConnection.POJO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class HyperLogLogPOJO {
    private String key;
    private String[] values;
    private UnitOfTime unitOfTime;
    private Integer timeDuration;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
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
