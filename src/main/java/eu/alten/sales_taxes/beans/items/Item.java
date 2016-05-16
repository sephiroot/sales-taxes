package eu.alten.sales_taxes.beans.items;

import eu.alten.sales_taxes.utils.TaxesUtils;

import java.math.BigDecimal;

/**
 * This class represents a generic Item having starndard tax percentage.
 */
public class Item {

    /**
     * Name of the item, make a composite key with originalPrice field
     */
    public String name;
    /**
     * Standard tax rate. It's 10% for generic goods.
     */
    public BigDecimal taxAmount = BigDecimal.TEN;
    /**
     * Extra tax rate. It applies only for imported items. Standard is 0
     */
    public BigDecimal extra = BigDecimal.ZERO;
    /**
     * Price without taxes
     */
    private BigDecimal originalPrice = BigDecimal.ONE;

    private BigDecimal taxedPrice = null;

    public Item(String name, double originalPrice) {
        this.name = name.trim();
        this.originalPrice = new BigDecimal(originalPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /* Notice: Two items could have the same name but different prices!
     The unique identifier is provided by both attributes (name, originalPrice)
     Example: you can have in your basket an item Book having name = "The Ship of Theseus" and having originalPrice 30€ and
     another book named "The Ship of Theseus" having originalPrice 24.44€
     The equals method is a key component in basket because of the data structure used
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!getName().equals(item.getName())) return false;
        return originalPrice.equals(item.originalPrice);

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + originalPrice.hashCode();
        return result;
    }

    public BigDecimal getTaxesPercentage() {
        return taxAmount.add(extra);
    }

    public BigDecimal getPriceNoTaxes() {
        return originalPrice;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", originalPrice=" + originalPrice +
                ", tax %=" + getTaxesPercentage() +
                ", priceWithTaxes=" + taxedPrice +
                ", calculatedTaxes= " + getCalculatedTaxOnPrice() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getExtra() {
        return extra;
    }

    /**
     * Price including taxes
     */
    public BigDecimal getPrice() {
        if (taxedPrice == null) {
            this.taxedPrice = new BigDecimal(this.originalPrice.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            taxedPrice = this.taxedPrice.add(getCalculatedTaxOnPrice());
        }
        return taxedPrice;
    }

    public BigDecimal getCalculatedTaxOnPrice() {
        return TaxesUtils.round(originalPrice.multiply(getTaxesPercentage()).divide(new BigDecimal("100")));
    }
}