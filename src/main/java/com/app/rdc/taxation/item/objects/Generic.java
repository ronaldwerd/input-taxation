package com.app.rdc.taxation.item.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Generic implements Item {
    private String name;
    private double price;
    private boolean imported;

    public double getTax() {

        if(imported == false)
            return price * Taxation.SALES_TAX;

        return price * (Taxation.IMPORT_TAX + Taxation.SALES_TAX);
    }
}
