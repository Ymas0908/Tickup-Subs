package org.tickup.adapters.utils;

import java.math.BigDecimal;

/**
 * @author Kassi
 */
public class BigDecimalRangeOperators {

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedRange(BigDecimal number, BigDecimal lowerBound, BigDecimal upperBound) {
        return (lowerBound.compareTo(number) <= 0 && number.compareTo(upperBound) <= 0);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenRange(BigDecimal number, BigDecimal lowerBound, BigDecimal upperBound) {
        return (lowerBound.compareTo(number) < 0 && number.compareTo(upperBound) < 0);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenClosedRange(BigDecimal number, BigDecimal lowerBound, BigDecimal upperBound) {
        return (lowerBound.compareTo(number) < 0 && number.compareTo(upperBound) <= 0);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedOpenRange(BigDecimal number, BigDecimal lowerBound, BigDecimal upperBound) {
        return (lowerBound.compareTo(number) <= 0 && number.compareTo(upperBound) < 0);
    }
}
