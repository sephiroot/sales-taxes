package eu.alten.sales_taxes;

import eu.alten.sales_taxes.beans.Basket;
import eu.alten.sales_taxes.beans.items.ImportedItem;
import eu.alten.sales_taxes.beans.items.Item;
import eu.alten.sales_taxes.beans.items.untaxed.Book;
import eu.alten.sales_taxes.beans.items.untaxed.Food;
import eu.alten.sales_taxes.beans.items.untaxed.MedicalGood;
import eu.alten.sales_taxes.utils.TaxesUtils;

public class Main {

    public static void main(String[] args) {
        Basket customerBasket = new Basket();
        {
            customerBasket.addSingleItem(new Book("book", 12.49));
            customerBasket.addSingleItem(new Item("music CD", 14.99));
            customerBasket.addSingleItem(new Food("chocolate bar", 0.85));
            System.out.println(customerBasket);
        }
        {
            customerBasket.reset();
            customerBasket.addSingleItem(new ImportedItem(new Food("Imported box of chocolates", 10.00)));
            customerBasket.addSingleItem(new ImportedItem(new Item("Imported bottle of perfume", 47.50)));
            System.out.println(customerBasket);
        }
        {
            customerBasket.reset();
            customerBasket.addSingleItem(new ImportedItem(new Item("imported bottle of perfume", 27.99)));
            customerBasket.addSingleItem(new Item("bottle of perfume", 18.99));
            customerBasket.addSingleItem(new MedicalGood("packet of headache pills", 9.75));
            customerBasket.addSingleItem(new ImportedItem(new Food("imported box of chocolates", 11.25)));
            System.out.println(customerBasket);
        }
    }

    public static boolean isDebugEnabled() {
        return true;
    }

}