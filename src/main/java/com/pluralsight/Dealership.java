package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    // 4 private fields
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    // Constructor using first 3 fields
    // Instantiates inventory
    public Dealership(String name, String address, String phone) {
        inventory = new ArrayList<>();
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getters/Setters
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

    // Adds a vehicle to the inventory ArrayList
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }

    // Removes a vehicle from the inventory ArrayList if the given VIN matches the vehicle's VIN
    public void removeVehicle(int vin){
        inventory.removeIf(vehicle -> vehicle.getVin() == vin);
    }

    // Returns the entire inventory
    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }

    // Returns a list of vehicles within a range
    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max){
        List<Vehicle> priceList = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getPrice().compareTo(min) >= 0 && vehicle.getPrice().compareTo(max) <= 0){
                priceList.add(vehicle);
            }
        }
        return priceList;
    }

    // Returns a list of vehicles with matching make and model
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        List<Vehicle> makeModelList = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                makeModelList.add(vehicle);
            }
        }
        return makeModelList;
    }

    // Returns a list of vehicles within a range
    public List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> yearList = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getYear() >= min && vehicle.getYear() <= max){
                yearList.add(vehicle);
            }
        }
        return yearList;
    }

    // Returns a list of vehicles with a matching color
    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> colorList = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getColor().equalsIgnoreCase(color)){
                colorList.add(vehicle);
            }
        }
        return colorList;
    }

    // Returns a list of vehicles within a range
    public List<Vehicle> getVehiclesByMileage(int min, int max){
        List<Vehicle> mileageList = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max){
                mileageList.add(vehicle);
            }
        }
        return mileageList;
    }

    // Returns a list of vehicles with matching vehicle type
    public List<Vehicle> getVehiclesByType(String vehicleType){
        List<Vehicle> typeList = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                typeList.add(vehicle);
            }
        }
        return typeList;
    }

    // to string formatting
    @Override
    public String toString(){
        return String.format("%s|%s|%s",name,address,phone);
    }
}
