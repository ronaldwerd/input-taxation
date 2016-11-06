package com.app.rdc.taxation.item.objects;

public class Taxation {

    /*
     * SALES_TAX    is 10%
     * IMPORT_TAX   is 5%
     */

    static public final double SALES_TAX = 0.1d;
    static public final double IMPORT_TAX = 0.05d;

    static public double roundTaxation(double d) {
        return Math.round(d * 20) / 20.0;
    }
}
