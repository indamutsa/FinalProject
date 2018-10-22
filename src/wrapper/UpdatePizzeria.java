package wrapper;

import model.PizzaConfig;

import java.util.List;

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

public interface UpdatePizzeria
{
    void updateOptionSetName(String pizzeriaName, String optionSetName, String newOptionSetName);
    void updateBasePrice(String pizzeriaName, double newBasePrice);
    void updateOptionPrice(String pizzeriaName, String optionSetName, String optionName, double newOptionPrice);
    void optionUpdater(String pizzeriaName, String optionSetName, String optionName, String newoptionName, double newOptionPrice);
    void updatePizzeriaName(String pizzeriaName, String newPizzeriaName);
    void deleteOptionSet(String pizzeriaName, String optionset);
    PizzaConfig retrivePizza(String pizzeriaName);
    List<String> optionSetList(String pizzeriaName);
    double calculatePrice(String pizzaname,String[] array);
}
