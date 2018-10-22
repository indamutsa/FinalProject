package exceptions;

import wrapper.PizzeriaConfigAPI;
import util.StructExcp;

public abstract class PizzaException extends Exception
{
    protected ExceptionLogs logs;
    public PizzaException(String message)
    {
        super(message);
        try
        {
           logs = ExceptionLogs.getInstance("logs.txt");
           logs.getLogger().setUseParentHandlers(false);
        }
        catch (Exception e)
        { }
    }

    //Fix the error thrown by the program
    public abstract void fixError(PizzeriaConfigAPI pizzeriaConfigAPI, StructExcp excp);
}
