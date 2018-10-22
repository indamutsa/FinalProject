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

public class PizzaTest6 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        pizzeriaConfigAPI.deleteOptionSet("Quattro Stagioni", "Vegetable choices");

        pizzeriaConfigAPI.deletePizzeria("Margeritta");
        pizzeriaConfigAPI.deleteOptionSet("Margeritta", "Meat choices");

        pizzeriaConfigAPI.deleteOptionSet("Carbonara", "Vegetable choices");
    }
}

