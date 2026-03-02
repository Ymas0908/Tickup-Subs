package org.tickup.adapters.utils;


/**
 * @author Kassi
 */
public class DoubleRangeOperators {

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedRange(Double number, Double lowerBound, Double upperBound) {
        return (lowerBound <= number && number <= upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenRange(Double number, Double lowerBound, Double upperBound) {
        return (lowerBound < number && number < upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenClosedRange(Double number, Double lowerBound, Double upperBound) {
        return (lowerBound < number && number <= upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedOpenRange(Double number, Double lowerBound, Double upperBound) {
        return (lowerBound <= number && number < upperBound);
    }
}
