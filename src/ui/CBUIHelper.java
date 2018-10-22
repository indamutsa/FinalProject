package ui;

/**
 * This program supports UI functionality
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import client.Client;
import model.PizzaConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CBUIHelper
{
    /*The initial display*/
    public static StringBuilder printLogo()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n       ==========================================================\n");
        stringBuilder.append("         || ** Welcome to INDAMUTSA PIZZERIA configuration **||\n");
        stringBuilder.append("       ==========================================================\n\n");

        stringBuilder.append("      To perform an action below, press its prefix number \n");
        stringBuilder.append("     -----------------------------------------------------\n\n");

        stringBuilder.append("              1.  Upload a file\n");
        stringBuilder.append("              2.  Show all pizzerias\n");
        stringBuilder.append("              3.  Display a pizzeria\n");
        stringBuilder.append("              4.  Remove a pizzeria\n");
        stringBuilder.append("              5.  Update pizzeria \n");

        return stringBuilder;
    }

    /*Uploading a file*/
    public void uploader(String input, Client client)
    {
        String str;

        str = client.uploadFile(input);
        System.out.println(str);
    }

    /*Displaying list of pizzerias*/
    public boolean displayAllPizzerias(Client client)
    {
        Object object = client.pizzeriaList();

        if (object == null)
            return false;

        int i = 0;

        if (object instanceof ArrayList)
        {
            System.out.println("\n      Pizza List: ");
            ArrayList<String> list = (ArrayList) object;
            for (String pizza : list)
            {
                System.out.printf("            %d)  %s\n", i + 1, pizza);
                i++;
            }
            System.out.println();
            return true;
        } else
        {
            System.out.println(object);
            return false;
        }
    }

    /*Displaying a single pizzera*/
    public void displayApizza(String inputUser, BufferedReader reader, Client client) throws IOException
    {
        int counter = 0;

        while (!inputUser.isEmpty())
            if (inputUser.matches("[\\d]+"))
            {
                PizzaConfig pizzaConfig = client.retrievePizza(Integer.valueOf(inputUser));

                if (pizzaConfig != null)
                {
                    pizzaConfig.printConfig();
                    break;
                } else
                {
                    System.out.println("   No such pizza in our database\n");
                    break;
                }
            } else
            {
                if (counter == 2)
                    break;

                System.out.print("\u2664      Please choose from available pizzas or press ENTER to exit...\n   >_: ");
                inputUser = reader.readLine();
                counter++;
            }

    }

    /*Remove a given pizza*/
    public void removePizza(String inputUser, BufferedReader reader, Client client) throws IOException
    {
        int counter = 0;

        while (!inputUser.isEmpty())
            if (inputUser.matches("[\\d]+"))
            {
                String str = client.deleteApizzeria(Integer.valueOf(inputUser));
                System.out.println("  " + str + "\n");
                break;
            } else
            {
                if (counter == 2)
                    break;

                System.out.print("\u2664     Please choose from available pizzas or press ENTER to exit...\n   >_: ");
                inputUser = reader.readLine();
                counter++;
            }
    }

    /*Add an option to an optionset of a given pizza*/
    public void addOption(String pizzaNum, String optsetNum, String optioname, String optionprice, Client client)
    {
        int pizzN = Integer.valueOf(pizzaNum);
        int optNum = Integer.valueOf(optsetNum);
        double optPrice = Double.valueOf(optionprice);


        String str = client.addAnOption(pizzN, optNum, optioname, optPrice);

        if (str != null)
            System.out.println("   Server response:" + str);
        else
            System.out.println("   Server response: No such optionset in our database");

    }

    public void updatePrice(String pizzaNumber, String price, Client client) throws IOException
    {
        int pizzaNum = Integer.valueOf(pizzaNumber);
        double baseprice = Double.valueOf(price);
        String str = client.updateBasePrice(pizzaNum, baseprice);

        if (str != null)
            System.out.println("   Server response: " + str + "\n");
        else
            System.out.println("   Server response: No such pizzeria in our database\n");

    }

    /*Display optionset list*/
    public boolean displayOptionset(Client client, int index)
    {
        ArrayList<String> list = client.showOptionSets(index);

        int j = 0;

        if (list == null)
            return false;


        System.out.println("\n       Option set list: ");

        for (String optionset : list)
        {
            System.out.printf("           %d)  %s\n", j + 1, optionset);
            j++;
        }
        return true;
    }
}
