package testcases;

/**
 * This program is responsible for managing pizza option sets
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import wrapper.PizzeriaConfigAPI;

public abstract class PizzaTest
{
    public abstract void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI);

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pizza test : \n-----------");
        return stringBuilder.toString();
    }


}
