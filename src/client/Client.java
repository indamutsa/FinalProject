package client;

import model.OptionSet;
import model.PizzaConfig;
import util.Courrier;
import util.SearchFile;
import util.StructOption;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Client implements PizzeriaClient
{
    private int port;
    private static Courrier courrier;

    public Client()
    {
        port = 5000;
        this.courrier = new Courrier();
    }

    public Client(int port) throws IOException
    {
        this.port = port;
        this.courrier = new Courrier();
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }


    /*This function is repsonsible of carrying the messages across the client and the server*/
    @Override
    public Object streamTransporter(Object o)
    {
        String serverDown = "    Failed to connect to the server,\n    Please Turn on the SERVER and try again\n";
        try (Socket socket = new Socket("localhost", getPort()))
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(o);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object obj = ois.readObject();
            return obj;

        } catch (Exception e)
        {
            return serverDown;
        }
    }


    /*This function is responsible of uploading the file*/
    @Override
    public String uploadFile(String route)
    {
        /*The courrier is our protocol and transporter to the server and back to the client*/

        String str = "    Incorrect path provided\n";

        /*Here we just uploading the file*/
        Properties props = new Properties();
        SearchFile searchFile = new SearchFile();
        Path currentPath = Paths.get("").toAbsolutePath();

        /*I search my computer to find any given file. when we see it, we return we let the program proceed
         * Otherwise we abort and return null
         */
        if (route.matches("[\\d]+") || searchFile.searchFile(currentPath, route) == null)
            return str;


        route = searchFile.searchFile(currentPath, route);

        /*Reading the file route given*/
        try
        {
            FileInputStream in = new FileInputStream(route);
            props.load(in);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        /*Setting the object to the property file*/
        courrier.setObject(props);
        courrier.setMessage("Upload");

        /*Transporting the file to the server and receiving from the client*/
        Object object = streamTransporter(courrier);

        if (object instanceof String)
        {
            str = (String) object;
            return str;
        }
        return str;
    }

    /*This function displays all the pizzerias in our menu*/
    @Override
    public Object pizzeriaList() //todo return a list to the dropdown
    {
        /*We send a message show to the server and it returns
         * The list of the items we have in our database so far
         */
        String show = "show";
        courrier.setMessage(show);

        /*Sending the file to the server and receiving the response if there happens to be one*/
        Object object = streamTransporter(courrier);
        return object;
    }

    /*This return a list of optionset of a given pizza*/
    @Override
    public ArrayList<String> showOptionSets(int index)
    {
        /*Setting the cargo which will bring back the list of optionsets of a
         * given pizza
         */

        String show = "showlist";

        //*****************************
        Object obj = pizzeriaList();

        /*If the returned object is a list, we return it otherwise we return null*/
        if (!(obj instanceof List) || obj instanceof String)
            return null;

        ArrayList<String> list = (ArrayList) obj;
        index = index - 1;

        /*The index choosen must be as part of the list
         * Once the index is verified  we send the obejct, when the object returns
         * we check if it is of PizzaConfig object*/
        if (index >= 0 && index < list.size())
        {
            courrier.setMessage(show);
            courrier.setPizzaNameIndex(index);

            Object object = streamTransporter(courrier);

            ArrayList<String> list1 = (ArrayList<String>) object;
            return list1;
        }
        return null;
    }

    public ArrayList<String> showOptionSets(String pizzaName)
    {
        /*Setting the cargo which will bring back the list of optionsets of a
         * given pizza
         */

        int index = 789;
        String show = "showlist";

        //*****************************
        Object obj = pizzeriaList();

        /*If the returned object is a list, we return it otherwise we return null*/
        if (!(obj instanceof List) || obj instanceof String)
            return null;

        ArrayList<String> list = (ArrayList) obj;

        int i = 0;
        for (String s : list)
        {
            if (pizzaName.equals(s))
            {
                index = i;
                break;
            } else
                i++;
        }

        /*The index choosen must be as part of the list
         * Once the index is verified  we send the obejct, when the object returns
         * we check if it is of PizzaConfig object*/
        if (index >= 0 && index < list.size())
        {
            courrier.setMessage(show);
            courrier.setPizzaNameIndex(index);

            Object object = streamTransporter(courrier);

            ArrayList<String> list1 = (ArrayList<String>) object;
            return list1;
        }
        return null;
    }

    /*In this function, we retrive a given pizza*/
    @Override
    public PizzaConfig retrievePizza(int n)
    {
        /*If the pizza chosen in the list pizzeria*/
        Object obj = pizzeriaList();


        if (!(obj instanceof List))
            return null;

        ArrayList<String> list = (ArrayList) obj;
        n = n - 1;

        if (list == null)
            return null;

        /*The index choosen must be as part of the list
         * Once the index is verified  we send the obejct, when the object returns
         * we check if it is of PizzaConfig object*/
        if (n >= 0 && n < list.size())
        {
            courrier.setMessage("Print");
            courrier.setObject(n);

            Object object = streamTransporter(courrier);

            if (object instanceof PizzaConfig)
            {
                PizzaConfig pizzaConfig = ((PizzaConfig) object);
                return pizzaConfig;
            }
        }
        return null;
    }

    /*In this function, we retrive a given pizza*/
    public PizzaConfig retrievePizza(String pizzaName)
    {
        /*If the pizza chosen in the list pizzeria*/
        Object obj = pizzeriaList();

        int n = 663;

        if (!(obj instanceof List))
            return null;

        ArrayList<String> list = (ArrayList) obj;

        if (list == null)
            return null;

        int i = 0;
        for (String s : list)
        {
            if (pizzaName.equals(s))
            {
                n = i;
                break;
            } else
                i++;
        }

        /*The index choosen must be as part of the list
         * Once the index is verified  we send the obejct, when the object returns
         * we check if it is of PizzaConfig object*/
        if (n >= 0 && n < list.size())
        {
            courrier.setMessage("Print");
            courrier.setObject(n);

            Object object = streamTransporter(courrier);

            if (object instanceof PizzaConfig)
            {
                PizzaConfig pizzaConfig = ((PizzaConfig) object);
                return pizzaConfig;
            }
        }
        return null;
    }

    /*In this function we delete a pizza*/
    @Override
    public String deleteApizzeria(int n)
    {
        /*We first secure the list of the pizzerias*/
        Object obj = pizzeriaList();

        if (!(obj instanceof List))
            return null;

        ArrayList<String> list = (ArrayList) pizzeriaList();
        String response = "No such pizza in our database";

        n = n - 1;

        if (list == null)
            return response;

        /*The index chosen must be as part of the list
         * Once the index is verified  we send the obejct, when the object returns
         * we delete the pizzaconfig selected in our database*/
        if (n >= 0 && n < list.size())
        {
            courrier.setMessage("Delete");
            courrier.setObject(n);

            Object object = streamTransporter(courrier);
            response = (String) object;
            return response;
        }
        return response;
    }

    /*In this function we update the base price*/
    @Override
    public String updateBasePrice(int number, double money) //todo retunr false or true
    {
        Object obj = pizzeriaList();
        String str;

        if (!(obj instanceof List))
            return null;

        ArrayList<String> list = (ArrayList) obj;
        number = number - 1;

        /*The index chosen must be as part of the list
         * Once the index is verified  we send the obejct, when the object returns
         * we update the base price of the selected pizzaconfig in our database*/
        if (number >= 0 && number < list.size())
        {
            courrier.setMessage("UpdateBP");
            courrier.setObject(money);
            courrier.setIndex(number);

            Object object = streamTransporter(courrier);

            /*The object returned on the server is a string notifying us the status of the response*/
            if (object instanceof String)
                return (String) object;
        }
        return null;
    }

    /*Adding option to a given option set of a given pizza*/
    @Override
    public String addAnOption(int pizzaNum, int optsetNum, String optionName, double price)
    {
        /*We retrive the list of the pizza and define the courrier that we shall use
         * to send our object*/
        String str = "No such option set in this Pizza";
        Object obj = showOptionSets(pizzaNum);

        if (!(obj instanceof List))
            return str;

        ArrayList<String> list = (ArrayList) obj;

        pizzaNum = pizzaNum - 1;
        optsetNum = optsetNum - 1;

        /*We send our object here after updating the courrier*/
        if (optsetNum >= 0 && optsetNum < list.size() && pizzaNum >= 0 && pizzaNum < list.size())
        {
            courrier.setMessage("UpdateOption");
            courrier.setPizzaNameIndex(pizzaNum);
            courrier.setOptionsetIndex(optsetNum);
            courrier.setOptionName(optionName);
            courrier.setPrice(price);

            /*Sending the object*/
            Object object = streamTransporter(courrier);

            if (object instanceof String)
            {
                str = (String) object;
                return str;
            }
        }
        return str;
    }

    public StringBuilder pizzaText(int n)
    {
        int j = 0;
        j = n;

        StringBuilder builder = null;

        if (retrievePizza(j + 1) != null)
            builder = retrievePizza(n + 1).pizzaTextBuilder();

        j = 0;
        return builder;
    }

    public ArrayList<StructOption> pizzaOptions(String pizzaName, String optset)
    {
        PizzaConfig pizzaConfig = retrievePizza(pizzaName);

        ArrayList<StructOption> optionList = pizzaConfig.getListOption(optset);
        return optionList;
    }

    public double getPizzaPrice(String pizzaName, String[] arr)
    {
        PizzaConfig pizzaConfig = retrievePizza(pizzaName);


        courrier.setSelectedElements(arr);
        courrier.setMessage("calculate");
        courrier.setObject(pizzaName);

        Object object = streamTransporter(courrier);
        double totalPrice = (Double) object;
        return totalPrice;
    }

    public boolean dbChoose(String db, BufferedReader reader)
    {
        try
        {
            while (!db.isEmpty())
            {
                if (db.equals("1"))
                {
                    courrier.setDbChoice("1");
                    return true;
                } else if (db.equals("2"))
                {
                    courrier.setDbChoice("2");
                    return true;
                }
                System.out.println("Please enter the 1 for database or 2 for linkedshmap\"" +
                        "\nPress ENTER to exit");
                db = reader.readLine();

            }
        } catch (IOException e)
        {

        }
        return false;
    }

}