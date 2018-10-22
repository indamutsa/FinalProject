package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This program is responsible for managing pizza option sets
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

//NOTE: I extended because the rearrange method is needed here as well
public class OptionSet extends PizzaConfig implements Serializable
{
    private String optionSetName;
    private List<Option> options;

    //Default constructor
    public OptionSet()
    {
        optionSetName = "meatChoice";
        options = new ArrayList<>(10);
    }

    //Constructor
    public OptionSet(String nameOptionSet) //Creating 1
    {
        this.optionSetName = nameOptionSet;
        options = new ArrayList<>();

    }


    //*******************CRUD Operations for optionSetName attribute****************************
    /*-------------------------This method shall read the optionSetName---------------------------*/
    protected String getOptionSetName()
    {
        return optionSetName;
    }

    /*-------------------------This method shall update the optionSetName---------------------------*/
    protected void updateOptionSetName(String optionSetName)
    {
        this.optionSetName = optionSetName;
    }


    //*******************CRUD Operations for optionSetOptions attribute ************************

    /*------------------------------------READ OPTIONS--------------------------------------*/
    /*-------------------------This method read the options array---------------------------*/
    protected List<Option> getOptions()
    {
        return options;
    }

    /*----------------This method will read the option by index specified----------------------*/
    protected Option getOptionsByIndex(int index)
    {
        if (options.get(index) != null && index <= 0 && index < options.size())
            return this.options.get(index);

        System.out.println("There is no option on the index specified");
        return null;
    }

    /*----------------This method will read the option by name specified----------------------*/
    protected Option getOptionByName(String optionName)
    {
        for (Option option : options)
        {
            if (optionName.equals(option.getOptionName()))
                return option;
        }
        System.out.println("We could not find this option!");
        return null;
    }


    /*---------------------------------UPDATING OPTIONS------------------------------------------*/

    /*----------------This method shall update options on a specified index--------------*/
    protected boolean updateOptionsbyIndex(String optionName, double price, int index)
    {
        if (index <= 0 && index < options.size())
        {
            options.get(index).updatePrice(price);
            options.get(index).updateOptionName(optionName);
            return true;
        }
        System.out.println("The index specified is out of range");
        return false;

    }

   /* -----------------------------DELETE OPTIONS--------------------------------*/
    /*--------------- Deleting the options by index or completely---------------------------------*/
    protected boolean deleteOptionByIndex(int index)
    {
        if (options != null && index >= 0 && index < options.size())
        {
            this.options.remove(index);
            return true;
        }
        System.out.println("The index specified is out of range");
        return false;
    }

   /* --------------- Deleting the options by index or completely---------------------------------*/
    protected boolean deleteOptionByName(String optioName)
    {
        if (options != null)
        {
            for (int i = 0; i < options.size(); i++)
            {
                if (options.get(i).getOptionName().toLowerCase().equals(optioName.toLowerCase()))
                {
                    options.remove(i);
                    return true;
                }
            }
        }
        System.out.println("The index specified is out of range");
        return false;
    }
    /*--------------- deleting the entire options ------------------------------------------------*/
    protected boolean deleteEntireOption()
    {
        if (options != null)
        {
            for (int i = 0; i < options.size(); i++)
                options.remove(i);
            return true;
        }
        System.out.println("You cannot delete an option which does not exist");
        return false;
    }

    //This class print nicely all the instance variables of this class
    protected void print()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("OptionSet: " + optionSetName);
        for(Option option: options)
        {
            builder.append("\nOption name: " + option.getOptionName());
            builder.append("Option price: " + option.getPrice() + "\n");
        }

        System.out.println(builder.toString());
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("OptionSet: " + optionSetName);
        builder.append("\nOption sets' array size: " + options.size());
        return builder.toString();
    }

    //Inner class Option
    public class Option implements Serializable
    {
        private String optionName;
        private double price;

        //Default constructor
        public Option()
        {
            optionName = "size";
            price = 1500;
        }

        //Constructor
        public Option(String optionName)
        {
            this.optionName = optionName;
        }

        //Constructor
        public Option(String optionName, double price)
        {
            this(optionName);
            this.price = price;
        }

        //********************CRUD Operations for optionName attribute*****************************
        /*--------------------------This will return the name---------------------------*/
        protected String getOptionName()
        {
            return optionName;
        }

        /*--------------------------This shall set the name------------------------------------*/
        protected void updateOptionName(String optionName)
        {
            this.optionName = optionName;
        }


        //CRUD Operations for price attribute
        /*--------------------------This will return the name---------------------------*/
        protected double getPrice()
        {
            return price;
        }

        /*--------------------------This will update the name---------------------------*/
        protected void updatePrice(double price)
        {
            this.price = price;
        }

        /*--------------------------This will reset the price to zero-----------------------*/
        protected void resetPrice()
        {
            this.price = 0.0;
        }

        protected void printOption()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Options: \n---------");
            stringBuilder.append("Name: " + optionName);
            stringBuilder.append(", Price: " + price + "\n");
            System.out.println(stringBuilder.toString());
        }

        @Override
        public String toString()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Options: \n---------");
            stringBuilder.append("\nName: " + optionName);
            stringBuilder.append("\nPrice: " + price);
            return stringBuilder.toString();
        }
    }
}
