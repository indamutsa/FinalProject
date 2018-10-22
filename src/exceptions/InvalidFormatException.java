package exceptions;

import io.BuildPizzaConfig;
import model.PizzaConfig;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/*
 * This program will search the directory to find a given file
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class InvalidFormatException extends Exception
{
    public InvalidFormatException(String message)
    {
        super(message);
    }

    public  List<PizzaConfig> invalidFormat()
    {
        List<PizzaConfig> configList = new ArrayList<>();
        BuildPizzaConfig buildPizza = new BuildPizzaConfig();
        System.out.println("Sorry, your file is invalid\nPlease refer to the format below");

        try
        {
            configList = buildPizza.buildPizzaConfig("recovery.txt");
        } catch (Exception e)
        {
            System.out.println("Provide a file");
        }

        return configList;
    }
}
