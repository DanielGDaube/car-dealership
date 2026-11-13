package com.pluralsight;

import java.io.IOException;

public class UserInterface {
    private UserInterface(){}

    Dealership dealership;

    private static final String FILE_NAME = "inventory.csv";

    private UserInterface init() throws IOException {
        this.dealership = DealershipFileManager.getDealership(FILE_NAME);
        return this;
    }
}
