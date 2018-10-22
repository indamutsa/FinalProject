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
import testcases.PizzaTest7;
import wrapper.PizzeriaConfigAPI;

public class SimulatedUser1 extends SimulatedUser
{

    public SimulatedUser1(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        super(pizzeriaConfigAPI);
    }

    @Override
    public void run()
    {
        PizzaTest pizzaTest = new PizzaTest7();
        pizzaTest.executeTest(pizzeriaConfigAPI);
    }
}
