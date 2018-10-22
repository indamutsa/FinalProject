package util;
/**
 * This program supports the exception recovery
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


public class StructExcp
{
    private String pizzaname;
    private String optionset;
    private String optionname;
    private double price;

    public StructExcp(String pizzaname, String optionset, String optionname, double price)
    {
        this.pizzaname = pizzaname;
        this.optionset = optionset;
        this.optionname = optionname;
        this.price = price;
    }

    public String getPizzaname()
    {
        return pizzaname;
    }

    public void setPizzaname(String pizzaname)
    {
        this.pizzaname = pizzaname;
    }

    public String getOptionset()
    {
        return optionset;
    }

    public void setOptionset(String optionset)
    {
        this.optionset = optionset;
    }

    public String getOptionname()
    {
        return optionname;
    }

    public void setOptionname(String optionname)
    {
        this.optionname = optionname;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
