package wrapper;
/**
 * Database api
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import db.DatabaseHandler;
import model.PizzaConfig;
import util.StructOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbProxyPizzeria
{
    private DatabaseHandler dbHandler;

    public void createPizzeria(String pizzeriaName, double price)
    {

    }


    public boolean configurePizzeria(String filename)
    {
        return false;
    }


    public boolean configurePizzeria(Properties properties)
    {
        dbHandler = new DatabaseHandler();
        return dbHandler.configurePizzeria(properties);
    }


    public void printPizzeria(String pizzeriaName)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.printPizzeria(pizzeriaName);
    }


    public void addAnOptionset(String pizzeriaName, String optionsetName)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.addAnOptionset(pizzeriaName, optionsetName);
    }


    public void addAnOption(String pizzeriaName, String optionsetName, String optionName, double price)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.addAnOption(pizzeriaName, optionsetName, optionName, price);
    }


    public void printPizzerias()
    {
        dbHandler = new DatabaseHandler();
        dbHandler.printPizzerias();
    }


    public void deletePizzeria(String key)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.deletePizzeria(key);
    }


    public List<String> pizzaList()
    {
        dbHandler = new DatabaseHandler();
        return dbHandler.pizzaList();
    }


    public void updateOptionSetName(String pizzeriaName, String optionSetName, String newOptionSetName)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.updateOptionSetName(pizzeriaName, optionSetName, newOptionSetName);
    }


    public void updateBasePrice(String pizzeriaName, double newBasePrice)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.updateBasePrice(pizzeriaName, newBasePrice);
    }


    public void updateOptionPrice(String pizzeriaName, String optionSetName, String optionName, double newOptionPrice)
    {

    }


    public void optionUpdater(String pizzeriaName, String optionSetName, String optionName, String newoptionName, double newOptionPrice)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.optionUpdater(pizzeriaName, optionSetName, optionName, newoptionName, newOptionPrice);
    }


    public void updatePizzeriaName(String pizzeriaName, String newPizzeriaName)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.updatePizzeriaName(pizzeriaName, newPizzeriaName);
    }


    public void deleteOptionSet(String pizzeriaName, String optionset)
    {
        dbHandler = new DatabaseHandler();
        dbHandler.deleteOptionSet(pizzeriaName, optionset);
    }


    public PizzaConfig retrivePizza(String pizzeriaName)
    {
        dbHandler = new DatabaseHandler();
        return dbHandler.retrivePizza(pizzeriaName);
    }


    public List<String> optionSetList(String pizzeriaName)
    {
        dbHandler = new DatabaseHandler();
        return dbHandler.optionSetList(pizzeriaName);
    }

    public double calculatePrice(String pizzaname,String[] array)
    {
        double totalPrice = 0;
        PizzaConfig pizzaConfig = retrivePizza(pizzaname);
        System.out.println("===>:  " + pizzaConfig);
        List<String> optionsets = pizzaConfig.getOptionSets();
        System.out.println("===>: " + optionsets);
        ArrayList<StructOption> option;

        int i = 0;
        for (String optionset : optionsets)
        {
            option = pizzaConfig.getListOption(optionset);
            for(StructOption opt : option)
            {
                System.out.println("===>: "+opt);
                if(opt.getName().equals(array[i]))
                {
                    totalPrice = totalPrice + opt.getPrice();
                    i++;
                }
                System.out.println("===>: "+totalPrice);
            }
            i= 0;
        }
        totalPrice = totalPrice + pizzaConfig.getBasePrice();
        System.out.println("===>: "+totalPrice);

        return totalPrice;
    }
}

