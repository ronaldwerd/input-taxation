package com.app.rdc.taxation.item.objects;

public interface Item {
    String getName();
    void setName(String name);

    double getPrice();
    void setPrice(double price);

    boolean isImported();
    void setImported(boolean imported);

    double getTax();
}