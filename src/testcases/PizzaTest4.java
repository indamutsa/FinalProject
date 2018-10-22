package testcases;

/*
 * This program will test our PizzaConfig class' getter methods
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import wrapper.PizzeriaConfigAPI;
import wrapper.UpdatePizzeria;

public class PizzaTest4 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        UpdatePizzeria updatePizzeria = pizzeriaConfigAPI;

        updatePizzeria.updatePizzeriaName("Quattro Stagioni", "Changed Quattro Stagioni");
        updatePizzeria.updateBasePrice("Quattro Stagioni", 3600);

        updatePizzeria.updatePizzeriaName("Napoletana", "Changed Napoletana");
        updatePizzeria.updateBasePrice("Napoletana", 4200);

        updatePizzeria.updatePizzeriaName("Carbonara", "Changed Carbonara");
        updatePizzeria.updateBasePrice("Carbonara", 3652);
    }
}

