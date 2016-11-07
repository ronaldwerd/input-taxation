package com.app.rdc.taxation.cart;

import com.app.rdc.taxation.item.objects.Taxation;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Order {

    @Getter
    private List<OrderLine> orderLines;

    public Order() {
        orderLines = new LinkedList<>();
    }

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
        return Taxation.roundTaxation(orderLines.stream().mapToDouble(o -> o.getTotalTax()).sum());
    }

    /**
     * Calculates the entire order amount
     * @return the entire order total including app applicable taxes
     */

    public double getTotal() {
        return Taxation.roundToSecondDecimal(getTaxes() + orderLines.stream().mapToDouble(o -> o.getTotalSum()).sum());
    }
}
