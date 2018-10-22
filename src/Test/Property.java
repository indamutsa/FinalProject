package Test;

import java.io.FileInputStream;
import java.util.Properties;

public class Property
{
    public static void main(String [] args) throws Exception
    {
        Properties props= new Properties();
        FileInputStream in = new FileInputStream("PizzaData.properties");
        props.load(in);

        System.out.println(props.getProperty("PizzaName1"));
        System.out.println(props.getProperty("Optionset11"));
        System.out.println(props.getProperty("OptionName11a"));

        System.out.println(props.getProperty("PizzaName2"));
        System.out.println(props.getProperty("OptionName12a"));
        System.out.println(props.getProperty("OptionPrice12a"));
//        props.list(System.out);
    }
}
