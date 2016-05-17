package eu.alten.sales_taxes.beans;

import eu.alten.sales_taxes.Main;
import eu.alten.sales_taxes.beans.items.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Container for items a customer is intended to buy.
 */
public class Basket {

    // This data structure has been chosen in order to track the quantity for each item, uniquely identified by name+price
    private Map<Item, Integer> items = new HashMap<>();
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal taxes = BigDecimal.ZERO;

    /** Add to basket a single unit of a specific Item */
    public void addSingleItem(Item toadd) {
        addItems(toadd, 1);
    }

    /** Remove from the basket a single unit of a specific Item */
    public void removeSingleItem(Item toRemove) {
        addItems(toRemove, 1);
    }

    /** Add multiple units of an item to the basket */
    public void addItems(Item toAdd, int qty) {
        incrementQuantity(toAdd, qty);
    }

    /** Remove multiple units of an item to the basket */
    public void removeItems(Item toRemove, int qty) {
        decrementQuantity(toRemove, qty);
    }

    private void incrementQuantity(Item item, int qtyToAddInBasket) {
        if (qtyToAddInBasket < 0) {
            // skip for negative values
            return;
        }
        Integer quantity = items.get(item);
        if (quantity == null) {
            // check against nullpointer, we cannot do any primitive operation using a null value
            quantity = 0;
        }

        items.put(item, qtyToAddInBasket + quantity);
        // once we've added items from cart we must update the price and the taxes
        updateTotals(item, new BigDecimal(qtyToAddInBasket), false);
        if (Main.isDebugEnabled()) {
            System.out.println("Added " + qtyToAddInBasket + " " + item + " to the basket");
        }
    }

    private void decrementQuantity(Item item, int qtyToRemoveFromBasket) {
        if (qtyToRemoveFromBasket < 0) {
            // skip for negative values
            return;
        }
        Integer quantity = items.get(item);
        if (quantity != null) {
            if (qtyToRemoveFromBasket > quantity) {
                // all items removed
                qtyToRemoveFromBasket = quantity;
            }
            items.put(item, qtyToRemoveFromBasket - quantity);
            // once we've removed items from cart we must update the price and the taxes
            updateTotals(item, new BigDecimal(qtyToRemoveFromBasket), true);
            if (Main.isDebugEnabled()) {
                System.out.println("Removed " + qtyToRemoveFromBasket + " " + item + " from the basket");
            }
        }
    }

    /* This method is invoked every time something changes in the basket */
    private void updateTotals(Item toIncrmentInBasket, BigDecimal quantityInBigDecimalForm, boolean isDecrease) {
        if (!isDecrease) {
            total = total.add(toIncrmentInBasket.getPrice().multiply(quantityInBigDecimalForm));
            taxes = taxes.add(toIncrmentInBasket.getCalculatedTaxOnPrice().multiply(quantityInBigDecimalForm));
        } else {
            total = total.subtract(toIncrmentInBasket.getPrice().multiply(quantityInBigDecimalForm));
            taxes = taxes.subtract(toIncrmentInBasket.getCalculatedTaxOnPrice().multiply(quantityInBigDecimalForm));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Basket\n");
        for (Item item : items.keySet()) {
            stringBuilder.append(items.get(item));
            stringBuilder.append(" ");
            stringBuilder.append(item.getName());
            stringBuilder.append(": ");
            stringBuilder.append(item.getPrice());
            stringBuilder.append("\n");
        }
        stringBuilder.append("SalesTaxes: ");
        stringBuilder.append(taxes);
        stringBuilder.append("\nTotal: ");
        stringBuilder.append(total);

        return stringBuilder.toString();
    }

    /**
     * Clean items and set totals to default
     */
    public void reset() {
        this.getItems().clear();
        this.total = BigDecimal.ZERO;
        this.taxes = BigDecimal.ZERO;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }
}
