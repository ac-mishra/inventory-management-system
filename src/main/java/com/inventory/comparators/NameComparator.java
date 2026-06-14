package com.inventory.comparators;

import com.inventory.model.Product;

import java.util.Comparator;

/**
 * Sorts products alphabetically by name.
 */
public class NameComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {

        int result = p1.getName()
                .compareToIgnoreCase(
                        p2.getName());

        if (result == 0) {
            return p1.getSku()
                    .compareTo(p2.getSku());
        }

        return result;
    }
}