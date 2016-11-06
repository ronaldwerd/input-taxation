package com.app.rdc.taxation.cart;

import com.app.rdc.taxation.item.objects.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private int qty;
    private Item item;

    /**
     * Calculates the tax of the order line by taking the tax of an item and multiply the quantity
     * @return the total taxation sum
     */
    public float getTotalTax() {
        return qty * item.getTax();
    }
}