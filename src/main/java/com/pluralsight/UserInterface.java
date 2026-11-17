package com.pluralsight;

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

    public void processGetByPriceRequest() {
        scanner.nextLine();
        System.out.println("Please enter your desired price range in the following format:");
        System.out.print("$00.00 - $00.00 :");
        String minMax = scanner.nextLine();
        minMax = minMax.replace("$", "");
        String[] minMaxTokens = minMax.split("-");
        BigDecimal min = new BigDecimal(minMaxTokens[0].trim());
        BigDecimal max = new BigDecimal(minMaxTokens[1].trim());
        List<Vehicle> priceList = dealership.getVehiclesByPrice(min, max);
        if (priceList.isEmpty()) {
            System.out.println("No vehicles found in price range.");
        } else {
            displayVehicles(priceList);
        }
    }

    public void processGetByMakeModelRequest(){
        scanner.nextLine();
        System.out.print("Enter the desired make and model:");
        String makeModel = scanner.nextLine();
        int spaceIndex = makeModel.indexOf(" ");
        String make = makeModel.substring(0, spaceIndex);
        String model = makeModel.substring(spaceIndex+1);
        List<Vehicle> makeModelList = dealership.getVehiclesByMakeModel(make, model);
        if (makeModelList.isEmpty()) {
            System.out.println("No vehicles found with specified make and model.");
        } else {
            displayVehicles(makeModelList);
        }
    }

    public void processGetByYearRequest(){
        scanner.nextLine();
        System.out.println("Enter the desired range in the following format:");
        System.out.print("yyyy - yyyy:");
        String startEnd = scanner.nextLine();
        String[] startEndTokens = startEnd.split("-");
        int start = Integer.parseInt(startEndTokens[0].trim());
        int end = Integer.parseInt(startEndTokens[1].trim());
        List<Vehicle> yearList = dealership.getVehiclesByYear(start,end);
        if (yearList.isEmpty()) {
            System.out.println("No vehicles found within the specified range.");
        } else {
            displayVehicles(yearList);
        }
    }

    public void processGetByColorRequest(){
        scanner.nextLine();
        System.out.print("Enter the desired color:");
        String color = scanner.nextLine();
        List<Vehicle> colorList = dealership.getVehiclesByColor(color);
        if (colorList.isEmpty()) {
            System.out.println("No vehicles found with that color.");
        } else {
            displayVehicles(colorList);
        }
    }

    public void processGetByMileageRequest(){
        scanner.nextLine();
        System.out.print("Enter the desired range in the following format:");
        System.out.println("000000 - 000000:");
        String minMax = scanner.nextLine();
        String[] minMaxTokens = minMax.split("-");
        int min = Integer.parseInt(minMaxTokens[0].trim());
        int max = Integer.parseInt(minMaxTokens[1].trim());
        List<Vehicle> mileageList = dealership.getVehiclesByMileage(min,max);
        if (mileageList.isEmpty()) {
            System.out.println("No vehicles found within the specified range.");
        } else {
            displayVehicles(mileageList);
        }
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
        String model = makeModel.substring(spaceIndex+1);
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
