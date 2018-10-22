package exceptions;

import wrapper.PizzeriaConfigAPI;

public class IncorrectFileformat extends Exception
{
    public IncorrectFileformat(String message)
    {
        super(message);
    }

    //This method is responsible of the program self-heal when an incorrect file format
    public void fixError(PizzeriaConfigAPI buildPizza)
    {
        buildPizza.configurePizzeria("recovery.txt");
        System.out.println(getMessage() + "\nwe have updated it for you");
    }
}
