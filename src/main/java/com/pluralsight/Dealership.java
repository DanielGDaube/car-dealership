package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    public Dealership(String name, String address, String phone) {
        ArrayList<Vehicle> inventory;
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
}
