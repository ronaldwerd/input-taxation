package com.app.rdc.taxation.item.objects;


public interface Item {
    public String getName();
    public void setName(String name);

    public double getPrice();
    public void setPrice(double price);

    public boolean isImported();
    public void setImported(boolean imported);

    public double getTax();
}