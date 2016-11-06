package com.app.rdc.taxation;

import com.app.rdc.taxation.cart.Order;
import com.app.rdc.taxation.input.InputEvaluator;
import com.app.rdc.taxation.input.InputException;
import com.app.rdc.taxation.input.InputResult;
import com.app.rdc.taxation.item.ItemFactory;
import com.app.rdc.taxation.item.ItemFactoryException;
import com.app.rdc.taxation.item.objects.Item;
import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {

    static Application application;

    public static Application getInstance() throws Exception {
        if(application == null) {
            application = new Application();
        }

        return application;
    }

    private final static String WORDNET_LOCATION = File.separator + "resources" + File.separator + "wordnet" + File.separator + "dict";
    private final static char CURSOR = '>';

    private final static String WELCOME = "Welcome sales taxes calculator.";
    private final static String EXIT = "Good Bye.";
    private final static String CLEAR = "Order items cleared.";
    private final static String INPUT_ERROR = "Invalid command or order line.";
    private final static String HELP = "Commands are as follows:" +
            "exit:  Quits the application\n" +
            "help:  Prints this help information\n" +
            "clear: Clears the current order\n" +
            "\n" +
            "Ordering examples:\n" +
            "1 book at 12.49\n" +
            "3 batteries at 39.95\n" +
            "2 imported bottles of perfume at 47.50";

    private ItemFactory itemFactory;
    private Order order;

    private IDictionary loadDictionary() throws IOException {
        Path currentRelativePath = Paths.get("");
        String wordNetUrl = currentRelativePath.toAbsolutePath().toString() + WORDNET_LOCATION;

        URL url;
        url = new URL("file", null, wordNetUrl);

        IDictionary dictionary = new Dictionary(url);

        if(dictionary.open() == false)
            throw new IOException("Unable to open wordnet dictionary, check resource installation");

        return dictionary;
    }

    private Application() throws Exception {
        itemFactory = new ItemFactory(loadDictionary());
        order = new Order();
    }

    public void run() {

        try {

            String[] orderLines = {
                    "1 imported bottle of perfume at 47.50",
                    "1 bottle of ketchup Heinz at 4.95",
                    "7 lemons at 2.37",
                    "31 cans of coca cola at 0.79",
                    "2 books of lore at 9.95"
            };

            for(String s: orderLines) {
                InputResult result = InputEvaluator.parseInputString(s);
                Item item = itemFactory.generateItem(result.getName(), result.getPrice());
                String foo = "1";
            }

        } catch (InputException | ItemFactoryException e) {
            e.printStackTrace();
        }

        /*
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

                case "clear":
                    order = new Order();
                    System.out.println(CLEAR);
                    break;

                default:
                    try {
                        InputResult result = InputEvaluator.parseInputString(input);
                        itemFactory.generateItem(result.getName(), result.getPrice());

                    } catch (InputException e) {
                        System.out.println(INPUT_ERROR);
                    }

                    break;
            }

            System.out.print("\n" + CURSOR + " ");
        }
        */
    }
}