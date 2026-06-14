package com.inventory.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a product in the inventory.
 */
public class Product implements Comparable<Product> {

    private String sku;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private LocalDateTime lastUpdated;

    public Product(String sku,
                   String name,
                   String category,
                   double price,
                   int quantity) {

        this.sku = sku;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.lastUpdated = LocalDateTime.now();
    }

    /**
     * Copy Constructor.
     */
    public Product(Product other) {
        this.sku = other.sku;
        this.name = other.name;
        this.category = other.category;
        this.price = other.price;
        this.quantity = other.quantity;
        this.lastUpdated = other.lastUpdated;
    }

    public double getInventoryValue() {
        return price * quantity;
    }

    @Override
    public int compareTo(Product other) {
        return this.sku.compareTo(other.sku);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Product))
            return false;

        Product product = (Product) obj;

        return Objects.equals(this.sku,
                product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {

        return String.format(
                "SKU=%s | Name=%s | Category=%s | Price=%.2f | Qty=%d | Value=%.2f",
                sku,
                name,
                category,
                price,
                quantity,
                getInventoryValue());
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.lastUpdated = LocalDateTime.now();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}