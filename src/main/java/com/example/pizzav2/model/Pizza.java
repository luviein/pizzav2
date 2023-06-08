package com.example.pizzav2.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Pizza implements Serializable {
    
    @NotNull(message="Please select at least 1 pizza." )
    private String pizza;

    @NotNull(message="Please select a size.")
    private String size;

    @Min(value=1, message="Please order at least one.")
    @Max(value=10, message="Only maximum of 10 pizzas can be ordered at one go.")
    private int quantity;

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Pizza [pizza=" + pizza + ", size=" + size + ", quantity=" + quantity + "]";
    }

    //set JSON to Pizza Object retrieved by Order ID
    public static Pizza getFromJSON(JsonObject o){
        Pizza p = new Pizza();
        p.setPizza(o.getString("pizza"));
        p.setQuantity(o.getInt("quantity"));
        p.setSize(o.getString("size"));

        return p;
    }
    
}
