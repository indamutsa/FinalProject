package serverUtils;

import model.PizzaConfig;
import util.Courrier;
import wrapper.PizzeriaAPI;
import wrapper.PizzeriaConfigAPI;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * This program prints a given pizza
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class PrintPizzeriaProtocol implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)    {
        try
        {
            List<String> listPizza = api.pizzaList();

            int indexPizza = (Integer) cargo.getObject();
            String pizzaName = listPizza.get(indexPizza);

            PizzaConfig pizzaConfig = api.retrivePizza(pizzaName);
            oos.writeObject(pizzaConfig);

        } catch (Exception e)
        {
            System.out.println("Print pizza option");
        }
    }
}
