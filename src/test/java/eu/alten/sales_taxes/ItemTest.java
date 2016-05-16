package eu.alten.sales_taxes;

import eu.alten.sales_taxes.beans.items.ImportedItem;
import eu.alten.sales_taxes.beans.items.Item;
import eu.alten.sales_taxes.beans.items.untaxed.Book;
import eu.alten.sales_taxes.beans.items.untaxed.Food;
import eu.alten.sales_taxes.beans.items.untaxed.MedicalGood;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * Tests in this file act only on Items
 */
public class ItemTest {

    @Test
    /*
    Goal of this test case is to test find out if objects are correctly instantiated, meaning that all specific
    data for each type contains correct values.
     */
    public void instantiateItems() {
        {
            Item item = new Item("bottle of perfume", 14.99);
            assertTrue(item.getPrice().doubleValue() == 16.49);
            assertTrue(item.getTaxAmount().doubleValue() == 10);
            assertTrue(item.getExtra().equals(BigDecimal.ZERO));
        }
        {
            Book book = new Book("IT", 14.99);
            assertTrue(book.getPrice().doubleValue() == 14.99);
            assertTrue(book.getTaxAmount().doubleValue() == 0);
            assertTrue(book.getExtra().equals(BigDecimal.ZERO));
        }
        {
            Food food = new Food("Snikers", 14.99);
            assertTrue(food.getPrice().doubleValue() == 14.99);
            assertTrue(food.getTaxAmount().doubleValue() == 0);
            assertTrue(food.getExtra().equals(BigDecimal.ZERO));
        }
        {
            MedicalGood medical = new MedicalGood("Bend", 14.99);
            assertTrue(medical.getPrice().doubleValue() == 14.99);
            assertTrue(medical.getTaxAmount().doubleValue() == 0);
            assertTrue(medical.getExtra() == BigDecimal.ZERO);
        }
        {
            ImportedItem importedItem = new ImportedItem(new Item("imported bottle of perfume", 27.99));
            assertTrue(importedItem.getPrice().doubleValue() == 32.19);
            assertTrue(importedItem.getTaxAmount().doubleValue() == 10);
            assertTrue(importedItem.getExtra().equals(new BigDecimal(5)));
        }
    }

    /*
    Equals/hash methods for Items must work properly since they are used in basket class to retrieve and add Items in the cart.
    In the following tests we find out how equals and hash methods react in different cases.
     */
    @Test
    public void equalsItem_sameName_samePrice() {
        Item item1 = new Item("NAME", 27.99);
        Item item2 = new Item("NAME", 27.99);
        // they actually ARE the same item
        assertTrue(item1.equals(item2));
    }

    @Test
    public void equalsItem_sameName_differentPrice() {
        Item item1 = new Item("NAME", 27.99);
        Item item2 = new Item("NAME", 27.79);
        // they are not the same item
        assertTrue(!item1.equals(item2));
    }

    @Test
    public void equalsItem_differentName_samePrice() {
        Item item1 = new Item("NAME_1", 27.99);
        Item item2 = new Item("NAME", 27.99);
        // they are not the same item
        assertTrue(!item1.equals(item2));
    }

    @Test
    public void hashItem_sameName_samePrice() {
        Item item1 = new Item("NAME", 27.99);
        Item item2 = new Item("NAME", 27.99);
        // they are not the same item
        assertTrue(item1.hashCode() == item2.hashCode());
    }

    @Test
    public void hashItem_sameName_differentPrice() {
        Item item1 = new Item("NAME", 27.99);
        Item item2 = new Item("NAME", 27.79);
        // they are not the same item
        assertTrue(item1.hashCode() != item2.hashCode());
    }

    @Test
    public void hashItem_differentName_samePrice() {
        Item item1 = new Item("NAME_1", 27.99);
        Item item2 = new Item("NAME", 27.99);
        // they are not the same item
        assertTrue(item1.hashCode() != item2.hashCode());
    }
}
