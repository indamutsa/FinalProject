package ui;

import client.Client;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CharacterBasedUI
{
    public static void main(String args[]) throws IOException
    {
        int port = 5000;


        boolean running = false;
        String input = "";
        int inputUser = 0;
        int counter = 0;
        int choice = 0;

        BufferedReader readerInput = new BufferedReader(new InputStreamReader(System.in));
        CBUIHelper cb = new CBUIHelper();
        Client client = new Client(port);

        System.out.println(cb.printLogo());

        System.out.print("\n\u2663 Select the action to perform or press ENTER to exit,\n>>_: ");
        input = readerInput.readLine();

        while (!running && !input.isEmpty())
        {
            counter = 0;
            if (input.matches("\\d"))
            {
                choice = Integer.parseInt(input);

                /*Within this swith statement, we will a single action to perform
                 * according to the user choice.*/
                switch (choice)
                {
                    /*When the user chooses 1, we shall upload the file and return
                     * a string telling us whether the action has been successful or failed
                     * The first thing the user does here is to upload the file. when the path is right
                     * we proceed otherwise we abort the program
                     */
                    case 1:
                        System.out.print("     \u00BB Please enter the file path: ");
                        input = readerInput.readLine().trim();

                        cb.uploader(input, client);
                        break;

                    /*Here we do few things, when The user chooses 2, we just display the list of the pizza
                     * in our pizzeria if he has uploaded some already
                     */
                    case 2:
                        cb.displayAllPizzerias(client);
                        break;

                    /*In this option we first choose a pizza to return,
                     * if the pizza chosen is in our database, we return it
                     */
                    case 3:
                        if (!cb.displayAllPizzerias(client))
                            break;

                        System.out.print("    \u00BB Select a pizza(1,2,3,..) to display or ENTER to exit...: ");

                        input = readerInput.readLine();
                        cb.displayApizza(input, readerInput, client);

                        break;

                    /*In case you want to delete a pizzeria in our database*/
                    case 4:
                        if (!cb.displayAllPizzerias(client))
                            break;

                        System.out.print("    \u00BB Select a pizza(1,2,3,..) to remove : ");
                        input = readerInput.readLine();
                        cb.removePizza(input, readerInput, client);
                        break;

                    /*Updating the pizza, we have two options
                     * updating the base price
                     * updating the option*/
                    case 5:

                        if (!cb.displayAllPizzerias(client))
                            break;

                        System.out.printf("    Select 1 to update the base price,\n" +
                                "    Select 2 to update an option set,\n  press ENTER exit...\n  >>_: ");
                        input = readerInput.readLine();

                        int b = 0;
                        int opt = 0;
                        while (!input.isEmpty())
                            if (input.matches("[\\d]"))
                            {
                                opt = Integer.parseInt(input);
                                break;
                            } else
                            {
                                System.out.print("   You entered a wrong input, Please enter 1 or 2 or\n   Press ENTER to exit\n  >>_: ");
                                input = readerInput.readLine();
                                if (b < 3)
                                {
                                    b++;
                                    break;
                                }
                            }

                        switch (opt)
                        {
                            /*When you want to update the base price*/
                            case 1:
                                String pizzaNumber;
                                String price;

                                System.out.print("    \u00BBSelect the pizza (1,2,3,..) to update: ");
                                pizzaNumber = readerInput.readLine().trim();

                                while (pizzaNumber.isEmpty())
                                {
                                    System.out.print("\u2664:  Please enter a valid optionset or 0 to exit\n>>_:");
                                    pizzaNumber = readerInput.readLine().trim();
                                    if (pizzaNumber.equals("0"))
                                        break;
                                }

                                if (pizzaNumber.equals("0"))
                                    break;

                                /*This loop will continue to prompt the user until he enter the right input or
                                 * presses enter to exit*/
                                while (!pizzaNumber.isEmpty())
                                    if (!pizzaNumber.matches("[\\d]+"))
                                    {
                                        System.out.print(">>: You entered a wrong input, Please enter a valid pizza or\n   Press ENTER to exit\n  >>_: ");
                                        pizzaNumber = readerInput.readLine();
                                        if (pizzaNumber.isEmpty())
                                            break;
                                    } else
                                        break;

                                System.out.print("    \u00BBSet the base price: ");
                                price = readerInput.readLine().trim();

                                while (!price.matches("[\\d.]+"))
                                {
                                    System.out.print("\u2664:  Please enter a valid price or 0 to exit\n>>_:");
                                    price = readerInput.readLine().trim();
                                    if (price.equals("0"))
                                        break;
                                }



                                /*This loop will continue to prompt the user until he enter the right input or
                                 * presses enter to exit*/
                                while (!price.isEmpty())
                                    if (!price.matches("[\\d.]+"))
                                    {
                                        System.out.print(">>: You entered a wrong input, Please enter a valid price or\n   Press ENTER to exit\n  >>_: ");
                                        price = readerInput.readLine();
                                        if (price.isEmpty())
                                            break;
                                    } else
                                        break;

                                cb.updatePrice(pizzaNumber, price, client);
                                break;
                            case 2:

                                System.out.print("    \u00BBSelect the pizza (1,2,3,..) to update: ");
                                String pizzaNum = readerInput.readLine().trim();


                                while (pizzaNum.isEmpty())
                                {
                                    System.out.print("\u2664:  Please enter a valid pizza or 0 to exit\n>>_:");
                                    pizzaNum = readerInput.readLine().trim();
                                    if (pizzaNum.equals("0"))
                                        break;
                                }

                                if (pizzaNum.equals("0"))
                                    break;

                                /*This loop will continue to prompt the user until he enter the right input or
                                 * presses enter to exit*/
                                while (!pizzaNum.isEmpty())
                                    if (!pizzaNum.matches("[\\d]+"))
                                    {
                                        System.out.print(">>_:  You entered a wrong input, Please enter a valid pizza or\n   Press ENTER to exit\n  >>_: ");
                                        pizzaNum = readerInput.readLine();
                                        if (pizzaNum.isEmpty())
                                            break;
                                    } else
                                        break;

                                if (!cb.displayOptionset(client, Integer.valueOf(pizzaNum)))
                                {
                                    System.out.println("   The pizza provided is not in our database, please try again\n ");
                                    break;
                                }


                                System.out.print("    \u00BBSelect the optionset (1,2,3,..) to update: ");

                                String optsetNum = readerInput.readLine().trim();

                                while (optsetNum.isEmpty())
                                {
                                    System.out.print("\u2664:  Please enter a valid optionset or 0 to exit\n>>_:");
                                    optsetNum = readerInput.readLine().trim();
                                    if (optsetNum.equals("0"))
                                        break;
                                }

                                if (optsetNum.equals("0"))
                                    break;
                                /*This loop will continue to prompt the user until he enter the right input or
                                 * presses enter to exit*/
                                while (!optsetNum.isEmpty())
                                    if (!optsetNum.matches("[\\d]+"))
                                    {
                                        System.out.print("   >>_: You entered a wrong input, Please enter a valid optionset or\n   Press ENTER to exit\n  >>_: ");
                                        optsetNum = readerInput.readLine();
                                        if (optsetNum.isEmpty())
                                            break;
                                    } else
                                        break;


                                System.out.print("    \u00BBAdd option name: ");
                                String optioname = readerInput.readLine().trim();

                                while (optioname.isEmpty())
                                {
                                    System.out.print("\u2664:  Please enter a option name pizza or 0 to exit\n>>_:");
                                    optioname = readerInput.readLine().trim();
                                    if (optioname.equals("0"))
                                        break;
                                }

                                if (optioname.equals("0"))
                                    break;

                                System.out.print("    \u00BBAdd an option price: ");
                                String optionprice = readerInput.readLine().trim();

                                while (optionprice.isEmpty())
                                {
                                    System.out.print("\u2664:  Please enter a option name pizza or 0 to exit\n>>_:");
                                    optionprice = readerInput.readLine().trim();
                                    if (optionprice.equals("0"))
                                        break;
                                }
                                if (optionprice.equals("0"))
                                    break;

                                /*This loop will continue to prompt the user until he enter the right input or
                                 * presses enter to exit*/
                                while (!optionprice.isEmpty())
                                    if (!optionprice.matches("[\\d.]+"))
                                    {
                                        System.out.print("   >>_You entered a wrong input, Please enter a valid price or\n   Press ENTER to exit\n  >>_: ");
                                        optionprice = readerInput.readLine();
                                        if (optionprice.isEmpty())
                                            break;
                                    } else
                                        break;

                                /*Updating the option*/
                                cb.addOption(pizzaNum, optsetNum, optioname, optionprice, client);
                                break;
                        }
                }

                choice = 0;

                System.out.print("\n\u2663 Select the action to perform or press ENTER to exit,\n>>_: ");

                input = readerInput.readLine().trim();

                if (input.matches("[\\d]+"))
                    continue;
                else
                {
                    counter++;
                    System.out.print("\n\u2665 You entered a wrong input,\n  Please try again or press ENTER to exit... \n >>_:");
                    input = readerInput.readLine().trim();


                    if (counter == 2)
                    {
                        System.out.println("    \u2666You entered a wrong input 2 times\n" +
                                "    System exiting....");
                        System.exit(0);
                    }
                }
            } else
            {
                System.out.print("   \u2664You entered a wrong input,\n   Please enter a valid input\n>>_: ");
                input = readerInput.readLine().trim();

                if (input.matches("[\\d]+"))
                    continue;
                else
                {
                    System.out.println("   \u2666Wrong selected action, Please follow instructions above!\n" +
                            "    Program exiting...");
                    System.exit(1);
                }
            }
        }
    }
}
