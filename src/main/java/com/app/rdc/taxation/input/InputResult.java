package com.app.rdc.taxation.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputResult {
    private int quantity;
    private String name;
    private float price;
    private boolean imported;
}