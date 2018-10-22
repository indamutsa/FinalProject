package serverUtils;
/**
 * This program displays option sets list
 *
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

public class DisplayOptionSet implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)    {
        try
        {
            List<String> listPizza = api.pizzaList();

            int indexPizza = (int) cargo.getPizzaNameIndex();

            String pizzaname = listPizza.get(indexPizza);

            List<String> listOptionSet = api.optionSetList(pizzaname);

            if (listOptionSet.size() != 0)
                oos.writeObject(listOptionSet);
            else
                oos.writeObject("  The list is empty, \n  Please load in items\n");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Display option exception");
        }
    }
}
