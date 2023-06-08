package com.example.pizzav2.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.example.pizzav2.model.Delivery;
import com.example.pizzav2.model.Order;
import com.example.pizzav2.model.Pizza;
import com.example.pizzav2.repository.PizzaRepo;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepo repo;
    
    private final static String[] PIZZA_NAMES = {
        "bella",
        "margherita",
        "marinara",
        "spianatacalabrese",
        "trioformaggio"
    };

    private final static String[] PIZZA_SIZE = {
        "sm",
        "md",
        "lg"
    };

    //put final string array into a Set
    //easier to check validation of input value against predefined set
    private final Set<String> pizzaNames;
    private final Set<String> pizzaSize;
    
    public PizzaService(){
        pizzaNames = new HashSet<String>(Arrays.asList(PIZZA_NAMES));
        pizzaSize = new HashSet<String>(Arrays.asList(PIZZA_SIZE));
    }

    //create list of object errors(to store potential errors) when validating 
    public List<ObjectError> validatePizzaOrder(Pizza p){
        List<ObjectError> errors = new LinkedList<>();
        FieldError error;
        if(!pizzaNames.contains(p.getPizza().toLowerCase())){
            //matches field value with HTML #fields.haserrors
            error = new FieldError("pizza", "pizza", "%s is out of stock.".formatted(p.getPizza()));
            errors.add(error);
        }

        if(!pizzaSize.contains(p.getSize().toLowerCase())){
            error = new FieldError("pizza", "size", "We do not have %s size ".formatted(p.getSize()));
            errors.add(error);
        }

        return errors;
    }

 
    // private Order createPizzaOrder(Pizza p, Delivery d){
        
    //     Order o = new Order(p, d);
        
    //     return o;
    // }

    private double calculateCost(Order o){
        double total = 0d;
        switch(o.getPizzaName()){
            case "bella", "spianatacalabrese", "marinara" :
                total += 30;
                break;
            case "margherita":
                total += 22;
                break;
            case "trioformaggio":
                total += 25;
                break;

        }

        switch(o.getPizzaSize()){
            case "sm":
                total *= 1;
                break;
            case "md":
                total *= 1.2;
                break;
            case "lg":
                total *= 1.5;
                break;
        }

        total *= o.getPizzaQuantity();
        if(o.getRush()){
            total += 2;
        }
        o.setTotalCost(total);
        return total;

    }

       //generate random UUID for pizza order 
    public Order savePizzaOrder(Pizza p, Delivery d){
        String orderUUID =  UUID.randomUUID().toString().substring(0,8);
        Order o = new Order(p, d);
        o.setId(orderUUID);
        calculateCost(o);
        repo.save(o);
        return o;
    }

    public Optional<Order> getOrderById(String id){
        return this.repo.get(id);
    }
}
