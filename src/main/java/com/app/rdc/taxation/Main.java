package com.app.rdc.taxation;

import com.app.rdc.taxation.input.InputEvaluator;
import com.app.rdc.taxation.input.InputException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        try {
            //InputEvaluator.parseInputString("65|35");
            //InputEvaluator.parseInputString("65|Ronald");
            //InputEvaluator.parseInputString("65 Ronald Partridge");
            InputEvaluator.parseInputString("65 Imported beer at 1036.95");
        } catch(InputException e) {

        }
    }
}


/*
    1 imported bottle of perfume at 27.99
    1 bottle of perfume at 18.99
    1 packet of headache pills at 9.75
    1 box of imported chocolates at 11.25
*/