import com.app.rdc.taxation.cart.Order;
import com.app.rdc.taxation.item.objects.Book;
import com.app.rdc.taxation.item.objects.Generic;
import com.app.rdc.taxation.item.objects.Item;
import com.app.rdc.taxation.item.objects.Taxation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class OrderTests {

    private Order order;

    @Before
    public void setup() {
        order = new Order();
    }

    @Test
    public void itemTaxation() {
        Item book = new Book();
        book.setName("cooking with bacon book");
        book.setPrice(14.95f);
        assert(book.getTax() == 0f);

        book.setImported(true);
        assert(book.getTax() == 0.75f);

        Item generic = new Generic();
        generic.setName("imported 27 inch ips flat panel");
        generic.setPrice(519.95f);
        assert(generic.getTax() == 52f);

        generic.setImported(true);
        assert(generic.getTax() == 78f);
    }

    @Test
    public void itemCostSummary() {

    }
}
