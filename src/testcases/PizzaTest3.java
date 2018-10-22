package testcases;

import wrapper.PizzeriaConfigAPI;

/* This is one the program testing our PizzaConfig class
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */


public class PizzaTest3 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        String fileName1 = "PizzeriaConfigurations.txt";
        pizzeriaConfigAPI.configurePizzeria(fileName1);

        String fileName2 = "Pizzeria.txt";
        String fileName3 = "Wrong file";
        pizzeriaConfigAPI.configurePizzeria(fileName2);
        pizzeriaConfigAPI.configurePizzeria(fileName3);
    }
}
