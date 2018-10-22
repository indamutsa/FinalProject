package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile
{
    public static void main(String[] arguments)
    {
        try
        {
            FileReader file = new FileReader("/home/arsene/IdeaProjects/Project 1.3/recovery.txt");
            BufferedReader buff = new BufferedReader(file);
            String line;

            line = buff.readLine();
            while (line != null)
            {
                System.out.println(line);
                line = buff.readLine();
            }
            buff.close();

        }
        catch (IOException e)
        {
            System.out.println("Error " + e.toString());
        }
    }
}
