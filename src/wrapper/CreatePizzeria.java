package wrapper;

import exceptions.PizzaException;
import model.PizzaConfig;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;


/*
 * This an interface for my wrapper classes
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public interface CreatePizzeria
{
    void createPizzeria(String pizzeriaName, double price);
    boolean configurePizzeria(String filename);
    boolean configurePizzeria(Properties properties);
    void printPizzeria(String pizzeriaName);
    void addAnOptionset(String pizzeriaName, String optionsetName);
    void addAnOption(String pizzeriaName, String optionsetName, String optionName, double price);
    void printPizzerias();
    void deletePizzeria(String key);
    List<String> pizzaList();
}
