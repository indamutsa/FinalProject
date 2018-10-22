package util;
/**
 * This program is a protocol for our communication
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import java.io.Serializable;

//This object will be used for communication
public class Courrier implements Serializable
{
    //Instance variables
    private String message;
    private Object object;
    private int index;
    private int pizzaNameIndex;
    private int optionsetIndex;
    private String optionName;
    private double price;
    private String [] selectedElements;
    private  String dbChoice;




    public Courrier()
    {
        this.message = "";
    }

    /*Getters and setters for our protocol object*/
    public int getPizzaNameIndex()
    {
        return pizzaNameIndex;
    }

    public void setPizzaNameIndex(int pizzaNameIndex)
    {
        this.pizzaNameIndex = pizzaNameIndex;
    }

    public int getOptionsetIndex()
    {
        return optionsetIndex;
    }

    public void setOptionsetIndex(int optionsetIndex)
    {
        this.optionsetIndex = optionsetIndex;
    }

    public String getOptionName()
    {
        return optionName;
    }

    public void setOptionName(String optionName)
    {
        this.optionName = optionName;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public Courrier(String message, Object object)
    {
        this();
        this.object = object;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    public String[] getSelectedElements()
    {
        return selectedElements;
    }

    public void setSelectedElements(String[] selectedElements)
    {
        this.selectedElements = selectedElements;
    }

    public String getDbChoice()
    {
        return dbChoice;
    }

    public void setDbChoice(String dbChoice)
    {
        this.dbChoice = dbChoice;
    }

    @Override
    public String toString()
    {
        return "Courrier{" +
                "message='" + message + '\'' +
                ", object=" + object +
                ", index=" + index +
                ", pizzaNameIndex=" + pizzaNameIndex +
                ", optionsetIndex=" + optionsetIndex +
                ", optionName='" + optionName + '\'' +
                ", price=" + price +
                '}';
    }
}
