package scaletests;

/*
 * This is one the program sumulating the user testing our PizzeriaConfigAPI
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import testcases.PizzaTest;
import testcases.PizzaTest4;
import testcases.PizzaTest5;

import wrapper.PizzeriaConfigAPI;

public class SimulatedUser4 extends SimulatedUser
{
    public SimulatedUser4(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        super(pizzeriaConfigAPI);
    }

    @Override
    public void run()
    {
        PizzaTest pizzaTest4 = new PizzaTest4();
        pizzaTest4.executeTest(pizzeriaConfigAPI);
    }
}
