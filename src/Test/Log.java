package Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log
{
    public Logger logger;
    FileHandler fileHandler;

    public Log( String filename) throws SecurityException, IOException
    {
        File file = new File(filename);
        if(!file.exists())
            file.createNewFile();

        // This block configure the logger with handler and formatter
        fileHandler = new FileHandler(filename, true);
        logger = Logger.getLogger("bufljdjyd");
        logger.addHandler(fileHandler);

        //Avoiding using XML data and employing a nice formatter
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
    }
}
