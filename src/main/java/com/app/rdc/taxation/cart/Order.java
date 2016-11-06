package com.app.rdc.taxation.cart;

import com.app.rdc.taxation.item.objects.Item;

import java.util.List;

public class Order {
    private List<OrderLine> orderLines;

    public void clear() {

    }

    public void add(OrderLine o) {
        orderLines.add(o);
    }

    public double getTaxes() {
        return 0f;
    }

    public double getTotal() {
        return 0f;
    }
}
