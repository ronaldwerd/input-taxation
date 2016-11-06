package com.app.rdc.taxation.input;

public class InputException extends Exception {

    public final static String BAD_FORMAT = "Invalid order line format.";

    public InputException(String message) {
        super(message);
    }
}
