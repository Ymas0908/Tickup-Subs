package org.tickup.adapters.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class AmountFormater {

    private static final DecimalFormat dfZero = new DecimalFormat("0.00");
    private static final DecimalFormat decimalFormat = new DecimalFormat("###.##");
    private static NumberFormat xofFormat;

    static {
        Locale civ = new Locale("fr", "CI");
        xofFormat = NumberFormat.getCurrencyInstance(civ);
    }

    public static String format(BigDecimal value) {
        return null != value ? xofFormat.format(value.doubleValue()) : null;
    }

    /**
     * Formatter les nombres à virgule à 2 chiffre après la virgule.
     *
     * @param value
     * @return
     */
    public static String format(Float value) {
        return null != value ? dfZero.format(value) : null;
    }

    /**
     * Formatter un nombre sous forme simple (sans decimal / sans notation
     * scientifique)
     *
     * @param value
     * @return
     */
    public static String zeroFreeFormat(Double value) {
        return null != value ? (new BigDecimal(value)).toString() : null;
    }

    /**
     * @param value
     * @return
     */
    public static String format(Double value) {
        return null != value ? decimalFormat.format(value) : null;
    }
}
