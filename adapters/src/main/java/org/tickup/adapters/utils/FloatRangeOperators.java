package org.tickup.adapters.utils;


/**
 * @author Kassi
 */
public class FloatRangeOperators {

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedRange(Float number, Float lowerBound, Float upperBound) {
        return (lowerBound <= number && number <= upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenRange(Float number, Float lowerBound, Float upperBound) {
        return (lowerBound < number && number < upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenClosedRange(Float number, Float lowerBound, Float upperBound) {
        return (lowerBound < number && number <= upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedOpenRange(Float number, Float lowerBound, Float upperBound) {
        return (lowerBound <= number && number < upperBound);
    }
}
