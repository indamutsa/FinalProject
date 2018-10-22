package model;

/*
 * This program configures the pizzeria
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import exceptions.ExceptionFactory;
import exceptions.PizzaException;
import util.StructOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PizzaConfig implements Serializable
{
    private String pizzaName;
    private double basePrice;
    private List<OptionSet> optionSets; //We need cheese choice, meatchoice, vegChoice

    //Default constructor
    public PizzaConfig()
    {
        pizzaName = "Marinara";
        basePrice = 20;
        optionSets = new ArrayList<>();

    }

    //Constructor
    public PizzaConfig(String pizzaName)
    {
        this();
        this.pizzaName = pizzaName;
    }

    public PizzaConfig(String pizzaName, double basePrice)
    {
        this(pizzaName);
        this.basePrice = basePrice;
    }

    /*--------------------------Getters for basePrice and OptionSet by indexValue-----------------*/

    /*-------------------------This method returns the base price---------------------------------*/
    public double getBasePrice()
    {
        return basePrice;
    }

    public String getPizzaName()
    {
        return pizzaName;
    }

    public List<String> getOptionSets()
    {
        List<String> list = new ArrayList<>();

        if (optionSets != null)
        {
            for (OptionSet set : optionSets)
            {
                list.add(set.getOptionSetName());
            }
        }
        return list;
    }


    /*This will return the options by index*/
    public OptionSet getOptionSets(int index)
    {
        if (optionSets != null && index < optionSets.size())
            return optionSets.get(index);
        System.out.printf("Sorry, there is no optionset on the index: \"%d\" specified\n", index);
        return null;
    }

    /*----------------------------Find methods------------------------------------------*/
    /*Find OptionsSet by name, it is private because I never want to expose the data it carries*/
    private OptionSet findOptionSetByName(String nameOptionSet)
    {
        if (optionSets != null)
        {
            for (OptionSet set : optionSets)
            {
                if (set != null && nameOptionSet.toLowerCase().equals(set.getOptionSetName().toLowerCase()))
                    return set;
            }
        }

        System.out.printf("We could not find the optionSet \"%s\" specified\n", nameOptionSet);
        return null;
    }

    /*Find Option by name in context of OptionSet, it is private because I never want to expose the data it carries*/
    private OptionSet.Option findOption(String optsetName, String optionName)
    {
        OptionSet optset = findOptionSetByName(optsetName);
        if (optset != null)
        {
            for (OptionSet.Option opt : optset.getOptions())
                if (opt != null && optionName.toLowerCase().equals(opt.getOptionName().toLowerCase()))
                    return opt;
        }
        System.out.printf("We could not find this option: \"%s\"\n", optionName);
        return null;
    }

    /*Find OptionsSet by name*/
    public boolean findAnOptionSetByName(String nameOptionSet)
    {
        if (optionSets != null)
        {
            for (OptionSet set : optionSets)
            {
                if (set != null && nameOptionSet.toLowerCase().equals(set.getOptionSetName().toLowerCase()))
                    return true;
            }
        }

        System.out.printf("We could not find the optionSet \"%s\" specified\n", nameOptionSet);
        return false;
    }

    /*Find Option by name in context of OptionSet*/
    public boolean findAnOption(String optsetName, String optionName)
    {
        OptionSet optset = findOptionSetByName(optsetName);
        if (optset != null)
        {
            for (OptionSet.Option opt : optset.getOptions())
                if (opt != null && optionName.toLowerCase().equals(opt.getOptionName().toLowerCase()))
                    return true;
        }
        System.out.printf("We could not find this option: \"%s\"\n", optionName);
        return false;
    }

    /*----------------------------------Setters-----------------------------------*/

    /*Set the pizza configuration name*/
    public synchronized void setPizzaName(String newPizzaName) throws PizzaException
    {
        if (!validateName(newPizzaName))
            throw ExceptionFactory.getPizzaException("Missing pizza name");
        this.pizzaName = newPizzaName;
    }

    /*Set the basePrice*/
    public synchronized void setBasePrice(double newBasePrice) throws PizzaException
    {
        if (!validatePrice(newBasePrice))
            throw ExceptionFactory.getPizzaException("Incorrect base price");

        this.basePrice = newBasePrice;
    }

    /*Set option price of a given option*/
    public synchronized void setOptionPrice(String optionsetName, String optionName, double newPrice)
    {
        OptionSet.Option option = findOption(optionsetName, optionName);
        if (option != null)
        {
            option.updatePrice(newPrice);
        }
    }

    /*Set option name of a given option*/
    public synchronized void setOptionName(String optionsetName, String optionName, String newOptionName)
    {
        OptionSet.Option option = findOption(optionsetName, optionName);
        if (option != null)
            option.updateOptionName(newOptionName);
    }

    /*Add an optionset, note that this adds on top of the other*/
    public synchronized void addOptionSets(String optionSetName) throws PizzaException
    {
        if (optionSets != null && optionSetName != null)
        {
            if (!validateName(optionSetName))
                throw ExceptionFactory.getPizzaException("Incorrect optionset");
            if (duplicateChecker(optionSetName, optionSets))
                throw ExceptionFactory.getPizzaException("Duplicate optionset name");
            OptionSet opset = new OptionSet(optionSetName);
            optionSets.add(opset);
        }
    }

    /*Set the values of an OptionSet (options), this sets every option set at the top of another*/
    public synchronized void addOptionSetOption(String optionSetName, String optionName, double price) throws PizzaException
    {

        OptionSet optset = findOptionSetByName(optionSetName);
        if (optset != null)
        {
            if (duplicateCheck(optionName, optset.getOptions()))
                throw ExceptionFactory.getPizzaException("Duplicate option name");
            OptionSet.Option option = optset.new Option(optionName, price);
            optset.getOptions().add(option);
        }
    }

    /*This method prints the pizza config*/
    public void printConfig()
    {
        System.out.println("\n      \u2665 Pizza name: " + pizzaName + "\n        =======================");
        System.out.printf("        Base price: %-17.2f\n", getBasePrice());

        int i = 0;

        if (optionSets != null)
            for (OptionSet optionSet : optionSets)
            {
                if (optionSet == null)
                    return;
                System.out.printf("\n        %s\n        ******************\n", optionSet.getOptionSetName());
                System.out.println("        Option name       Price\n        -----------       -----");

                for (OptionSet.Option option : optionSet.getOptions())
                {
                    System.out.printf("        %-17s %.2f\n", option.getOptionName(), option.getPrice());
                }
            }
        System.out.println();
    }


    public StringBuilder pizzaTextBuilder()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("\n      \u2665 Pizza name: " + pizzaName + String.format("\n        ===================")));
        builder.append(String.format("\n        Base price: %-17.2f\n", getBasePrice()));

        int i = 0;

        for (OptionSet optionSet : optionSets)
        {
            builder.append(String.format("\n        %s\n        ******************\n", optionSet.getOptionSetName()));
            builder.append(String.format("        Option name       Price\n        ---------------------      ---------\n"));

            for (OptionSet.Option option : optionSet.getOptions())
            {
                builder.append(String.format("        %-20s %.2f\n", option.getOptionName(), option.getPrice()));
            }
        }

        return builder;
    }


    /*--------------------------------Delete options or optionSet------------------------*/

    /*Deleting an option of an optionset at certain index*/
    public synchronized boolean deleteAnOPtionByIndex(String optionSetName, int index)
    {
        if (findOptionSetByName(optionSetName) != null)
        {
            OptionSet optionSet = findOptionSetByName(optionSetName);
            if (index >= 0 && index < optionSets.size())
            {
                optionSet.deleteOptionByIndex(index);
                return true;
            }
        }

        System.out.printf("We could not find the option on the index: \"%d\" specified\n", index);
        return false;
    }


    /*Deleting an option in OptionSet by name*/
    public synchronized boolean deleteAnOptionByName(String opsetName, String optionName)
    {
        if (findOptionSetByName(opsetName) != null)
        {
            OptionSet optionSet = findOptionSetByName(opsetName);

            if (optionSet != null)
            {
                optionSet.deleteOptionByName(optionName);
                return true;
            }
            System.out.printf("The option \"%s\" specified doesn't exist\n", optionName);
        }
        return false;
    }

    /* Delete entire options in an OptionSet*/
    public synchronized boolean deleteAnEntireOption(String opsetName)
    {
        if (findOptionSetByName(opsetName) != null)
        {
            OptionSet optionSet = findOptionSetByName(opsetName);

            if (optionSet != null)
            {
                optionSet.deleteEntireOption();
                return true;
            }
        }

        System.out.printf("The options of this %s set doesn't exist\n", opsetName);

        return false;
    }

    /* Deleting an optionsSet at certain index*/
    public synchronized boolean deleteAnOPtionSetByIndex(int index)
    {
        if (index >= 0 && index < optionSets.size())
        {
            optionSets.remove(index);
            return true;
        }

        System.out.printf("We could not find the option on the index: \"%d\" specified\n", index);
        return false;
    }

    //Deleting an optionsSet at certain index
    public synchronized boolean deleteAnOPtionSetByName(String optionSetName)
    {
        if (findOptionSetByName(optionSetName) != null)
        {
            OptionSet optionSet = findOptionSetByName(optionSetName);

            if (optionSet != null)
            {
                for (int i = 0; i < optionSets.size(); i++)
                {
                    if (optionSet.equals(optionSets.get(i)))
                        optionSets.remove(i);
                }
                return true;
            }
        }

        System.out.printf("We could not find the optionset by this %s\n", optionSetName);

        return false;
    }

    //This method deletes the entire optionset
    public synchronized boolean deleteEntireOptionSet()
    {
        if (optionSets != null)
        {
            for (int i = 0; i < optionSets.size(); i++)
            {
                optionSets.remove(i);
                return true;
            }
        }

        System.out.println("We cannot delete an optionSets which do not exist");
        return false;
    }

    /*-------------------------------Update(Find and Set)--------------------------------*/

    /*Update the values of optionSet(We shall update the optionSet name)*/
    public synchronized boolean updateOptionSet(String optionSetName, String newOptionSetName)
    {
        if (findOptionSetByName(optionSetName) != null)
        {
            OptionSet optSet = findOptionSetByName(optionSetName);
            if (optSet != null)
            {
                optSet.updateOptionSetName(newOptionSetName);
                return true;
            }
        }
        System.out.printf("We could not find the optionSet: %s\n", optionSetName);
        return false;
    }

    /*    Updating the options of a given optionSet*/
    public synchronized boolean updateAnOption(String optionSetName, String optionName, String newOptionName, double newPrice)
    {
        if (findOptionSetByName(optionSetName) != null)
        {

            OptionSet.Option option = findOption(optionSetName, optionName);
            if (option != null)
            {
                option.updateOptionName(newOptionName);
                option.updatePrice(newPrice);
                return true;
            }
        }

        System.out.println("Sorry We cannot edit null option");
        return false;
    }

    /*    Updating the options of a given optionSet*/
    public synchronized boolean updateAnOptionName(String optionSetName, String optionName, String newOptionName)
    {
        if (findOptionSetByName(optionSetName) != null)
        {

            OptionSet.Option option = findOption(optionSetName, optionName);
            if (option != null)
            {
                option.updateOptionName(newOptionName);
                return true;
            }
        }

        System.out.println("Sorry We cannot edit null option");
        return false;
    }

    /*    Updating the options of a given optionSet*/
    public synchronized boolean updateAnOptionPrice(String optionSetName, String optionName, double newPrice)
    {
        if (findOptionSetByName(optionSetName) != null)
        {
            OptionSet.Option option = findOption(optionSetName, optionName);
            if (option != null)
            {
                option.updatePrice(newPrice);
                return true;
            }
        }

        System.out.println("Sorry We cannot edit null option");
        return false;
    }

    //This method update the optionset and option at the same time
    public synchronized boolean updateOptionSetandOption(
            String optionSetName, String newOptionSetName,
            String optionName, String newOptionName, double newPrice)
    {

        if (findOptionSetByName(optionSetName) != null)
        {
            OptionSet optset = findOptionSetByName(optionSetName);
            if (optset != null)
            {
                updateOptionSet(optionSetName, newOptionSetName);
                updateAnOption(optset.getOptionSetName(), optionName, newOptionName, newPrice);
                return true;
            }
        }

        System.out.println("Sorry We cannot edit null optionset");
        return false;
    }

    public boolean validatePrice(Object price)
    {
        if ((price instanceof Double || price instanceof Integer) && ((Double) price > 0))
            return true;
        return false;
    }

    public boolean validateName(String name) //todo validate at least name and baseprice
    {
        if (regexChecker("[\\w\\d\\s]{1,20}", name) != null)
            return true;
        return false;
    }

    /*---------------------------------Validate methods-------------------------------------*/
    public boolean validate(String name, Object price) throws PizzaException //todo validate at least name and baseprice
    {
        if (!validateName(name))
            throw ExceptionFactory.getPizzaException("Incorrect pizza name");
        if (!validatePrice(price))
            throw ExceptionFactory.getPizzaException("Incorrect base price");
        return true;
    }

    public boolean duplicateChecker(String str, List<OptionSet> check)
    {
        if (str.equals(""))
            return true;
        for (int i = 0; i < check.size(); i++)
        {
            if (check.get(i).getOptionSetName().toLowerCase().equals(str.toLowerCase()))
                return true;
        }
        return false;
    }

    public boolean duplicateCheck(String str, List<OptionSet.Option> check)
    {
        for (int i = (check.size() - 1); i > 0; i--)
        {
            if (check.get(i).getOptionName().toLowerCase().equals(str.toLowerCase()))
                return true;
        }
        return false;
    }

    public synchronized void deleteDuplicate(String str, List<? extends Object> duplicate)
    {
        for (int i = 0; i < duplicate.size(); i++)
        {
            if (str.equals((String) duplicate.get(i)))
                duplicate.remove(i);
        }
    }

    public String regexChecker(String pattern, String str)
    {
        Pattern patt = Pattern.compile(pattern);
        Matcher matcher = patt.matcher(str);
        if (matcher.find())
            return matcher.group();
        return null;
    }

    //This class print nicely all the instance variables of this class
    protected void print()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Pizza name: " + pizzaName);
        for (OptionSet optionSet : optionSets)
        {
            builder.append("\nOptionset name: " + optionSet.getOptionSetName());
            for (OptionSet.Option option : optionSet.getOptions())
            {
                builder.append("\nOption name: " + option.getOptionName());
                builder.append("Option price: " + option.getPrice() + "\n");
            }
        }

        System.out.println(builder.toString());
    }


    /*This method when is given the name of an optionset, it returns its options*/
    public ArrayList getListOption(String opset)
    {
        ArrayList<StructOption> list = new ArrayList();
        StructOption structOption;
        OptionSet optionSet = findOptionSetByName(opset);
        for (OptionSet.Option option : optionSet.getOptions())
        {
            structOption = new StructOption(option.getOptionName(), option.getPrice());
            list.add(structOption);
        }
        return list;
    }

    /*-----------------------------toString()--------------------------------------------*/
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pizza configuration: \n----------------------");
        stringBuilder.append("\nPizza name: " + pizzaName);
        stringBuilder.append("\nBase price: " + basePrice);
        stringBuilder.append("\nOptionsets array size: " + optionSets.size());

        return stringBuilder.toString();
    }
}
