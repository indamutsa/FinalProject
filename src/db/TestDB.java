package db;

/**
 * Database test
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */
import client.Client;
import wrapper.DbProxyPizzeria;
import java.util.ArrayList;


public class TestDB
{
    public static void main(String[] args)
    {
        DatabaseHandler dbHandler = new DatabaseHandler();
//        System.out.println(dbHandler.pizzaList());
//
//        dbHandler.optionUpdater("Carbonara", "Meat choices", "Beef", "New Beef", 1);
//        dbHandler.printPizzeria("Carbonara");
//        dbHandler.deletePizzeria("Carbonara");
//
//        dbHandler.optionSetList("Quattro Stagioni");
//        System.out.println(dbHandler.optionSetList("Quattro Stagioni"));
//
//        Client client = new Client();
//        ArrayList<String> listPizza;

//        System.out.println(client.pizzeriaList());

        String [] a  = {"Extra-Large", "Pepperoni", "oregano", "Manchego", "Delivered"};
        String p = "Napoletana";

        System.out.println(new Client().retrievePizza(0));
//        System.out.println(new Client().showOptionSets(p));
    }
}
