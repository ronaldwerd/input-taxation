package com.app.rdc.taxation.item.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book implements Item {
    private String name;
    private double price;
    private boolean imported;

    public double getTax() {

        if(imported == false)
            return 0f;

        return price * Taxation.IMPORT_TAX;
    }

    private String wtf;
}
