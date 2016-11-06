package com.app.rdc.taxation.cart;

import com.app.rdc.taxation.item.objects.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private int qty;
    private Item item;
}
