package testcases;

/*
 * This is one the program testing our PizzaConfigAPI class
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import wrapper.CreatePizzeria;
import wrapper.PizzeriaConfigAPI;


/*This class particulary tests how to update data using the API via UpdatePizzeria instance*/

public class PizzaTest8 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {

        CreatePizzeria createPizzeria = pizzeriaConfigAPI;

        createPizzeria.createPizzeria("Calzone", 3000.00);

        createPizzeria.addAnOptionset("Calzone", "Vegetable choices");
        createPizzeria.addAnOption("Calzone", "Vegetable choices", "Broccoli", 0);
        createPizzeria.addAnOption("Calzone", "Vegetable choices", "Maize", 0);
        createPizzeria.addAnOption("Calzone", "Vegetable choices", "Peas", 0);
    }
}

