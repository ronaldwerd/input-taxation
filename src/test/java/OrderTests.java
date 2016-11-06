import com.app.rdc.taxation.cart.Order;
import com.app.rdc.taxation.cart.OrderLine;
import com.app.rdc.taxation.item.objects.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class OrderTests {

    private Order order;

    /*
        1 imported bottle of perfume at 27.99
        1 bottle of perfume at 18.99
        1 packet of headache pills at 9.75
        1 box of imported chocolates at 11.25
    */

    private Item importedPerfume;
    private Item perfume;
    private Item headachePills;
    private Item importedChocolates;

    private Item book;
    private Item food;
    private Item computerScreen;

    @Before
    public void setup() {

        importedPerfume = new Generic();
        importedPerfume.setName("imported bottle of perfume");
        importedPerfume.setImported(true);
        importedPerfume.setPrice(27.99f);

        perfume = new Generic();
        perfume.setName("bottle of perfume");
        perfume.setPrice(18.99f);

        headachePills = new Medical();
        headachePills.setName("packet of headache pills");
        headachePills.setPrice(9.75f);

        importedChocolates = new Generic();
        importedChocolates.setName("box of imported chocolates");
        importedChocolates.setImported(true);
        importedChocolates.setPrice(11.25f);

        //

        book = new Book();
        book.setName("cooking with bacon book");
        book.setPrice(14.95f);

        food = new Food();
        food.setName("large pepperoni pizza");
        food.setPrice(12.95f);

        computerScreen = new Generic();
        computerScreen.setName("imported 27 inch ips flat panel");
        computerScreen.setPrice(519.95f);

        order = new Order();
    }

    @Test
    public void itemTaxation() {

        assert(importedPerfume.getTax() == 4.20);
        assert(perfume.getTax() == 1.90);
        assert(headachePills.getTax() == 0f);
        assert(importedChocolates.getTax() == 0.6);
        //
        assert(book.getTax() == 0f);
        assert(food.getTax() == 0f);
        assert(computerScreen.getTax() == 52f);

        perfume.setImported(true);
        headachePills.setImported(true);
        //
        computerScreen.setImported(true);
        book.setImported(true);
        food.setImported(true);

        assert(perfume.getTax() == 2.85f);
        assert(headachePills.getTax() == 0.5f);
        assert(computerScreen.getTax() == 78f);
        assert(food.getTax() == 0.79f);
    }

    @Test
    public void itemCostSummary() {

        Order order = new Order();
        order.add(new OrderLine(1, importedPerfume));
        order.add(new OrderLine(1, perfume));
        order.add(new OrderLine(1, headachePills));
        order.add(new OrderLine(1, importedChocolates));
    }
}
