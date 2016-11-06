package com.app.rdc.taxation.item.objects;

/**
 * Created by Ronald on 11/6/2016.
 */
public class Taxation {
    static public final float SALES_TAX = 0.1f; // 10%
    static public final float IMPORT_TAX = 0.05f;

    static public double roundTaxation(float f) {
        return Math.round(f * 20) / 20.0;
    }
}
