package Test;

import java.util.logging.Level;

public class Main
{
    public static void main(String [] args)
    {
        try
        {
            Log mylog = new Log("log.txt");
            mylog.logger.setLevel(Level.WARNING);
            mylog.logger.info("IMfo mdg");
            mylog.logger.info("Waring msg");
            mylog.logger.info("Severe mdg");

        }
        catch (Exception e)
        {

        }
    }
}
