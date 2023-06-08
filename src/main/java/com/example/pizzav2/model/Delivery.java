package com.example.pizzav2.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable {
    
    @NotNull(message="Name is required")
    @NotBlank(message="Name is required")
    @Size(min=3, message="Minimum of 3 characters")
    private String name;

    @NotNull(message="Phone Number is required")
    @NotBlank(message="Phone Number is required")
    @Pattern(regexp = "^[0-9]{8,}$", message="Must be valid phone number")
    private String phone;

    @NotNull(message="Address is required")
    @NotBlank(message="Address is required")
    private String address;
    private boolean isRush = false;
    private String comments;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isRush() {
        return isRush;
    }
    public void setRush(boolean isRush) {
        this.isRush = isRush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Delivery [name=" + name + ", phone=" + phone + ", address=" + address + ", isRush=" + isRush
                + ", comments=" + comments + "]";
    }

    //set JSON to Delivery Object retrieved by Order ID

    public static Delivery getFromJSON(JsonObject o){
        Delivery d = new Delivery();
        d.setName(o.getString("name"));
        d.setAddress(o.getString("address"));
        d.setComments(o.getString("comments"));
        d.setPhone(o.getString("phone"));
        d.setRush(o.getBoolean("rush"));

        return d;
    }
    
    

}
