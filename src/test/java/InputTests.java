import com.app.rdc.taxation.input.InputEvaluator;
import com.app.rdc.taxation.input.InputException;
import com.app.rdc.taxation.input.InputResult;
import org.junit.Test;

public class InputTests {

    @Test
    public void inputResult() {

        String[] orderLines = {
                " 11 imported bottle of perfume at 27.99 ",
                " 1 chocolate bar at 0.85",
                "   677 packet of headache pills at 9.75 ",
                " 34 box of imported chocolates at 11.25"
        };

        try {

            InputResult r;

            r = InputEvaluator.parseInputString(orderLines[0]);
            assert(r.getName().equals("imported bottle of perfume"));
            assert(r.getQuantity() == 11);
            assert(r.getPrice() == 27.99f);

            r = InputEvaluator.parseInputString(orderLines[1]);
            assert(r.getName().equals("chocolate bar"));
            assert(r.getQuantity() == 1);
            assert(r.getPrice() == 0.85f);

            r = InputEvaluator.parseInputString(orderLines[2]);
            assert(r.getName().equals("packet of headache pills"));
            assert(r.getQuantity() == 677);
            assert(r.getPrice() == 9.75f);

            r = InputEvaluator.parseInputString(orderLines[3]);
            assert(r.getName().equals("box of imported chocolates"));
            assert(r.getQuantity() == 34);
            assert(r.getPrice() == 11.25f);


        } catch(Exception ex) {

        }
    }

    @Test
    public void inputResultException() {

        String[] badOrderLines = {
                "  ",
                "chocolate bar at 0.85",
                "677 packet of headache pills 9.75 ",
                " 34 at 11.25"
        };

        for(String s : badOrderLines) {
            try {
                InputEvaluator.parseInputString(s);
            } catch(InputException ex) {
                assert(ex.getMessage().equals(InputException.BAD_FORMAT));
            }
        }
    }
}
