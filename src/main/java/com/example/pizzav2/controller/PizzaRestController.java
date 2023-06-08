package com.example.pizzav2.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzav2.model.Order;
import com.example.pizzav2.service.PizzaService;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/order")
public class PizzaRestController {
    
    @Autowired
    private PizzaService pSvc;

    @GetMapping(path="{id}")
    public ResponseEntity<String> getOrderDetails(@PathVariable String id){
        Optional<Order> o = pSvc.getOrderById(id);
        if (o.isEmpty()) {
            JsonObject error = Json.createObjectBuilder()
                .add("message", "Order %s is not found".formatted(id))
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error.toString());
        }

        //after everything has been set, convert back to JSON
        //o.get() returns order object (as hexadecimal representation EG: com.example.pizzav2.model.Order@53fb2a47) stored within Optional<order>
        return ResponseEntity.ok(o.get().toJSON().toString());
    }

}
