package com.inventory.main;

import com.inventory.model.Product;
import com.inventory.service.InventoryService;

import java.util.Scanner;

/**
 * Main class for Inventory Management System.
 */
public class InventoryManagementSystem {


    private static final Scanner scanner =
            new Scanner(System.in);

    private static final InventoryService inventoryService =
            new InventoryService();

    public static void main(String[] args) {
        loadSampleData();
        System.out.println(
                "========================================");
        System.out.println(
                " INVENTORY MANAGEMENT SYSTEM ");
        System.out.println(
                "========================================");

        int choice;

        do {

            displayMenu();

            choice = readInteger(
                    "Enter your choice: ");

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    updateQuantity();
                    break;

                case 3:
                    viewProductsMenu();
                    break;

                case 4:
                    searchProduct();
                    break;

                case 5:
                    inventoryService
                            .displayLowStockAlerts();
                    break;

                case 6:
                    inventoryService
                            .displayTransactionHistory();
                    break;

                case 7:
                    inventoryService
                            .displayInventoryStatistics();
                    break;

                case 8:
                    inventoryService
                            .undoLastUpdate();
                    break;

                case 9:
                    System.out.println(
                            "Thank you for using Inventory Management System.");
                    break;

                default:
                    System.out.println(
                            "Invalid choice.");
            }

        } while (choice != 9);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n========== MENU ==========");

        System.out.println("1. Add Product");
        System.out.println("2. Update Quantity");
        System.out.println("3. View Products");
        System.out.println("4. Search Product");
        System.out.println("5. Low Stock Alerts");
        System.out.println("6. Transaction History");
        System.out.println("7. Inventory Statistics");
        System.out.println("8. Undo Last Update");
        System.out.println("9. Exit");

        System.out.println("==========================");
    }
    private static void loadSampleData() {

        inventoryService.addProduct(
                new Product(
                        "P101",
                        "Laptop",
                        "Electronics",
                        50000,
                        10));

        inventoryService.addProduct(
                new Product(
                        "P102",
                        "Mouse",
                        "Accessories",
                        500,
                        3));
    }

    private static void addProduct() {

        System.out.println(
                "\n--- Add Product ---");

        System.out.print("SKU (P101): ");
        String sku =
                scanner.nextLine().trim();

        System.out.print("Name: ");
        String name =
                scanner.nextLine().trim();

        System.out.print("Category: ");
        String category =
                scanner.nextLine().trim();

        double price =
                readDouble("Price: ");

        int quantity =
                readInteger("Quantity: ");

        Product product =
                new Product(
                        sku,
                        name,
                        category,
                        price,
                        quantity);

        boolean result =
                inventoryService.addProduct(
                        product);

        if (result) {

            System.out.println(
                    "Product added successfully.");
        }
    }

    private static void updateQuantity() {

        System.out.println(
                "\n--- Update Quantity ---");

        System.out.print(
                "Enter SKU: ");

        String sku =
                scanner.nextLine().trim();

        int quantity =
                readInteger(
                        "Enter new quantity: ");

        boolean result =
                inventoryService.updateQuantity(
                        sku,
                        quantity);

        if (result) {

            System.out.println(
                    "Quantity updated successfully.");
        }
    }

    private static void searchProduct() {

        System.out.println(
                "\n--- Search Product ---");

        System.out.println(
                "1. Search By SKU");

        System.out.println(
                "2. Search By Name");

        int choice =
                readInteger(
                        "Enter choice: ");

        switch (choice) {

            case 1:

                System.out.print(
                        "Enter SKU: ");

                String sku =
                        scanner.nextLine();

                Product skuProduct =
                        inventoryService
                                .searchBySku(
                                        sku);

                if (skuProduct != null) {

                    System.out.println(
                            skuProduct);

                } else {

                    System.out.println(
                            "Product not found.");
                }

                break;

            case 2:

                System.out.print(
                        "Enter Name: ");

                String name =
                        scanner.nextLine();

                Product nameProduct =
                        inventoryService
                                .searchByName(
                                        name);

                if (nameProduct != null) {

                    System.out.println(
                            nameProduct);

                } else {

                    System.out.println(
                            "Product not found.");
                }

                break;

            default:

                System.out.println(
                        "Invalid choice.");
        }
    }

    private static void viewProductsMenu() {

        System.out.println(
                "\n--- View Products ---");

        System.out.println(
                "1. All Products");

        System.out.println(
                "2. Sorted By SKU");

        System.out.println(
                "3. Sorted By Name");

        System.out.println(
                "4. Sorted By Price");

        System.out.println(
                "5. Sorted By Inventory Value");

        int choice =
                readInteger(
                        "Enter choice: ");

        switch (choice) {

            case 1:
                inventoryService.displayProducts();
                break;

            case 2:
                inventoryService
                        .displayProductsSortedBySku();
                break;

            case 3:
                inventoryService
                        .displayProductsSortedByName();
                break;

            case 4:
                inventoryService
                        .displayProductsSortedByPrice();
                break;

            case 5:
                inventoryService
                        .displayProductsSortedByValue();
                break;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }

    private static int readInteger(
            String message) {

        while (true) {

            try {

                System.out.print(
                        message);

                int value =
                        Integer.parseInt(
                                scanner.nextLine());

                return value;

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(
            String message) {

        while (true) {

            try {

                System.out.print(
                        message);

                double value =
                        Double.parseDouble(
                                scanner.nextLine());

                return value;

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid number.");
            }
        }
    }
}