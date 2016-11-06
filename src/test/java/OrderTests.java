import com.app.rdc.taxation.cart.Order;
import com.app.rdc.taxation.cart.OrderLine;
import com.app.rdc.taxation.item.objects.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class OrderTests {

    private Order order;

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
        importedPerfume.setPrice(27.99d);

        perfume = new Generic();
        perfume.setName("bottle of perfume");
        perfume.setPrice(18.99d);

        headachePills = new Medical();
        headachePills.setName("packet of headache pills");
        headachePills.setPrice(9.75d);

        importedChocolates = new Food();
        importedChocolates.setName("box of imported chocolates");
        importedChocolates.setImported(true);
        importedChocolates.setPrice(11.25d);

        //

        book = new Book();
        book.setName("cooking with bacon book");
        book.setPrice(14.95d);

        food = new Food();
        food.setName("large pepperoni pizza");
        food.setPrice(12.95d);

        computerScreen = new Generic();
        computerScreen.setName("imported 27 inch ips flat panel");
        computerScreen.setPrice(519.95d);

        order = new Order();
    }

    @Test
    public void itemTaxation() {


        assert(importedPerfume.getTax() == 4.20d);
        assert(perfume.getTax() == 1.90d);
        assert(headachePills.getTax() == 0d);
        assert(importedChocolates.getTax() == 0.6d);
        //
        assert(book.getTax() == 0d);
        assert(food.getTax() == 0d);
        assert(computerScreen.getTax() == 52d);

        perfume.setImported(true);
        headachePills.setImported(true);
        //
        computerScreen.setImported(true);
        book.setImported(true);
        food.setImported(true);

        assert(perfume.getTax() == 2.85d);
        assert(headachePills.getTax() == 0.5d);
        assert(computerScreen.getTax() == 78d);
        assert(food.getTax() == 0.65d);

    }

    @Test
    public void itemCostSummary() {

        Order order = new Order();
        order.add(new OrderLine(1, importedPerfume));
        order.add(new OrderLine(1, perfume));
        order.add(new OrderLine(1, headachePills));
        order.add(new OrderLine(1, importedChocolates));

        assert(order.getTaxes() == 6.7d);
        assert(order.getTotal() == 74.68d);
    }
}
