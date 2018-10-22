package testcases;

/*
 * This is one the program testing our PizzeriaConfigAPI class
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

/*This class particulary tests how to create and add using the CreatePizzeria instance*/
public class PizzaTest7 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        CreatePizzeria createPizzeria = pizzeriaConfigAPI;

        createPizzeria.createPizzeria("Stromboli", 3695);

        createPizzeria.addAnOptionset("Stromboli", "Size");
        createPizzeria.addAnOption("Stromboli", "Size", "Small", 0);
        createPizzeria.addAnOption("Stromboli", "Size", "Medium", 0);
        createPizzeria.addAnOption("Stromboli", "Size", "Large", 0);
    }
}
