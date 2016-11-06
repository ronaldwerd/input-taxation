package com.app.rdc.taxation.cart;

import com.app.rdc.taxation.item.objects.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderLine {
    private int qty;
    private Item item;

    public OrderLine(int qty, Item item) {
        this.qty = qty;
        this.item = item;
    }

    /**
     * Calculates the tax of the order line by taking the tax of an item and multiply the quantity
     * @return the total taxation sum
     */
    public double getTotalTax() {
        return qty * item.getTax();
    }
}