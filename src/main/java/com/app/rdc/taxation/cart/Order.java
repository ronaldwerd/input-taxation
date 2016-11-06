package com.app.rdc.taxation.cart;

import java.util.List;

public class Order {
    private List<OrderLine> orderLines;

    /**
     * Removes all of the order lines within the order
     */

    public void clear() {
        orderLines.clear();
    }

    /**
     * Add an order line to the order object
     * @param orderLine the order line to be added to the order
     */

    public void add(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    /**
     * Calculates all of the taxes in the order
     * @return the total amount of taxes calculated on all items based on the items own tax calculation
     */

    public double getTaxes() {
        return orderLines.stream().mapToDouble(o -> o.getTotalTax()).sum();
    }

    /**
     * Calculates the entire order amount
     * @return the entire order total including app applicable taxes
     */

    public double getTotal() {
        return 0f;
    }
}
