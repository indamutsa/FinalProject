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

public class IncorrectOptionsetName extends PizzaException
{
    public IncorrectOptionsetName(String message)
    {
        super(message);
    }

    //This method is responsible of the program self-heal when an incorrect base price is thrown
    @Override
    public void fixError(PizzeriaConfigAPI pizzeriaConfigAPI, StructExcp excp)
    {
        if (excp.getPizzaname() != null)
            pizzeriaConfigAPI.addAnOptionset(excp.getPizzaname(), "Pizza sizes");
        logs.getLogger().log(Level.INFO, getMessage());
        System.out.println(getMessage() + "\nwe have updated it for you");
    }
}
