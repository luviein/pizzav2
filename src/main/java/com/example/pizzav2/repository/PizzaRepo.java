package com.example.pizzav2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.pizzav2.model.Order;

@Repository
public class PizzaRepo {

    @Autowired
    private RedisTemplate<String,Object> template;

    public void save(Order o){
        template.opsForValue().set(o.getId(), o.toJSON().toString());

    }
}
