package serverUtils;

/**
 * Calculate total price
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
import java.util.Arrays;
import java.util.List;

public class CalculatePrice implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)
    {
        try
        {
            List<String> listPizza = api.pizzaList();
            System.out.println("The list of pizza: "+listPizza);

            String pizzaName = (String) cargo.getObject();
            System.out.println("The pizza name: ===> " + pizzaName);

            String selectedElements[] = (String[]) cargo.getSelectedElements();
            System.out.println("Array:  "+Arrays.toString(selectedElements));

            double totalPrice = api.calculatePrice(pizzaName, selectedElements);
            System.out.println("Total price: ==>  " + totalPrice);
            oos.writeObject(totalPrice);

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Calcultate price exception");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
