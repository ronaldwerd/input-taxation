package com.app.rdc.taxation.item;

public class ItemFactoryException extends Exception {
    public final static String TOO_SHORT = "Entry name was too short or does not make sense.";
    public final static String NOT_A_NOUN = "No noun was detected in your order string.";

    public ItemFactoryException(String message) {
        super(message);
    }
}
