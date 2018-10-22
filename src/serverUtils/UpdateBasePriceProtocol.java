package serverUtils;
/**
 * This program updates the base price
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import util.Courrier;
import wrapper.PizzeriaAPI;
import wrapper.PizzeriaConfigAPI;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateBasePriceProtocol implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)    {
        try
        {
            List<String> listPizza = new ArrayList<>();
            listPizza = api.pizzaList();

            double baseprice = (double) cargo.getObject();
            int indexPizza = (int) cargo.getIndex();
            String pizzaname = listPizza.get(indexPizza);

            api.updateBasePrice(pizzaname, baseprice);
            oos.writeObject("  Base price successfully updated\n");
        } catch (Exception e)
        {
            System.out.println("Update base exception");
        }
    }
}
