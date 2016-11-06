package com.app.rdc.taxation.item.objects;

public class Taxation {

    /*
     * SALES_TAX    is 10%
     * IMPORT_TAX   is 5%
     */

    static public final double SALES_TAX = 0.1d;
    static public final double IMPORT_TAX = 0.05d;

    static public double roundToSecondDecimal(double d) {
        return Math.round(d * 100) / 100.0;
    }

    static public double roundTaxation(double d) {
        return roundToSecondDecimal(Math.ceil(d / 0.05d) * 0.05d);
    }
}
