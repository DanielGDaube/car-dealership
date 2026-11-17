package com.pluralsight;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    // 1 field
    Dealership dealership;

    // Name of file to read from and write to
    private static final String FILE_NAME = "inventory.csv";

    Scanner scanner = new Scanner(System.in);

    // Initializes the user interface by calling DealershipFileManager.getDealership()
    private UserInterface init() {
        this.dealership = DealershipFileManager.getDealership(FILE_NAME);
        System.out.println(dealership);
        return this;
    }

    // Calls init() to set the dealership that should be displayed
    // Displays a menu that uses a switch to call selected methods
    public void display(){
        init();



        boolean menuCondition = true;

        while (menuCondition) {
            try {
                System.out.println("WELCOME TO");
                System.out.println(dealership.getName());
                System.out.println("1 - Find vehicles within a price range");
                System.out.println("2 - Find vehicles by make / model");
                System.out.println("3 - Find vehicles within a range of years");
                System.out.println("4 - Find vehicles by color");
                System.out.println("5 - Find vehicles by mileage");
                System.out.println("6 - Find vehicles by type");
                System.out.println("7 - List all vehicles");
                System.out.println("8 - Add a vehicle");
                System.out.println("9 - Remove a vehicle");
                System.out.println("99 - Quit");

                switch (scanner.nextInt()) {
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
                }
            }
            catch (Exception e){
                System.err.println("Invalid Input!");
                scanner.nextLine();
            }
        }
    }

    // Helper method to display a list of vehicles
    private void displayVehicles(List<Vehicle> searchList){
        for (Vehicle vehicle : searchList){
            System.out.println(vehicle);
        }
    }

    // Receives a price range from the user
    // Calls dealership.getVehiclesByPrice() using the given range to return a list of vehicles
    // Calls displayVehicles() using the returned list
    public void processGetByPriceRequest() {
        scanner.nextLine();
        while (true){
            try {
                String minString;
                String maxString;
                System.out.println("Please enter your desired price range:");
                String minMax = scanner.nextLine();
                //Attempts to account for various user input errors to avoid saving minString as an invalid input
                if (minMax.length() > 10 || minMax.length() < 6 || !minMax.matches(".*\\d.*")) {
                    System.err.println("Invalid input!");
                    continue;
                }
                else if (!minMax.contains("-") && !minMax.contains(" ")){ //Accounts for users entering values one at a time
                    minString = minMax;
                    maxString = scanner.nextLine();
                    minMax = String.join("-",minString,maxString).trim();
                } else if (!minMax.contains("-") && minMax.contains(" ")) { //Accounts for users separating values with " " rather than "-"
                    minMax = minMax.replace(" ","-").trim();
                } else {
                    minMax = minMax.trim();
                }
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
                break;
            }
            catch (Exception e){
                System.err.println("Invalid Input!");
            }
        }
    }

    // Receives a make and model from the user
    // Calls dealership.getVehiclesByMakeModel() using the given make and model to return a list of vehicles
    // Calls displayVehicles() using the returned list
    public void processGetByMakeModelRequest(){
        scanner.nextLine();
        while (true) {
            try {
                System.out.print("Enter the desired make and model:");
                String makeModel = scanner.nextLine();
                int spaceIndex = makeModel.indexOf(" ");
                String make = makeModel.substring(0, spaceIndex);
                String model = makeModel.substring(spaceIndex + 1);
                List<Vehicle> makeModelList = dealership.getVehiclesByMakeModel(make, model);
                if (makeModelList.isEmpty()) {
                    System.out.println("No vehicles found with specified make and model.");
                } else {
                    displayVehicles(makeModelList);
                }
                break;
            }
            catch (Exception e){
                System.err.println("Invalid Input");
            }
        }
    }

    // Receives a year range from the user
    // Calls dealership.getVehiclesByYear() using the given range to return a list of vehicles
    // Calls displayVehicles() using the returned list
    public void processGetByYearRequest(){
        scanner.nextLine();
        while (true) {
            try {
                System.out.println("Enter the desired range in the following format:");
                System.out.print("yyyy - yyyy:");
                String startEnd = scanner.nextLine();
                String[] startEndTokens = startEnd.split("-");
                int start = Integer.parseInt(startEndTokens[0].trim());
                int end = Integer.parseInt(startEndTokens[1].trim());
                List<Vehicle> yearList = dealership.getVehiclesByYear(start, end);
                if (yearList.isEmpty()) {
                    System.out.println("No vehicles found within the specified range.");
                } else {
                    displayVehicles(yearList);
                }
                break;
            }
            catch (Exception e){
                System.err.println("Invalid Input!");
            }
        }
    }

    // Receives a color from the user
    // Calls dealership.getVehiclesByColor() using the given color to return a list of vehicles
    // Calls displayVehicles() using the returned list
    public void processGetByColorRequest(){
        scanner.nextLine();
        while (true) {
            try {
                System.out.print("Enter the desired color:");
                String color = scanner.nextLine();
                List<Vehicle> colorList = dealership.getVehiclesByColor(color);
                if (colorList.isEmpty()) {
                    System.out.println("No vehicles found with that color.");
                } else {
                    displayVehicles(colorList);
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid Input");
            }
        }
    }

    // Receives a mileage range from the user
    // Calls dealership.getVehiclesByMileage() using the given range to return a list of vehicles
    // Calls displayVehicles() using the returned list
    public void processGetByMileageRequest(){
        scanner.nextLine();
        while (true) {
            try {
                System.out.print("Enter the desired range in the following format:");
                System.out.println("000000 - 000000:");
                String minMax = scanner.nextLine();
                String[] minMaxTokens = minMax.split("-");
                int min = Integer.parseInt(minMaxTokens[0].trim());
                int max = Integer.parseInt(minMaxTokens[1].trim());
                List<Vehicle> mileageList = dealership.getVehiclesByMileage(min, max);
                if (mileageList.isEmpty()) {
                    System.out.println("No vehicles found within the specified range.");
                } else {
                    displayVehicles(mileageList);
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }
        }
    }

    // Receives a vehicle type from the user
    // Calls dealership.getVehiclesByType() using the given type to return a list of vehicles
    // Calls displayVehicles() using the returned list
    public void processGetByVehicleTypeRequest(){
        scanner.nextLine();
        while (true) {
            try {
                System.out.print("Enter the desired type:");
                String type = scanner.nextLine();
                List<Vehicle> typeList = dealership.getVehiclesByType(type);
                if (typeList.isEmpty()) {
                    System.out.println("No vehicles found of that type.");
                } else {
                    displayVehicles(typeList);
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }
        }
    }

    // Calls displayVehicles() dealership.getAllVehicles() to print the whole inventory
    public void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }

    // Receives information from the user to create a new vehicle and add it to the inventory
    // Calls DealershipFileManager.saveDealership() to save the new list to 'inventory.csv'
    public void processAddVehicleRequest(){
        while (true) {
            try {
                System.out.println("Please enter the following information:");
                System.out.print("VIN:");
                int vin = scanner.nextInt();
                System.out.print("Year:");
                int year = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Make and Model:");
                String makeModel = scanner.nextLine();
                String make;
                String model;
                if (makeModel.contains(" ")){
                    int spaceIndex = makeModel.indexOf(" ");
                    make = makeModel.substring(0, spaceIndex);
                    model = makeModel.substring(spaceIndex + 1);
                }
                else {
                    System.out.println("Please enter both make, and model.");
                    continue;
                }
                System.out.print("Type:");
                String type = scanner.nextLine();
                System.out.print("Color:");
                String color = scanner.nextLine();
                System.out.print("Odometer:");
                int odometer = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Price:");
                BigDecimal price = new BigDecimal(scanner.nextLine());
                dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
                DealershipFileManager.saveDealership(FILE_NAME, dealership.getAllVehicles(), dealership);
                System.out.println("Thank You!");
                break;
            } catch (Exception e) {
                System.err.println("Invalid Input!");
                scanner.nextLine();
            }
        }

    }

    // Receives a VIN from the user to remove a vehicle with the matching VIN
    // Calls DealershipFileManager.saveDealership() to save the new list to 'inventory.csv'
    public void processRemoveVehicleRequest(){
        while (true) {
            try {
                int startSize = dealership.getAllVehicles().size();
                System.out.println("Please enter the VIN of the vehicle you would like to remove.");
                System.out.print("VIN:");
                dealership.removeVehicle(scanner.nextInt());
                scanner.nextLine();
                if (dealership.getAllVehicles().size() == startSize) {
                    System.out.println("VIN not found! Please try again.");
                } else {
                    System.out.println("Vehicle removed.");
                    DealershipFileManager.saveDealership(FILE_NAME, dealership.getAllVehicles(), dealership);
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid Input!");
                scanner.nextLine();
            }
        }
    }

    private String signRemove(String price){
        if (price.contains("$")){
            price = price.replace("$","");
        }
        return price;
    }
}
