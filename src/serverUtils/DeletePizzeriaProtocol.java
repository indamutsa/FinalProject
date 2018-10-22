package serverUtils;
/**
 * This program delete a pizzeria
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

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DeletePizzeriaProtocol implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)
    {
        try
        {
            List<String> listPizza = new ArrayList<>();
            listPizza = api.pizzaList();

            int indexPizza = (Integer) cargo.getObject();
            String pizzaname = listPizza.get(indexPizza);

            api.deletePizzeria(pizzaname);
            oos.writeObject("  " + pizzaname + " successfully deleted\n");
        } catch (Exception e)
        {
            System.out.println("Delete exception");
        }
    }
}
