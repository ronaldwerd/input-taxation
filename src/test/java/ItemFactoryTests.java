import com.app.rdc.taxation.Dependencies;
import com.app.rdc.taxation.input.InputEvaluator;
import com.app.rdc.taxation.input.InputException;
import com.app.rdc.taxation.input.InputResult;
import com.app.rdc.taxation.item.ItemFactory;
import com.app.rdc.taxation.item.ItemFactoryException;
import com.app.rdc.taxation.item.objects.Food;
import com.app.rdc.taxation.item.objects.Generic;
import com.app.rdc.taxation.item.objects.Item;
import edu.mit.jwi.IDictionary;
import org.junit.Before;
import org.junit.Test;

public class ItemFactoryTests {

    private ItemFactory itemFactory;

    @Before
    public void instantiateDependencies() throws Exception {
        IDictionary dictionary = Dependencies.loadDictionary();
        itemFactory = new ItemFactory(dictionary);
    }

    @Test
    public void genericItemTest() {

        String[] orderNames = {
            "imported bottle of perfume",
            "bottle of liquid nitrogen",
            "bookshelf speakers",
            "tin cans",
            "wooden trees"
        };

        try {

            for(String s : orderNames) {
                Item i = itemFactory.generateItem(s, 9.95f);
                assert(i instanceof Generic);
            }

        } catch(ItemFactoryException ex) {

        }

    }

    @Test
    public void bookItemTest() {

    }

    @Test
    public void foodItemTest() {

        String[] orderNames = {
                "bottle of ketchup Heinz",
                "lemons",
                "Nova Scotia lobsters",
                "lean ground beef",
                "rosemary herbs"
        };

        try {

            for(String s : orderNames) {
                Item i = itemFactory.generateItem(s, 9.95f);
                assert(i instanceof Food);
            }

        } catch(ItemFactoryException ex) {

        }

        /*
        try {

            String[] orderLines = {
                    "1 imported bottle of perfume at 47.50",
                    "1 bottle of ketchup Heinz at 4.95",
                    "7 lemons at 2.37",
                    "31 cans of coca cola at 0.79",
                    "2 books of lore at 9.95"
            };

            for (String s : orderLines) {
                InputResult result = InputEvaluator.parseInputString(s);
                Item item = itemFactory.generateItem(result.getName(), result.getPrice());
                String foo = "1";
            }

        } catch (InputException | ItemFactoryException e) {
            e.printStackTrace();
        }
        */

    }

    @Test
    public void medicalItemTest() {

    }

    @Test
    public void generateExceptions() {

    }
}
