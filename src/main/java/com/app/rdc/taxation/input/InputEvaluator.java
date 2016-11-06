package com.app.rdc.taxation.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputEvaluator {

    private final static Pattern quantityPattern = Pattern.compile("(^[0-9]*)\\s([a-z\\s]*)at\\s([0-9]*\\.[0-9]{2})", Pattern.CASE_INSENSITIVE);

    private InputEvaluator() {}

    /**
     * Parse a humanized string input in the following format:
     * (qty) "string name" at (price)
     * Examples:
     * 1 bottle of perfume at 18.99
     * 3 packets of headache pills at 9.75
     * 10 steak knifes at 49.95
     * @param input is the order string
     */

    public static InputResult parseInputString(String input) throws InputException {

        InputResult result = new InputResult();

        Matcher m = quantityPattern.matcher(input.trim());

        if(m.find()) {
            result.setQuantity(Integer.parseInt(m.group(1)));
            result.setName(m.group(2).trim());
            result.setPrice(Float.parseFloat(m.group(3)));
        } else {
            throw new InputException(InputException.BAD_FORMAT);
        }

        result.setImported(input.toLowerCase().contains("imported"));

        return result;
    }
}
