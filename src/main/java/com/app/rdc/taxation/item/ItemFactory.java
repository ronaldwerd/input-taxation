package com.app.rdc.taxation.item;


import com.app.rdc.taxation.item.objects.*;

public class ItemFactory {

    private static final String medicalWords[] = {"pill"};
    private static final String foodWords[] = {"chocolate"};
    private static final String bookWords[] = {"book"};

    public static Item generateItem(String name, double price) {

        Item item;

        /*
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
        */

        item = new Generic();
        item.setName(name);
        item.setPrice(price);

        return item;
    }
}
