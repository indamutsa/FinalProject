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

public class AlreadyExistConfig extends PizzaException
{
    public AlreadyExistConfig(String message)
    {
        super(message);
    }

    @Override
    public void fixError(PizzeriaConfigAPI pizzeriaConfigAPI, StructExcp excp)
    {
//        pizzeriaConfigAPI.createPizzeria("New Margeritta", 690);
        logs.getLogger().log(Level.INFO, getMessage());
        System.out.println(getMessage() + "\nThe pizza name already exist");
    }
}