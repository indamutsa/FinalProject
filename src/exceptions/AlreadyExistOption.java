package exceptions;

import wrapper.PizzeriaConfigAPI;
import util.StructExcp;

import java.util.logging.Level;

/*
 * This program handles the duplicate optionset
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class AlreadyExistOption extends PizzaException
{
    public AlreadyExistOption(String message)
    {
        super(message);
    }

    //This method is responsible of the program self-heal when duplicate optionset thrown
    @Override
    public void fixError(PizzeriaConfigAPI pizzeriaConfigAPI, StructExcp excp)
    {
        if (excp.getPizzaname() != null && excp.getOptionset() != null)
        {
            pizzeriaConfigAPI.addAnOption(excp.getPizzaname(), excp.getOptionset(), "New Optionname", 2564);
            logs.getLogger().log(Level.INFO, getMessage());
            System.out.println(getMessage());
        }
    }
}
