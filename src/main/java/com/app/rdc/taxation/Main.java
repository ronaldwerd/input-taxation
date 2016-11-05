package com.app.rdc.taxation;

public class Main {

    public static void main(String[] args) {

        try {

            Application app = Application.getInstance();
            app.run();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}