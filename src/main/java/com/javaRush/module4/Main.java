package com.javaRush.module4;

import com.javaRush.module4.controller.ManagementCommandDB;

public class Main {
    public static void main(String[] args) {
        ManagementCommandDB commandDB = new ManagementCommandDB();
        commandDB.createCustomer();
        commandDB.buyerReturnedRentedFilm();
        commandDB.buyerRentedInventory();
        commandDB.newMovieIsAvailable();
    }
}