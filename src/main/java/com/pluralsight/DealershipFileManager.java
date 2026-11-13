package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class DealershipFileManager {

    private DealershipFileManager(){}

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

    public static void saveDealership(){

    }
}
