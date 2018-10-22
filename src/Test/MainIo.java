package Test;

import wrapper.PizzeriaConfigAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainIo
{
    public static void main(String[] arguments)
    {
        try
        {
            FileReader file = new FileReader("PizzeriaConfigurations.txt");
            BufferedReader buff = new BufferedReader(file);
            PizzeriaConfigAPI pizzeriaConfigAPI = PizzeriaConfigAPI.getInstance();


            String line;
            String pizzaName = "";
            double basePrice;
            String optionSet = "";
            String optionName = "";
            double optionPrice;

            line = buff.readLine();
            while (line != null)
            {
                if (line.contains("Pizza name:"))       //todo use piazzacongif
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s]+", line);
                    pizzaName = regexChecker("[\\w\\s]+", str);
//                    System.out.println( pizzaName);
//                    try
//                    {
////                        pizzeriaConfigAPI.createPizzeria(pizzaName);
//                    }
//                    catch (PizzaException e)
//                    {
//                        System.out.println(e.getMessage());
//                    }
//  System.out.println(line);
                }
                if (line.contains("Base price:"))
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s]+", line);
                    basePrice = Double.parseDouble(regexChecker("[\\d]+", str));
                    //   System.out.println( basePrice);

                   // pizzeriaConfigAPI.addBasePrice(pizzaName, basePrice);

                }

                if (line.contains("Optionset:"))
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s]+", line);
                    optionSet = regexChecker("\\w[\\w\\s]+", str);
                    //           System.out.println(optionSet);

                    pizzeriaConfigAPI.addAnOptionset(pizzaName, optionSet);

                }

                if (line.contains("Option:"))
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s\\d-\\.]+", line);
                    optionName = regexChecker("[\\w-]+", str);
                    optionPrice = Double.parseDouble(regexChecker("[\\d\\.]+", str));
//                    System.out.println(optionName + "   " + optionPrice);

                    pizzeriaConfigAPI.addAnOption(pizzaName, optionSet, optionName, optionPrice);

                }


//                System.out.println(line);
                line = buff.readLine();
                if (line != null)
                    if (line.equals("Test"))
                        pizzeriaConfigAPI.printPizzeria(pizzaName);
            }
            buff.close();

        } catch (IOException e)
        {
            System.out.println("Error " + e.toString());
        }
    }


    public static String regexChecker(String pattern, String str)
    {
        Pattern patt = Pattern.compile(pattern);
        Matcher matcher = patt.matcher(str);
        if (matcher.find())
            return matcher.group(); // you can get it from desired index as well
        return null;
    }
}