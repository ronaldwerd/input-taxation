import com.app.rdc.taxation.Dependencies;
import com.app.rdc.taxation.item.ItemFactory;
import com.app.rdc.taxation.item.ItemFactoryException;
import com.app.rdc.taxation.item.objects.Book;
import com.app.rdc.taxation.item.objects.Food;
import com.app.rdc.taxation.item.objects.Generic;
import com.app.rdc.taxation.item.objects.Item;
import edu.mit.jwi.IDictionary;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

public class ItemFactoryTests {

    private final static String[] genericOrderNames = {
            "imported bottle of perfume",
            "bottle of liquid nitrogen",
            "bookshelf speakers",
            "tin cans",
            "wooden trees"
    };

    private final static String[] foodOrderNames = {
            "bottle of ketchup Heinz",
            "lemons",
            "Nova Scotia imported lobsters",
            "lean ground beef",
            "rosemary herbs"
    };

    private final static String[] bookOrderNames = {
            "awful harlequin romance novels",
            "c++ programming book",
            "book regarding fine cuisine",
            "globe and mail newspaper",
            "men's health magazines"
    };

    private ItemFactory itemFactory;

    private String[] generalConcatAll(String[]...arrays) {

        String[] result = null;

        for(String[] array : arrays) {
            result = ArrayUtils.addAll(result, array);
        }

        return result;
    }


    @Before
    public void instantiateDependencies() throws Exception {
        IDictionary dictionary = Dependencies.loadDictionary();
        itemFactory = new ItemFactory(dictionary);
    }

    @Test
    public void genericItemTest() throws ItemFactoryException {

        for(String s : genericOrderNames) {
            Item i = itemFactory.generateItem(s, 9.95f);
            assert(i instanceof Generic);
        }
    }

    @Test
    public void bookItemTest() throws ItemFactoryException {
        for(String s : bookOrderNames) {
            Item i = itemFactory.generateItem(s, 9.95f);
            assert(i instanceof Book);
        }
    }

    @Test
    public void foodItemTest() throws ItemFactoryException {

        for(String s : foodOrderNames) {
            Item i = itemFactory.generateItem(s, 9.95f);
            assert(i instanceof Food);
        }
    }

    @Test
    public void medicalItemTest() {

    }

    @Test
    public void testImported() throws ItemFactoryException {

        String[] allOrderLines = generalConcatAll(genericOrderNames, foodOrderNames, bookOrderNames);

        for(String s : allOrderLines) {

            StringBuilder builder = new StringBuilder(s);

            Item i = itemFactory.generateItem("imported " + s, 9.95f);
            assert(i.isImported() == true);

            i = itemFactory.generateItem(s + " imported", 9.95f);
            assert(i.isImported() == true);
        }

        Item i;
        i = itemFactory.generateItem("Japanese imported java programming book", 9.95f);
        assert(i.isImported() == true);
        assert(i instanceof Book);

        i = itemFactory.generateItem("rich black imported italian truffles", 9.95f);
        assert(i.isImported() == true);
        assert(i instanceof Food);

        /*
         * This fails, goose contains food as one of its lexical categories. Further business rules
         * and smarter formatting would be required to fix this as to assume meta data such as brands
         * and other descriptors.
         */

        /*
        i = itemFactory.generateItem("canada goose imported coats", 9.95f);
        assert(i.isImported() == true);
        assert(i instanceof Generic);
        */
    }

    @Test
    public void generateExceptions() {

        /*
         * Unrecognizable giberish and bad characters
         */

        try {
            itemFactory.generateItem("bee zz ff ww 1234 %^%& ", 9.95f);
        } catch(ItemFactoryException ex) {
            assert(ex.getMessage().equals(ItemFactoryException.BAD_CHARACTERS));
        }


        /*
         * No noun in order name
         */

        try {
            itemFactory.generateItem("fast running red hot", 9.95f);
        } catch(ItemFactoryException ex) {
            assert(ex.getMessage().equals(ItemFactoryException.NO_NOUNS));
        }
    }
}