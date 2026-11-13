package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        inventory = new ArrayList<>();
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {return name;}

    public Dealership setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {return address;}

    public Dealership setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {return phone;}

    public Dealership setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public void addVehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, BigDecimal price){
        inventory.add(new Vehicle(vin,year,make,model,vehicleType,color,odometer,price));
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }
}
