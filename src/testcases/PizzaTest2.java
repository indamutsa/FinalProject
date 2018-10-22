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

public class PizzaTest2 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        CreatePizzeria createPizzeria = pizzeriaConfigAPI;

        createPizzeria.createPizzeria("Mozzarella", 3500.00);

        createPizzeria.addAnOptionset("Mozzarella", "Size");
        createPizzeria.addAnOption("Mozzarella", "Size", "Small", 0);
        createPizzeria.addAnOption("Mozzarella", "Size", "Medium", 0);
        createPizzeria.addAnOption("Mozzarella", "Size", "Large", 0);

        createPizzeria.addAnOptionset("Mozzarella", "Meat choices");
        createPizzeria.addAnOption("Mozzarella", "Meat choices", "pork", 0);
        createPizzeria.addAnOption("Mozzarella", "Meat choices", "goat", 0);
        createPizzeria.addAnOption("Mozzarella", "Meat choices", "chicken", 1000);

        createPizzeria.addAnOptionset("Mozzarella", "Vegetable choices");
        createPizzeria.addAnOption("Mozzarella", "Vegetable choices", "Choux", 0);
        createPizzeria.addAnOption("Mozzarella", "Vegetable choices", "Maize", 0);
        createPizzeria.addAnOption("Mozzarella", "Vegetable choices", "Dodo", 0);

        createPizzeria.addAnOptionset("Mozzarella", "Cheese choices");
        createPizzeria.addAnOption("Mozzarella", "Cheese choices", "No cheese", 0);
        createPizzeria.addAnOption("Mozzarella", "Cheese choices", "Regular cheese", 0);
        createPizzeria.addAnOption("Mozzarella", "Cheese choices", "Extra cheese", 500);

        createPizzeria.addAnOptionset("Mozzarella", "Delivery mode");
        createPizzeria.addAnOption("Mozzarella", "Delivery mode", "Eat-in", 0);
        createPizzeria.addAnOption("Mozzarella", "Delivery mode", "Take-away", 0);
        createPizzeria.addAnOption("Mozzarella", "Delivery mode", "Delivered", 750);
    }
}

