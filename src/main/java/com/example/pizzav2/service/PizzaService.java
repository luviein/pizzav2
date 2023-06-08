package com.example.pizzav2.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.example.pizzav2.model.Pizza;

@Service
public class PizzaService {
    
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
}
