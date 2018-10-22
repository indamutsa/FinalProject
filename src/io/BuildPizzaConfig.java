package io;
/**
 * This program is responsible for configuring textfile or properties files to a pizza config
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import exceptions.*;
import model.PizzaConfig;
import util.SearchFile;
import wrapper.PizzeriaConfigAPI;
import util.StructExcp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildPizzaConfig
{
    /*Building a pizza from a text*/
    public List<PizzaConfig> buildPizzaConfig(String filename)
    {
        FileReader file;
        FileReader file1;
        String fileNa = "";

        List<PizzaConfig> configArrayList = new ArrayList<>();
        SearchFile search = new SearchFile();
        PizzaConfig pizzaConfig = new PizzaConfig();
        BufferedReader buff;

        Path currentPath = Paths.get("");
        Path path = currentPath.toAbsolutePath();
        String route;

        try
        {
            if (!filename.contains("/"))
                fileNa = fileAuthenticate(filename);
            else
                fileNa = filename;

            file = new FileReader(fileNa);

            buff = new BufferedReader(file);

            String line;
            String pizzaName = "";
            double basePrice;
            String optionSet = "";
            String optionName = "";
            double optionPrice;
            int i = 0;

            line = buff.readLine();


            while (line != null)
            {
                if (line.contains("Pizza name:"))
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s]+", line);
                    pizzaName = regexChecker("[\\w\\s]+", str).trim();
                    try
                    {
                        pizzaConfig.setPizzaName(pizzaName);
                    } catch (PizzaException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

                if (line.contains("Base price:") && pizzaName != "")
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s]+", line);
                    basePrice = Double.parseDouble(regexChecker("[\\d]+", str));
                    try
                    {
                        pizzaConfig.setBasePrice(basePrice);
                    } catch (PizzaException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

                if (line.contains("Optionset:") && pizzaName != "")
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s]+", line);
                    optionSet = regexChecker("\\w[\\w\\s]+", str).trim();
                    try
                    {
                        pizzaConfig.addOptionSets(optionSet);
                    } catch (PizzaException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

                if (line.contains("Option:") && optionSet != null)
                {
                    String str;
                    str = regexChecker("\\:\\s[\\w\\s\\d-\\.]+", line);
                    optionName = regexChecker("[\\w-]+", str);
                    optionPrice = Double.parseDouble(regexChecker("[\\d\\.]+", str));
                    try
                    {
                        pizzaConfig.addOptionSetOption(optionSet, optionName, optionPrice);
                    } catch (PizzaException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

                if ((line.equals("Test") && i == 0))
                {
                    i++;
                    line = buff.readLine();
                    continue;
                }

                if (line.equals("Test") || line.equals("End"))
                {
                    configArrayList.add(pizzaConfig);
                    pizzaConfig = new PizzaConfig();
                }

                line = buff.readLine();
            }
            buff.close();

        } catch (InvalidFormatException e)
        {
            configArrayList = e.invalidFormat();
        } catch (MissingFileException e)
        {
            configArrayList = e.fixMissingFile(path, search);
        } catch (IOException e) //todo catch at least two option
        {
            System.out.println("Error " + e.toString() + "\nWe shall attempt to search it");
        }


        return configArrayList;
    }

    public static String regexChecker(String pattern, String str)
    {
        Pattern patt = Pattern.compile(pattern);
        Matcher matcher = patt.matcher(str);
        if (matcher.find())
            return matcher.group(); // you can get it from desired index as well
        return null;
    }

    /*This method checks if the file is not in the current directory and throws an error*/
    public static boolean fileSearcher(SearchFile s, Path path, String filename) throws MissingFileException
    {
        if (filename.contains(".txt") && s.searchFile(path, filename) != null)
            return true;
        else
            throw new MissingFileException("Missing file");
    }

    public boolean validateFile(FileReader filen) throws InvalidFormatException
    {
        boolean spotter = false;
        try
        {
            if (filen == null)
                return spotter;

            BufferedReader buff = new BufferedReader(filen);

            String line = buff.readLine();

            while (line != null)
            {
                if (line.equals("") || line.equals("Test") || line.contains("Pizza name:") || line.contains("Base price:") || line.contains("Optionset:") || line.contains("Option:"))
                {
                    spotter = true;
                } else if (line.equals("End"))
                    return spotter;
                else
                    throw new InvalidFormatException("Invalid file format");
                line = buff.readLine();
            }

        } catch (IOException e)
        {

        }

        return spotter;
    }

    /*Authenticating the existing of a file*/
    public String fileAuthenticate(String filename) throws MissingFileException, InvalidFormatException
    {
        FileReader fileReader = null;
        FileReader fileReader1 = null;

        String file = "";

        List<PizzaConfig> configArrayList = new ArrayList<>();
        SearchFile search = new SearchFile();

        Path currentPath = Paths.get("");
        Path path = currentPath.toAbsolutePath();
        System.out.println(path.toString());

        try
        {
            if (fileSearcher(search, path, filename))
                System.out.println("File is found and ready to process ...\n");
            else
                throw new MissingFileException("The file is not found, We can process a file of a such a format");

            file = search.searchFile(path, filename);
            fileReader = new FileReader(search.searchFile(path, filename));
            fileReader1 = fileReader;

            if (fileReader1 != null)
                if (!validateFile(fileReader) && fileReader != null)
                    System.out.println("The file is invalid, we shall fix it");
        } catch (IOException e)
        {
        }

        return file;
    }

    /*Configuring the property file to a pizzaconfig object*/
    public PizzaConfig buildPizzaProperty(Properties properties)
    {
        List<PizzaConfig> configArrayList = new ArrayList<>();
        PizzaConfig pizzaConfig = new PizzaConfig();
        StructExcp excp = new StructExcp(null, null, null, 0);
        try
        {
            if (properties.getProperty("PizzaName") != null)
                pizzaConfig.setPizzaName(properties.getProperty("PizzaName"));

            if (properties.getProperty("BasePrice") != null)
                pizzaConfig.setBasePrice(Double.parseDouble(properties.getProperty("BasePrice")));

            for (int i = 1; i <= 5; i++)
            {
                if (properties.getProperty("Optionset" + i + "") != null)
                {
                    pizzaConfig.addOptionSets(properties.getProperty("Optionset" + i + ""));

                    if (properties.getProperty("OptionName" + i + "a") != null && properties.getProperty("OptionPrice" + i + "a") != null)
                        pizzaConfig.addOptionSetOption(
                                properties.getProperty("Optionset" + i),
                                properties.getProperty("OptionName" + i + "a"),
                                Double.parseDouble(properties.getProperty("OptionPrice" + i + "a")));

                    if (properties.getProperty("OptionName" + i + "b") != null && properties.getProperty("OptionPrice" + i + "b") != null)
                        pizzaConfig.addOptionSetOption(
                                properties.getProperty("Optionset" + i),
                                properties.getProperty("OptionName" + i + "b"),
                                Double.parseDouble(properties.getProperty("OptionPrice" + i + "b")));

                    if (properties.getProperty("OptionName" + i + "c") != null && properties.getProperty("OptionPrice" + i + "c") != null)
                        pizzaConfig.addOptionSetOption(
                                properties.getProperty("Optionset" + i),
                                properties.getProperty("OptionName" + i + "c"),
                                Double.parseDouble(properties.getProperty("OptionPrice" + i + "c")));

                    if (properties.getProperty("OptionName" + i + "d") != null && properties.getProperty("OptionPrice" + i + "d") != null)
                        pizzaConfig.addOptionSetOption(
                                properties.getProperty("Optionset" + i),
                                properties.getProperty("OptionName" + i + "d"),
                                Double.parseDouble(properties.getProperty("OptionPrice" + i + "d")));

                    if (properties.getProperty("OptionName" + i + "e") != null && properties.getProperty("OptionPrice" + i + "e") != null)
                        pizzaConfig.addOptionSetOption(
                                properties.getProperty("Optionset" + i),
                                properties.getProperty("OptionName" + i + "e"),
                                Double.parseDouble(properties.getProperty("OptionPrice" + i + "e")));

                    if (properties.getProperty("OptionName" + i + "f") != null && properties.getProperty("OptionPrice" + i + "f") != null)
                        pizzaConfig.addOptionSetOption(
                                properties.getProperty("Optionset" + i),
                                properties.getProperty("OptionName" + i + "f"),
                                Double.parseDouble(properties.getProperty("OptionPrice" + i + "f")));
                }

            }

        } catch (PizzaException e)
        {
            e.fixError(PizzeriaConfigAPI.getInstance(), excp);
        }

        return pizzaConfig;
    }
}

