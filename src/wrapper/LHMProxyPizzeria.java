package wrapper;

import exceptions.ExceptionFactory;
import exceptions.PizzaException;
import io.BuildPizzaConfig;
import model.PizzaConfig;
import util.StructExcp;
import util.StructOption;

import java.util.*;

/*
 * This program implements the proxy design pattern
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */


public abstract class LHMProxyPizzeria
{
    private static LinkedHashMap<String, PizzaConfig> configs = new LinkedHashMap<>();
    private BuildPizzaConfig buildPizza = new BuildPizzaConfig();
    private StructExcp excp;


    public LinkedHashMap<String, PizzaConfig> print()
    {
        return configs;
    }

    /*This method saves Pizzeria configuration*/
    public synchronized void createPizzeria(String pizzeriaName, double basePrice)
    {
        pizzeriaName = pizzeriaName.trim();
        PizzaConfig pizzaConfig = new PizzaConfig(pizzeriaName);
        StructExcp excp = new StructExcp(pizzeriaName, null, "null", basePrice);

        try
        {
            if (pizzaConfig.validate(pizzeriaName, basePrice))
                pizzaConfig.setBasePrice(basePrice);

            if (!duplicateChecker(configs, pizzeriaName))
                return;

        } catch (PizzaException e)
        {
            e.fixError(PizzeriaConfigAPI.getInstance(), excp);
        }
    }

    /*This method shall configure the pizzeria through a file*/
    public synchronized boolean configurePizzeria(String filename)
    {
        List<PizzaConfig> configArrayList = buildPizza.buildPizzaConfig(filename);

        if (configArrayList == null)
            return false;
        else
        {
            for (PizzaConfig pizzaConfig : configArrayList)
            {
                try
                {
                    if (!duplicateChecker(configs, pizzaConfig.getPizzaName()))
                        configs.put(pizzaConfig.getPizzaName(), pizzaConfig);
                } catch (PizzaException e)
                {
                    e.fixError(PizzeriaConfigAPI.getInstance(), excp);
                }
            }
            return true;
        }
    }

    /*This method shall configure the pizzeria through a file*/
    public synchronized boolean configurePizzeria(Properties properties)
    {
        PizzaConfig pizzaConfig = buildPizza.buildPizzaProperty(properties);

        if (pizzaConfig != null)
        {
            try
            {
                if (!duplicateChecker(configs, pizzaConfig.getPizzaName()))
                {
                    configs.put(pizzaConfig.getPizzaName(), pizzaConfig);
                    return true;
                }
                else
                    return false;

            } catch (PizzaException e)
            {
                e.fixError(PizzeriaConfigAPI.getInstance(), excp);
            }
        }
        return false;
    }

