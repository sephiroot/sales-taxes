package eu.alten.sales_taxes;

import eu.alten.sales_taxes.beans.Basket;
import eu.alten.sales_taxes.beans.items.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class BasketTest {

    @Test
    /*
    Mainly testing that custom equals and hash code react properly in HashMap
     */
    public void findInBasket() {
        Basket customerBasket = new Basket();
        Item item1 = new Item("Item", 27.99);
        customerBasket.addSingleItem(item1);
        Item item2 = new Item("ItEm", 27.99);
        customerBasket.addSingleItem(item2);
        assertTrue(customerBasket.getItems().keySet().size() == 2);

        Item item3 = new Item("Item", 99.99);
        customerBasket.addSingleItem(item3);
        assertTrue(customerBasket.getItems().keySet().size() == 3);

        customerBasket.addSingleItem(item2);
        assertTrue(customerBasket.getItems().keySet().size() == 3);
        // incremented quantity by 1
        assertTrue(customerBasket.getItems().get(item2) == 2);
    }

    @Test
    /*
    Add and remove items from basket. Find out if total price and taxes are properly updated
     */
    public void basketHandlingItems() {
        eu.alten.sales_taxes.beans.Basket customerBasket = new eu.alten.sales_taxes.beans.Basket();
        Item perfume = new Item("imported bottle of perfume", 27.99);
        customerBasket.addItems(perfume, 3);
        assertTrue(customerBasket.getTotal().equals(perfume.getPrice().multiply(new BigDecimal(3))));
        customerBasket.removeItems(perfume, 3);
        assertTrue(customerBasket.getTotal().doubleValue() == 0.00);

        customerBasket.addItems(perfume, 3);
        customerBasket.removeItems(perfume, 8);
        assertTrue(customerBasket.getTotal().doubleValue() == 0.00);

        customerBasket.addItems(perfume, -3);
        assertTrue(customerBasket.getTotal().doubleValue() == 0.00);
    }
}
