package eu.alten.sales_taxes.beans.items;

import eu.alten.sales_taxes.utils.TaxesUtils;

import java.math.BigDecimal;

/**
 * ImportedItem, generic item having an additional tax rate equals to TaxesUtils.EXTRA_TAX_RATE
 */
public class ImportedItem extends Item {

    // an imported item is, first of all...an item!
    public ImportedItem(Item item) {
        super(item.getName(), item.getPriceNoTaxes().doubleValue());
        // overwrite taxAmount using the one inherited from Item (the standard one) or the one from the actual type of
        // the item which could be an untaxed one
        taxAmount = item.getTaxAmount();
        // no matter what kind of item it is, since it is imported it always have at least 5% of tax rate
        extra = TaxesUtils.EXTRA_TAX_RATE;
    }

}