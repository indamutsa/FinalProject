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
import testcases.PizzaTest8;
import wrapper.PizzeriaConfigAPI;

public class SimulatedUser2 extends SimulatedUser
{
    public SimulatedUser2(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        super(pizzeriaConfigAPI);
    }

    @Override
    public void run()
    {
        PizzaTest pizzaTest = new PizzaTest8();
        pizzaTest.executeTest(pizzeriaConfigAPI);
    }
}
