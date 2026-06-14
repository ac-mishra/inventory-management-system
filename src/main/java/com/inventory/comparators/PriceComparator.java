package com.inventory.comparators;

import com.inventory.model.Product;

import java.util.Comparator;

/**
 * Sorts products by price in ascending order.
 */
public class PriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {

        int result = Double.compare(
                p1.getPrice(),
                p2.getPrice());

        if (result == 0) {
            return p1.getSku()
                    .compareTo(p2.getSku());
        }

        return result;
    }
}