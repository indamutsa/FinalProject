package exceptions;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ExceptionLogs
{
    private Logger logger;
    private FileHandler fileHandler;

    private static ExceptionLogs instance;

    private ExceptionLogs() {
    }

    public static ExceptionLogs getInstance(String filename) {
        if(instance == null) {
            try
            {
                instance = new ExceptionLogs( filename);
            }
            catch (Exception e)
            { }
        }

        return instance;
    }

    public ExceptionLogs( String filename) throws SecurityException, IOException
    {
        File file = new File(filename);
        if(file.exists())
            file.createNewFile();

        // This block configure the logger with handler and formatter
        fileHandler = new FileHandler(filename, true);
        logger = Logger.getLogger("testcases");
        logger.addHandler(fileHandler);

        //Avoiding using XML data and employing a nice formatter
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
    }

    public Logger getLogger()
    {
        return logger;
    }

    public void setLogger(Logger logger)
    {
        this.logger = logger;
    }
}
