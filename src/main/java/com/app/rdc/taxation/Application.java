package com.app.rdc.taxation;

import com.app.rdc.taxation.cart.Order;
import com.app.rdc.taxation.cart.OrderLine;
import com.app.rdc.taxation.input.InputEvaluator;
import com.app.rdc.taxation.input.InputException;
import com.app.rdc.taxation.input.InputResult;
import com.app.rdc.taxation.item.ItemFactory;
import com.app.rdc.taxation.item.ItemFactoryException;
import com.app.rdc.taxation.item.objects.Item;

import java.util.Scanner;

public class Application {

    static Application application;

    public static Application getInstance() throws Exception {
        if(application == null) {
            application = new Application();
        }

        return application;
    }

    private final static char CURSOR = '>';

    private final static String WELCOME = "Welcome sales taxes calculator.";
    private final static String EXIT = "Good Bye.";
    private final static String ORDER_ADD = "Item added successfully";
    private final static String ORDER_CLEAR = "Order items cleared.";
    private final static String ORDER_EMPTY = "Your order is empty.";
    private final static String INPUT_ERROR = "Invalid command or order line.";
    private final static String HELP = "Commands are as follows:" +
            "exit:  Quits the application\n" +
            "help:  Prints this help information\n" +
            "show:  Displays the curremnt order\n" +
            "clear: Clears the current order\n" +
            "\n" +
            "Ordering examples:\n" +
            "1 book at 12.49\n" +
            "3 batteries at 39.95\n" +
            "2 imported bottles of perfume at 47.50";

    private ItemFactory itemFactory;
    private Order order;

    private Application() throws Exception {
        itemFactory = new ItemFactory(Dependencies.loadDictionary());
        order = new Order();
    }

    private void renderOrder(Order o) {

        if(o.getOrderLines().size() == 0) {
            System.out.println(ORDER_EMPTY);
            return;
        }

        for(OrderLine l : o.getOrderLines()) {
            double lineTotal = l.getTotalTax() + l.getTotalSum();
            System.out.printf(l.getQty() + " " + l.getItem().getName() + ": %.2f\n", lineTotal);
        }

        System.out.format("Sales taxes: %.2f\n", o.getTaxes());
        System.out.format("Total: %.2f\n", o.getTotal());
    }

    public void run() {

        System.out.println(WELCOME);
        System.out.println("\n");
        System.out.println(HELP);

        Scanner userInput = new Scanner(System.in);

        System.out.print(CURSOR + " ");

        applicationLoop: for(;;) {

            String input = userInput.nextLine().trim();

            switch(input) {

                case "exit":
                    System.out.println(EXIT);
                    break applicationLoop;

                case "help":
                    System.out.println(HELP);
                    break;

                case "show":
                    renderOrder(order);
                    break;

                case "clear":
                    order.clear();
                    System.out.println(ORDER_CLEAR);
                    break;

                default:

                    if(input.trim().length() > 0) {

                        try {
                            InputResult result = InputEvaluator.parseInputString(input);
                            Item item = itemFactory.generateItem(result.getName(), result.getPrice());
                            order.add(new OrderLine(result.getQuantity(), item));
                            System.out.println(ORDER_ADD);

                        } catch (InputException | ItemFactoryException e) {
                            System.out.println(INPUT_ERROR);
                        }
                    }
            }

            System.out.print("\n" + CURSOR + " ");
        }
    }
}