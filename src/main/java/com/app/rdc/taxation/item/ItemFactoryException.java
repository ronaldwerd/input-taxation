package com.app.rdc.taxation.item;

public class ItemFactoryException extends Exception {
    public final static String BAD_CHARACTERS  = "Name must contain letters and spaces only";
    public final static String NO_NOUNS = "No noun was detected in your order string.";

    public ItemFactoryException(String message) {
        super(message);
    }
}
