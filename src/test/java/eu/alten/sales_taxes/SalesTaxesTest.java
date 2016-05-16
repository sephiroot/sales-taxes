package eu.alten.sales_taxes;

import eu.alten.sales_taxes.beans.Basket;
import eu.alten.sales_taxes.beans.items.ImportedItem;
import eu.alten.sales_taxes.beans.items.Item;
import eu.alten.sales_taxes.beans.items.untaxed.Book;
import eu.alten.sales_taxes.beans.items.untaxed.Food;
import eu.alten.sales_taxes.beans.items.untaxed.MedicalGood;
import eu.alten.sales_taxes.utils.TaxesUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * Generic tests
 */
public class SalesTaxesTest {

    BigDecimal inputValue;

    @Test
    /*
    Goal of this test is to get sure the rounding method is working properly.
    Any change to TaxesUtils.round method will have impact on following assertions.
     */
    public void roundTest() {
        Assert.assertTrue(31.05 == populate("31.05").doubleValue());
        Assert.assertTrue(1.50 == populate("1.499").doubleValue());
        Assert.assertTrue(1.60 == populate("1.574").doubleValue());
        Assert.assertTrue(1.60 == populate("1.575").doubleValue());
        Assert.assertTrue(31.15 == populate("31.114").doubleValue());
        Assert.assertTrue(982.55 == populate("982.533").doubleValue());
        Assert.assertTrue(122.55 == populate("122.523").doubleValue());
        Assert.assertTrue(1455.55 == populate("1455.546").doubleValue());
        Assert.assertTrue(1455.55 == populate("1455.528").doubleValue());
        Assert.assertTrue(12.50 == populate("12.49").doubleValue());
        Assert.assertTrue(11 == populate("11").doubleValue());
    }

    @Test
    public void input1() {
        Basket customerBasket = new Basket();
        customerBasket.addSingleItem(new Book("The Ship of Theseus", 12.49));
        customerBasket.addSingleItem(new Item("music CD", 14.99));
        customerBasket.addSingleItem(new Food("chocolate bar", 0.85));

        System.out.println(customerBasket);
        assertTrue(true);
    }

    @Test
    public void input2() {
        Basket customerBasket = new Basket();
        customerBasket.addSingleItem(new ImportedItem(new Food("Imported box of chocolates", 10.00)));
        customerBasket.addSingleItem(new ImportedItem(new Item("Imported bottle of perfume", 47.50)));

        System.out.println(customerBasket);
        assertTrue(true);
    }

    @Test
    public void input3() {
        Basket customerBasket = new Basket();
        customerBasket.addSingleItem(new ImportedItem(new Item("imported bottle of perfume", 27.99)));
        customerBasket.addSingleItem(new Item("bottle of perfume", 18.99));
        customerBasket.addSingleItem(new MedicalGood("packet of headache pills", 9.75));
        customerBasket.addSingleItem(new ImportedItem(new Food("box of imported chocolates", 11.25)));

        System.out.println(customerBasket);
        assertTrue(true);
    }


    private BigDecimal populate(String value) {
        inputValue = new BigDecimal(value);
        inputValue.setScale(3, BigDecimal.ROUND_FLOOR);
        BigDecimal result = TaxesUtils.round(inputValue);
        return result;
    }
}
