package wrapper;

/*
 * This program enables the programmer to access the Linkedhashmap  API for pizzeria
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class PizzeriaConfigAPI
        extends LHMProxyPizzeria implements PizzeriaAPI
{
    private static PizzeriaConfigAPI instance;

    private PizzeriaConfigAPI()
    {
    }

    public static PizzeriaConfigAPI getInstance()
    {
        if (instance == null)
        {
            instance = new PizzeriaConfigAPI();
        }

        return instance;
    }

    @Override
    public String className()
    {
        return getClass().getName();
    }
}
