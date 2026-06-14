package com.inventory.model;

import java.time.LocalDateTime;

/**
 * Stores inventory transaction history.
 */
public class Transaction {

    private String transactionType;
    private String productSku;
    private String productName;
    private String description;
    private LocalDateTime transactionTime;

    public Transaction(String transactionType,
                       String productSku,
                       String productName,
                       String description) {

        this.transactionType = transactionType;
        this.productSku = productSku;
        this.productName = productName;
        this.description = description;
        this.transactionTime = LocalDateTime.now();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getProductSku() {
        return productSku;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    @Override
    public String toString() {

        return String.format(
                "[%s] %s | SKU=%s | Product=%s | %s",
                transactionTime,
                transactionType,
                productSku,
                productName,
                description);
    }
}