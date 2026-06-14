package com.inventory.service;

import com.inventory.comparators.NameComparator;
import com.inventory.comparators.PriceComparator;
import com.inventory.comparators.ValueComparator;
import com.inventory.model.Product;
import com.inventory.model.Transaction;
import com.inventory.utils.InventoryUtils;

import java.util.*;

/**
 * Service layer responsible for all
 * inventory operations.
 */
public class InventoryService {

    private HashSet<Product> productSet;
    private TreeSet<Product> sortedProducts;
    private HashMap<String, Product> productMap;
    private LinkedList<Transaction> transactionHistory;
    private Stack<Product> undoStack;
    private Queue<Product> lowStockQueue;

    private static final int LOW_STOCK_LIMIT = 5;

    public InventoryService() {

        productSet = new HashSet<>();
        sortedProducts = new TreeSet<>();
        productMap = new HashMap<>();
        transactionHistory = new LinkedList<>();
        undoStack = new Stack<>();
        lowStockQueue = new LinkedList<>();
    }

    /**
     * Add new product.
     */
    public boolean addProduct(Product product) {

        if (!InventoryUtils.validateSku(product.getSku())) {
            System.out.println("Invalid SKU format.");
            return false;
        }

        if (!InventoryUtils.validatePrice(product.getPrice())) {
            System.out.println("Invalid price.");
            return false;
        }

        if (!InventoryUtils.validateQuantity(product.getQuantity())) {
            System.out.println("Invalid quantity.");
            return false;
        }

        if (productMap.containsKey(product.getSku())) {
            System.out.println("SKU already exists.");
            return false;
        }

        productSet.add(product);
        sortedProducts.add(product);
        productMap.put(
                product.getSku().toUpperCase(),
                product);

        transactionHistory.addFirst(
                new Transaction(
                        "ADD PRODUCT",
                        product.getSku(),
                        product.getName(),
                        "Product added successfully"
                )
        );

        checkLowStock(product);

        return true;
    }

    /**
     * Search by SKU.
     */
    public Product searchBySku(String sku) {
        return productMap.get(
                sku.toUpperCase());
    }

    /**
     * Search by Product Name.
     */
    public Product searchByName(String name) {

        for (Product product : productSet) {

            if (product.getName()
                    .equalsIgnoreCase(name)) {

                return product;
            }
        }

        return null;
    }

    /**
     * Update product quantity.
     */
    public boolean updateQuantity(
            String sku,
            int newQuantity) {

        Product product =
                searchBySku(sku);

        if (product == null) {

            System.out.println(
                    "Product not found.");

            return false;
        }

        undoStack.push(
                new Product(product));

        int oldQuantity =
                product.getQuantity();

        product.setQuantity(
                newQuantity);

        transactionHistory.addFirst(
                new Transaction(
                        "UPDATE PRODUCT",
                        product.getSku(),
                        product.getName(),
                        "Quantity changed from "
                                + oldQuantity
                                + " to "
                                + newQuantity
                )
        );

        checkLowStock(product);

        return true;
    }

    /**
     * Delete product.
     */
    public boolean deleteProduct(
            String sku) {

        Product product =
                searchBySku(sku);

        if (product == null) {

            System.out.println(
                    "Product not found.");

            return false;
        }

        undoStack.push(
                new Product(product));

        productSet.remove(product);
        sortedProducts.remove(product);
        productMap.remove(product.getSku());

        transactionHistory.addFirst(
                new Transaction(
                        "DELETE PRODUCT",
                        product.getSku(),
                        product.getName(),
                        "Product deleted"
                )
        );

        return true;
    }

    /**
     * Display all products.
     */
    public void displayProducts() {

        if (productSet.isEmpty()) {

            System.out.println(
                    "No products available.");

            return;
        }

        for (Product product : productSet) {
            System.out.println(product);
        }
    }

