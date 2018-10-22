package testcases;

/*
 * This is the main test program for all our test cases PizzaTest classes
 *
 * @author Arsene INDAMUTSA, Cathy Bishop
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import scaletests.*;
import wrapper.PizzeriaConfigAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDriver
{
    public static void main(String[] args)
    {
        PizzeriaConfigAPI pizzeriaConfigAPI = PizzeriaConfigAPI.getInstance();

        ArrayList<PizzaTest> pizzaTests = new ArrayList<>();
        UserFactory userFactory = new UserFactory();

        pizzaTests.add(new PizzaTest1());
        pizzaTests.add(new PizzaTest2());
        pizzaTests.add(new PizzaTest3());


        for (PizzaTest pizzaTest : pizzaTests)
            pizzaTest.executeTest(pizzeriaConfigAPI);

        UserFactory.getSimulator(4, pizzeriaConfigAPI).start();
        UserFactory.getSimulator(5, pizzeriaConfigAPI).start();
        UserFactory.getSimulator(6, pizzeriaConfigAPI).start();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        int i;
        for (i = 4; i < 7; i++)
        {
            threadPool.execute(UserFactory.getSimulator(i, pizzeriaConfigAPI));
        }
        threadPool.shutdown();
        while (!threadPool.isTerminated())
        {
            System.out.println("main() is waiting");
            SimulatedUser.pause(1000);
        }
        System.out.println("main Ending");

        pizzeriaConfigAPI.printPizzerias();
    }
}
