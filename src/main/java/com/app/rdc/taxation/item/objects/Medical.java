package com.app.rdc.taxation.item.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medical implements Item {
    private String name;
    private double price;
    private boolean imported;

    public double getTax() {
        return 0f;
    }
}
