package com.example.pizzav2.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Order implements Serializable {
    private String id;
    private double totalCost = 0;
    private Pizza pizza;
    private Delivery delivery;

    // create Pizza and Delivery objects when Order is created
    public Order(Pizza p, Delivery d) {
        this.pizza = p;
        this.delivery = d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }



    //get attributes from Pizza and Delivery to be converted to JSON in Object class
    public String getName() {return this.getDelivery().getName();}
    public String getPhone() {return this.getDelivery().getPhone();}
    public String getAddress() {return this.getDelivery().getAddress();}
    public boolean getRush() {return this.getDelivery().isRush();}
    public String getComments() {return this.getDelivery().getComments();}
    public String getPizzaName() {return this.getPizza().getPizza();}
    public String getPizzaSize() {return this.getPizza().getSize();}
    public int getPizzaQuantity() {return this.getPizza().getQuantity();}

    //displays total cost without the rush surcharge
    public double getPizzaCost(){
        return this.getRush() ? this.getTotalCost() -2 : this.getTotalCost();
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
            .add("orderId", this.getId())
            .add("name", this.getName())
            .add("address", this.getAddress())
            .add("phone", this.getPhone())
            .add("rush", this.getRush())
            .add("comments", this.getComments())
            .add("pizza", this.getPizzaName())
            .add("size", this.getPizzaSize())
            .add("quantity", this.getPizzaQuantity())
            .add("total", this.getTotalCost())
            .build();
    }

   

}
