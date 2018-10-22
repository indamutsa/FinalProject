package exceptions;

import io.BuildPizzaConfig;
import model.PizzaConfig;
import util.SearchFile;

import java.io.FileReader;
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

public class MissingFileException extends Exception
{
    public MissingFileException(String message)
    {
        super(message);
    }

//    public FileReader fixMissingFile(Path path,String fileN, SearchFileTest s)
//    {
//        String fileName = null;
//        FileReader file = null;
//
//        fileName = s.searchFile(path,fileN,0);
//        if(fileName == "")
//        {
//            System.out.println("Sorry, We could not find the file specified\nWe created a dummy file for you");
//
//            try
//            {
//                file = new FileReader("recovery.txt");
//            }
//            catch (IOException e)
//            { }
//            return file;
//        }
//        try
//        {
//            file = new FileReader(fileName);
//        }
//        catch (IOException e)
//        { }
//        return file;
//    }

    public List<PizzaConfig> fixMissingFile(Path path, SearchFile s)
    {
        List<PizzaConfig> configList = new ArrayList<>();
        BuildPizzaConfig buildPizza = new BuildPizzaConfig();
        System.out.println("Sorry, your file is missing\nWe created a dummy file for you");
        try
        {
            String fileName = null;
            FileReader file = null;

            fileName = s.searchFile(path, "recovery.txt");
            configList = buildPizza.buildPizzaConfig(fileName);
        } catch (Exception e)
        {
            System.out.println("Provide a file");
        }

        return configList;
    }

}
