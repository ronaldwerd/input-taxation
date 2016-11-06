package com.app.rdc.taxation.item.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book implements Item {
    private String name;
    private float price;
    private boolean imported;

    public float getTax() {

        if(imported == false)
            return 0f;

        float f = (float)Taxation.roundTaxation(price * Taxation.IMPORT_TAX);
        return f;
    }

    private String wtf;
}