    /**
     * Display products sorted by SKU.
     */
    public void displayProductsSortedBySku() {

        if (sortedProducts.isEmpty()) {

            System.out.println(
                    "No products available.");

            return;
        }

        for (Product product :
                sortedProducts) {

            System.out.println(product);
        }
    }

    /**
     * Display products sorted by Name.
     */
    public void displayProductsSortedByName() {

        List<Product> list =
                new ArrayList<>(productSet);

        Collections.sort(
                list,
                new NameComparator());

        InventoryUtils.printList(list);
    }

    /**
     * Display products sorted by Price.
     */
    public void displayProductsSortedByPrice() {

        List<Product> list =
                new ArrayList<>(productSet);

        Collections.sort(
                list,
                new PriceComparator());

        InventoryUtils.printList(list);
    }

    /**
     * Display products sorted by Value.
     */
    public void displayProductsSortedByValue() {

        List<Product> list =
                new ArrayList<>(productSet);

        Collections.sort(
                list,
                new ValueComparator());

        InventoryUtils.printList(list);
    }

    /**
     * Display transaction history.
     */
    public void displayTransactionHistory() {

        if (transactionHistory.isEmpty()) {

            System.out.println(
                    "No transactions available.");

            return;
        }

        InventoryUtils.printList(
                transactionHistory);
    }

    /**
     * Display low stock alerts.
     */
    public void displayLowStockAlerts() {

        if (lowStockQueue.isEmpty()) {

            System.out.println(
                    "No low stock alerts.");

            return;
        }

        for (Product product :
                lowStockQueue) {

            System.out.println(product);
        }
    }

    /**
     * Display inventory statistics.
     */
    public void displayInventoryStatistics() {

        int totalProducts = productSet.size();

        int totalQuantity = 0;

        double totalValue = 0;

        double totalPrice = 0;

        Product highestValueProduct = null;

        double highestValue = 0;

        for (Product product : productSet) {

            totalQuantity += product.getQuantity();

            totalValue += product.getInventoryValue();

            totalPrice += product.getPrice();

            if (product.getInventoryValue() > highestValue) {

                highestValue = product.getInventoryValue();

                highestValueProduct = product;
            }
        }

        double averagePrice = 0;

        if (!productSet.isEmpty()) {

            averagePrice =
                    totalPrice / productSet.size();
        }

        System.out.println(
                "\n===== INVENTORY STATISTICS =====");

        System.out.println(
                "Total Products : "
                        + totalProducts);

        System.out.println(
                "Total Quantity : "
                        + totalQuantity);

        System.out.println(
                "Total Inventory Value : "
                        + totalValue);

        System.out.println(
                "Average Product Price : "
                        + averagePrice);

        System.out.println(
                "Low Stock Products : "
                        + lowStockQueue.size());

        if (highestValueProduct != null) {

            System.out.println(
                    "Highest Value Product : "
                            + highestValueProduct.getName());
        }
    }
    /**
     * Undo last operation.
     */
    public void undoLastUpdate() {

        if (undoStack.isEmpty()) {

            System.out.println(
                    "Nothing to undo.");

            return;
        }

        Product previous =
                undoStack.pop();

        Product current =
                searchBySku(
                        previous.getSku());

        if (current != null) {

            current.setQuantity(
                    previous.getQuantity());

            current.setPrice(
                    previous.getPrice());

        } else {

            productSet.add(previous);
            sortedProducts.add(previous);
            productMap.put(
                    previous.getSku(),
                    previous);
        }

        transactionHistory.addFirst(
                new Transaction(
                        "UNDO OPERATION",
                        previous.getSku(),
                        previous.getName(),
                        "Previous state restored"
                )
        );

        System.out.println(
                "Undo successful.");
    }

    /**
     * Check low stock condition.
     */
    private void checkLowStock(
            Product product) {

        if(product.getQuantity()
                <= LOW_STOCK_LIMIT
                &&
                !lowStockQueue.contains(product))
        {
            lowStockQueue.offer(product);
        }
    }
}