package serverUtils;

import util.Courrier;
import wrapper.PizzeriaAPI;
import wrapper.PizzeriaConfigAPI;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * This program display the list df pizzeria
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


public class DisplayPizzeriaProtocol implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)    {
        try
        {
            List<String> listPizza = new ArrayList<>();
            listPizza = api.pizzaList();

            if (listPizza.size() != 0)
                oos.writeObject(listPizza);
            else
                oos.writeObject("  The list is empty, \n  Please load in items\n");



        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Display pizzeria exception");
        }
    }
}
