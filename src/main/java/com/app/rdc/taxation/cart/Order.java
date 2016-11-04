package com.app.rdc.taxation.cart;

import com.app.rdc.taxation.item.objects.Item;

import java.util.List;

/**
 * Created by Ronald on 11/3/2016.
 */
public class Order {
    private List<Item> itemList;

    public void clear() {

    }

    public void add(Item i) {
        itemList.add(i);
    }

    public double getTaxes() {
        return 0f;
    }

    public double getTotal() {
        return 0f;
    }
}
