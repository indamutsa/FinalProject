package serverUtils;
/**
 * This program add an option to an optionset of a pizza
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

public class OptionAdditionProtocol implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)    {
        try
        {
            List<String> listPizza = api.pizzaList();

            int indexPizza = (int) cargo.getPizzaNameIndex();
            int indexOption = (int) cargo.getOptionsetIndex();

            String optionName = (String) cargo.getOptionName();
            double optionPrice = (double) cargo.getPrice();

            String pizzaname = listPizza.get(indexPizza);

            List<String> listOptionSet = api.optionSetList(pizzaname);
            String optionsetName = listOptionSet.get(indexOption);

            api.addAnOption(pizzaname, optionsetName, optionName, optionPrice);
            oos.writeObject("   "+optionName + " has been successfully added\n");

        } catch (Exception e)
        {
            System.out.println("Adding option exception");
        }
    }
}
