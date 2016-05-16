package eu.alten.sales_taxes.beans.items;

import java.math.BigDecimal;

/**
 * Superclass for untaxed items.
 * Any specific class representing untaxed item should exend it
 */
public class UntaxedItem extends Item {

    public UntaxedItem(String name, double price) {
        super(name, price);
        taxAmount = BigDecimal.ZERO;
    }
}
