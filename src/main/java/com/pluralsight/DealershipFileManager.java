package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class DealershipFileManager {

    // Constructor
    private DealershipFileManager(){}

    // Reads the given file
    // Information from first line is used to create a new dealership
    // Information from the following lines are used to create new vehicles
    // Calls the dealerships addVehicle() method to add the new vehicles to the dealership's inventory
    // Returns the dealership to be used in UserInterface.java
    public static Dealership getDealership(String fileName)  {


        try {
            BufferedReader dealershipReader = new BufferedReader(new FileReader(fileName));

            String dealershipLine = dealershipReader.readLine();

            String[] dealershipTokens = dealershipLine.split("\\|");
            String name = dealershipTokens[0];
            String address = dealershipTokens[1];
            String phone = dealershipTokens[2];

            Dealership dealership = new Dealership(name, address, phone);


            String vehicleLine;

            while ((vehicleLine = dealershipReader.readLine()) != null) {
                String[] vehicleTokens = vehicleLine.split("\\|");
                int vim = Integer.parseInt(vehicleTokens[0]);
                int year = Integer.parseInt(vehicleTokens[1]);
                String make = vehicleTokens[2];
                String model = vehicleTokens[3];
                String vehicleType = vehicleTokens[4];
                String color = vehicleTokens[5];
                int odometer = Integer.parseInt(vehicleTokens[6]);
                BigDecimal price = new BigDecimal(vehicleTokens[7]);

                Vehicle vehicle = new Vehicle(vim, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
            return dealership;
        }
        catch (Exception e){
            System.err.println("Error reading file!");
            return null;
        }
    }

    // Called after adding or removing a vehicle to overwrite 'inventory.csv' with new inventory list
    public static void saveDealership(String fileName, ArrayList<Vehicle> inventory, Dealership dealership){
        try {
            BufferedWriter dealershipWriter = new BufferedWriter(new FileWriter(fileName));
            dealershipWriter.write(String.valueOf(dealership));
            dealershipWriter.write("\n");
            for (Vehicle vehicle : inventory){
                dealershipWriter.write(String.valueOf(vehicle));
                dealershipWriter.write("\n");
            }
            dealershipWriter.close();
        }
        catch (Exception e){
            System.err.println("Error printing file!");
        }

    }
}
