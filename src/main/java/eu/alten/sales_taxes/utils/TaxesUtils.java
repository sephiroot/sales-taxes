package eu.alten.sales_taxes.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxesUtils {

    // never change this !
    private static final BigDecimal FIVE = new BigDecimal(5);
    // never change this !
    private static final BigDecimal HUNDRED = new BigDecimal(100);

    public static final BigDecimal EXTRA_TAX_RATE = FIVE;
    public static final BigDecimal ROUND_VALUE = EXTRA_TAX_RATE.divide(HUNDRED);

    /**
     * This method should never be modified.
     * It is used in order to correctly round numbers basing on ROUND_VALUE and RoundingMode
     *
     * @param toRound
     * @return
     */
    public static BigDecimal round(BigDecimal toRound) {
        /*
        To find out which is the correct value after rounding we must:
            1. Divide by ROUND_VALUE
            2. Round up to the next int value (as required)
            3. Multiply back by 0.05
        */
        BigDecimal result = toRound.divide(ROUND_VALUE, 10, RoundingMode.UP);
        return result.setScale(0, RoundingMode.UP).multiply(ROUND_VALUE);

    }
}
