package com.inventory.comparators;

import com.inventory.model.Product;

import java.util.Comparator;

/**
 * Sorts products by inventory value in descending order.
 */
public class ValueComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {

        int result = Double.compare(
                p2.getInventoryValue(),
                p1.getInventoryValue());

        if (result == 0) {
            return p1.getSku()
                    .compareTo(p2.getSku());
        }

        return result;
    }
}