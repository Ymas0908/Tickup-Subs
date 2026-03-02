package org.tickup.adapters.utils;


/**
 * @author Kassi
 */
public class IntRangeOperators {

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedRange(Integer number, Integer lowerBound, Integer upperBound) {
        return (lowerBound <= number && number <= upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenRange(Integer number, Integer lowerBound, Integer upperBound) {
        return (lowerBound < number && number < upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInOpenClosedRange(Integer number, Integer lowerBound, Integer upperBound) {
        return (lowerBound < number && number <= upperBound);
    }

    /**
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean isInClosedOpenRange(Integer number, Integer lowerBound, Integer upperBound) {
        return (lowerBound <= number && number < upperBound);
    }
}
