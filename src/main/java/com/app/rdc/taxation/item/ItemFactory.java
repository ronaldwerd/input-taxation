package com.app.rdc.taxation.item;


import com.app.rdc.taxation.item.objects.*;

public class ItemFactory {

    public static Item generateItem(String name, String type, double price) {

        Item item;

        /*
         * If we have more granular requirements for specific attributes and items later, we can use this factory
         * pattern to create these.
         */

        switch(type) {

            case "medical":
                item = new Medical();

            case "book":
                item = new Book();
                break;

            case "food":
                item = new Food();
                break;

            default:
                item = new Generic();
                break;
        }

        item.setName(name);
        item.setPrice(price);

        return item;
    }
}
