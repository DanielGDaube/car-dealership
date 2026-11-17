package com.pluralsight;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    Dealership dealership;

    private static final String FILE_NAME = "inventory.csv";

    Scanner scanner = new Scanner(System.in);

    private UserInterface init() {
        this.dealership = DealershipFileManager.getDealership(FILE_NAME);
        return this;
    }

    public void display(){
        init();



        boolean menuCondition = true;

        while (menuCondition){
            System.out.println("WELCOME TO");
            System.out.println(dealership.getName());
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles within a range of years");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage");
            System.out.println("6 - Find vehicles type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");

            switch (scanner.nextInt()){
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> {
                    scanner.close();
                    menuCondition = false;
                }
                default -> System.err.println("Invalid Option!");
            }
        }
    }

    private void displayVehicles(List<Vehicle> searchList){
        for (Vehicle vehicle : searchList){
            System.out.println(vehicle);
        }
    }

    public void processGetByPriceRequest(){

    }

    public void processGetByMakeModelRequest(){

    }

    public void processGetByYearRequest(){

    }

    public void processGetByColorRequest(){

    }

    public void processGetByMileageRequest(){

    }

    public void processGetByVehicleTypeRequest(){

    }

    public void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest(){
        System.out.println("Please enter the following information:");
        System.out.print("VIN:");
        int vin = scanner.nextInt();
        System.out.print("Year:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make and Model:");
        String makeModel = scanner.nextLine();
        int spaceIndex = makeModel.indexOf(" ");
        String make = makeModel.substring(0, spaceIndex);
        String model = makeModel.substring(spaceIndex, makeModel.length()-1);
        System.out.print("Type:");
        String type = scanner.nextLine();
        System.out.print("Color:");
        String color = scanner.nextLine();
        System.out.print("Odometer:");
        int odometer = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Price:");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        dealership.addVehicle(new Vehicle(vin,year,make,model,type,color,odometer,price));
        System.out.println("Thank You!");

    }

    public void processRemoveVehicleRequest(){
        int startSize = dealership.getAllVehicles().size();
        System.out.println("Please enter the VIN of the vehicle you would like to remove.");
        System.out.print("VIN:");
        dealership.removeVehicle(scanner.nextInt());
        scanner.nextLine();
        if (dealership.getAllVehicles().size() == startSize){
            System.out.println("VIN not found! Please try again.");
        }
        else {
            System.out.println("Vehicle removed.");
        }
    }
}
