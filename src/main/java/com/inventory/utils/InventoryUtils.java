package com.inventory.utils;

import java.util.List;

/**
 * Utility methods for validation and
 * generic operations.
 */
public final class InventoryUtils {

    private InventoryUtils() {
    }

    /**
     * SKU format:
     * P101
     * P999
     */
    public static boolean validateSku(String sku) {

        if (sku == null ||
                sku.trim().isEmpty()) {

            return false;
        }

        return sku.toUpperCase()
                .matches("P\\d{3}");
    }

    /**
     * Price must be positive.
     */
    public static boolean validatePrice(double price) {
        return price > 0;
    }

    /**
     * Quantity cannot be negative.
     */
    public static boolean validateQuantity(int quantity) {
        return quantity >= 0;
    }

    /**
     * Removes unwanted spaces.
     */
    public static String sanitizeText(
            String text) {

        if (text == null) {
            return "";
        }

        return text.trim();
    }

    /**
     * Generic method.
     */
    public static <T> void printList(
            List<T> list) {

        if (list == null ||
                list.isEmpty()) {

            System.out.println(
                    "No records found.");

            return;
        }

        for (T item : list) {
            System.out.println(item);
        }
    }
}