    /*This method print the pizzeria*/
    public void printPizzeria(String pizzeriaName)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);

        if (pizzaConfig != null)
            pizzaConfig.printConfig();
    }

    /* This method update optionset name*/
    public synchronized void updateOptionSetName(String pizzeriaName, String optionSetName, String newOptionSetName)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        if (pizzaConfig != null)
            pizzaConfig.updateOptionSet(optionSetName, newOptionSetName);
    }

    /*This method update the base price*/
    public synchronized void updateBasePrice(String pizzeriaName, double newBasePrice)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        StructExcp excp = new StructExcp(pizzeriaName, null, "null", newBasePrice);

        if (pizzaConfig != null)
        {
            try
            {
                pizzaConfig.setBasePrice(newBasePrice);
            } catch (PizzaException e)
            {
                e.fixError(PizzeriaConfigAPI.getInstance(), excp);
            }
        }
    }

    /*This method update the price of a given option*/
    public synchronized void updateOptionPrice(String pizzeriaName, String optionSetName, String optionName, double newOptionPrice)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        if (pizzaConfig != null)
            pizzaConfig.updateAnOptionPrice(optionSetName, optionName, newOptionPrice);
    }

    /*This method updates the options*/
    public synchronized void optionUpdater(String pizzeriaName, String optionSetName, String optionName, String newoptionName, double newOptionPrice)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        if (pizzaConfig != null)
            pizzaConfig.updateAnOption(optionSetName, optionName, newoptionName, newOptionPrice);
    }

    /* This method updates the pizzeria name*/
    public synchronized void updatePizzeriaName(String pizzeriaName, String newPizzeriaName)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        StructExcp excp = new StructExcp(pizzeriaName, null, "null", 0);

        if (pizzaConfig != null)
        {
            try
            {
                pizzaConfig.setPizzaName(newPizzeriaName);
            } catch (PizzaException e)
            {
                e.fixError(PizzeriaConfigAPI.getInstance(), excp);
            }
        }
    }

    /* This method updates the optionset from the API*/
    public synchronized void addAnOptionset(String pizzeriaName, String optionsetName)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        StructExcp excp = new StructExcp(pizzeriaName, optionsetName, "null", 0);

        if (pizzaConfig != null)
        {
            try
            {
                pizzaConfig.addOptionSets(optionsetName);
            } catch (PizzaException e)
            {
                e.fixError(PizzeriaConfigAPI.getInstance(), excp);
            }
        }
    }

    /*This method updates the options from the API*/
    public synchronized void addAnOption(String pizzeriaName, String optionsetName, String optionName, double price)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        StructExcp excp = new StructExcp(pizzeriaName, optionsetName, optionName, price);

        if (pizzaConfig != null)
        {
            try
            {
                pizzaConfig.addOptionSetOption(optionsetName, optionName, price);
            } catch (PizzaException e)
            {
                e.fixError(PizzeriaConfigAPI.getInstance(), excp);
            }
        }
    }

    /*This method checks for duplicates in the linkedhashmap of a given pizzeria*/
    public boolean duplicateChecker(Map<String, PizzaConfig> check, String pizzeriaName) throws PizzaException
    {
        Iterator it = check.entrySet().iterator();
        boolean response = false;
        while (it.hasNext())
        {
            Map.Entry map = (Map.Entry) it.next();
            PizzaConfig pizzaConfig = (PizzaConfig) map.getValue();

            if ((pizzaConfig.getPizzaName().toLowerCase().equals(pizzeriaName.toLowerCase())))
            {
                response = true;
                throw ExceptionFactory.getPizzaException("Duplicate configuration");
            }
        }
        return response;
    }

    /*This method checks for duplicates in the linkedhashmap of a given pizzeria*/
    public synchronized void printPizzerias()
    {
        Iterator it = configs.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry map = (Map.Entry) it.next();
            PizzaConfig pizzaConfig = (PizzaConfig) map.getValue();
            pizzaConfig.printConfig();
        }
    }

    /*Removes a configuration from a pizzeria*/
    public synchronized void deleteOptionSet(String pizzeriaName, String optionset)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        if (pizzaConfig != null)
            pizzaConfig.deleteAnOPtionSetByName(optionset);
    }


    /*Return the list of Pizza config*/
    public List<String> pizzaList()
    {
        List<String> list = new ArrayList<>();
        Iterator it = configs.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry map = (Map.Entry) it.next();
            PizzaConfig pizzaConfig = (PizzaConfig) map.getValue();
            list.add(pizzaConfig.getPizzaName());
        }
        return list;
    }

    public PizzaConfig retrivePizza(String pizzeriaName)
    {
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        return pizzaConfig;
    }

    /*Removes a configuration from a pizzeria*/
    public synchronized void deletePizzeria(String pizzeriaName)
    {
        Iterator it = configs.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry map = (Map.Entry) it.next();
            String key = (String) map.getKey();
            PizzaConfig pizzaConfig = (PizzaConfig) map.getValue();
            System.out.println(key);
            if ((pizzaConfig.getPizzaName().toLowerCase().equals(pizzeriaName.toLowerCase())))
            {
                it.remove();
            }
        }
    }

    public List<String> optionSetList(String pizzeriaName)
    {
        List<String> list = new ArrayList<>();
        PizzaConfig pizzaConfig = configs.get(pizzeriaName);
        list = pizzaConfig.getOptionSets();
        return list;
    }


    public double calculatePrice(String pizzaname,String[] array)
    {
        double totalPrice = 0;
        PizzaConfig pizzaConfig = retrivePizza(pizzaname);
        List<String> optionsets = pizzaConfig.getOptionSets();
        ArrayList<StructOption> option;

        int i = 0;
        for (String optionset : optionsets)
        {
            option = pizzaConfig.getListOption(optionset);
            for(StructOption opt : option)
            {
                if(opt.getName() == array[i])
                {
                    totalPrice = totalPrice + opt.getPrice();
                    i++;
                }
            }
        }
        totalPrice = totalPrice + pizzaConfig.getBasePrice();
        return totalPrice;
    }
}
