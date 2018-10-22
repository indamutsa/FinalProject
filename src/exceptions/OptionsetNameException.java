package exceptions;

import wrapper.PizzeriaConfigAPI;
import util.StructExcp;

import java.util.logging.Level;

/*
 * This program handles the incorrect optionset name
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class OptionsetNameException extends PizzaException
{
    public OptionsetNameException(String message)
    {
        super(message);
    }

    //This method is responsible of the program self-heal when an incorrect optionset name is thrown
    @Override
    public void fixError(PizzeriaConfigAPI pizzeriaConfigAPI, StructExcp excp)
    {
        if (excp.getPizzaname() != null)
        {
            pizzeriaConfigAPI.addAnOptionset(excp.getPizzaname(), "Pizza sizes");
            logs.getLogger().log(Level.INFO, getMessage());
            System.out.println(getMessage() + "\nwe have updated it for you");
        }
    }
}
