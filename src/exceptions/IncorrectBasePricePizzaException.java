package exceptions;

import wrapper.PizzeriaConfigAPI;
import util.StructExcp;

import java.util.logging.Level;

/*
 * This program handles the incorrect pizza base price
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class IncorrectBasePricePizzaException extends PizzaException
{
    public IncorrectBasePricePizzaException(String message)
    {
        super(message);
    }

    //This method is responsible of the program self-heal when an incorrect base price is thrown
    @Override
    public void fixError(PizzeriaConfigAPI pizzeriaConfigAPI, StructExcp excp)
    {
        if (excp.getPizzaname() != null)
        {
            pizzeriaConfigAPI.createPizzeria(excp.getPizzaname(), 690);
            logs.getLogger().log(Level.INFO, getMessage());
            System.out.println(getMessage() + "\nwe have updated it for you");
        }
    }
}
