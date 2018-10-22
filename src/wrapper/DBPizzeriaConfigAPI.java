package wrapper;

/*
 * This program enables the programmer to access the DB API for pizzeria
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class DBPizzeriaConfigAPI
        extends DbProxyPizzeria implements PizzeriaAPI
{

    private static DBPizzeriaConfigAPI instance;

    private DBPizzeriaConfigAPI()
    {
    }

    public static DBPizzeriaConfigAPI getInstance()
    {
        if (instance == null)
        {
            instance = new DBPizzeriaConfigAPI();
        }
        return instance;
    }

    @Override
    public String className()
    {
        return getClass().getName();
    }
}


