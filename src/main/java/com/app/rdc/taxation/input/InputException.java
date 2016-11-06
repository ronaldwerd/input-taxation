package com.app.rdc.taxation.input;

/**
 * Created by Ronald on 11/3/2016.
 */
public class InputException extends Exception {

    public final static String BAD_FORMAT = "Invalid order line format.";

    public InputException(String message) {
        super(message);
    }

}
