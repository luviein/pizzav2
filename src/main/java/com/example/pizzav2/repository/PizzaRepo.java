package com.example.pizzav2.repository;

import java.util.Optional;

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

    public Optional<Order> get(String id){
         String json = (String) template.opsForValue().get(id);
         //sets attributes of Order,Pizza,Delivery based on JSON data 
         return Optional.of(Order.getFromJSON(json));
    }
}
