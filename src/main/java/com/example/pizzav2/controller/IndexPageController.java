package com.example.pizzav2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.pizzav2.model.Pizza;

@Controller
public class IndexPageController {
    
    //create new instance of Pizza
    @GetMapping(path="/")
    public String getLanding(Model m){

        m.addAttribute("pizza", new Pizza());

        return "index";
    }
}
