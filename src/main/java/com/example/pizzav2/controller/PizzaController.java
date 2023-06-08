package com.example.pizzav2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pizzav2.model.Delivery;
import com.example.pizzav2.model.Pizza;
import com.example.pizzav2.service.PizzaService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class PizzaController {

    @Autowired
    private PizzaService pSvc;
    
    @PostMapping(path="/pizza")
    public String postPizza(HttpSession session, @Valid Pizza pizza,  BindingResult result, Model m){
        List<ObjectError> errors = pSvc.validatePizzaOrder(pizza);

        if(result.hasErrors()){
            return "index";
        }

        
        if(!errors.isEmpty()){
            for(ObjectError e : errors){
                result.addError(e);
            }
            return "index";
        }
        //to store pizza object in session
        session.setAttribute("pizza", pizza);
        m.addAttribute("delivery", new Delivery());
        return "delivery";
            
    }

    @PostMapping(path="/pizza/order")
        public String placeOrder(HttpSession session, @Valid Delivery delivery, BindingResult result, Model m) {
            if(result.hasErrors()){
                return "delivery";
            }
        
        return "delivery";
    }
    
   
}

