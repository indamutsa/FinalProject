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
public class PizzaTest1 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        CreatePizzeria createPizzeria = pizzeriaConfigAPI;

        createPizzeria.createPizzeria("Margeritta", 0.00);

        createPizzeria.addAnOptionset("Margeritta", "");
        createPizzeria.addAnOption("Margeritta", "Pizza sizes", "Small", 0);
        createPizzeria.addAnOption("Margeritta", "Pizza sizes", "Medium", 0);
        createPizzeria.addAnOption("Margeritta", "Pizza sizes", "Large", 0);

        createPizzeria.addAnOptionset("Margeritta","Meat choices");
        createPizzeria.addAnOption("Margeritta","Meat choices", "Beef", 0);
        createPizzeria.addAnOption("Margeritta","Meat choices", "Ham", 0);
        createPizzeria.addAnOption("Margeritta","Meat choices", "Pepperoni", 1000);

        createPizzeria.addAnOptionset("Margeritta","Vegetable choices");
        createPizzeria.addAnOption("Margeritta","Vegetable choices", "Broccoli", 0);
        createPizzeria.addAnOption("Margeritta","Vegetable choices", "Tomatoes", 0);
        createPizzeria.addAnOption("Margeritta","Vegetable choices", "mushroom", 0);

        createPizzeria.addAnOptionset("Margeritta","Cheese choices");
        createPizzeria.addAnOption("Margeritta","Cheese choices", "Camembert", 0);
        createPizzeria.addAnOption("Margeritta","Cheese choices", "Stracchino", 0);
        createPizzeria.addAnOption("Margeritta","Cheese choices", "Manchego", 0);

        createPizzeria.addAnOptionset("Margeritta","Delivery mode");
        createPizzeria.addAnOption("Margeritta","Delivery mode", "Eat-in", 0);
        createPizzeria.addAnOption("Margeritta","Delivery mode", "Take-away", 0);
        createPizzeria.addAnOption("Margeritta","Delivery mode", "Delivered", 0);
    }
}
