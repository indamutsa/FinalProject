package exceptions;

/*
 * This class is responsible of implementing the factory design pattern per se
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class ExceptionFactory
{
    //Given an identifier(in this case it is a string object), it creates and return the relevant object
    public static PizzaException getPizzaException(String eDescription)
    {
        switch (eDescription)
        {
            case "Incorrect base price":
                return new IncorrectBasePricePizzaException("Incorrect base price exception");
            case "Missing pizza name":
                return new MissingNamePizzaException("Missing pizza name exception");
            case "Duplicate option name":
                return new AlreadyExistOption("You cannot add an existing option");
            case "Duplicate optionset name":
                return new AlreadyExistOptionSet("You cannot add an existing optionset");
            case "Non-existant optionset":
                return new UpdateNameException("The specified optionset does not exist");
            case "Incorrect optionset":
                return new IncorrectOptionsetName("Unable to find the optionset specified");
            case "Duplicate configuration":
                return new AlreadyExistConfig("Duplicate pizzeria configuration exception");
            default:
                return null;
        }
    }
}
