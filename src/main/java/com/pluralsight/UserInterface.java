package com.pluralsight;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private UserInterface(){}

    Dealership dealership;

    private static final String FILE_NAME = "inventory.csv";

    private UserInterface init() {
        this.dealership = DealershipFileManager.getDealership(FILE_NAME);
        return this;
    }

    public void display(){
        init();

        Scanner scanner = new Scanner(System.in);

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
                case default -> System.err.println("Invalid Option!");
            }
        }
    }
}
