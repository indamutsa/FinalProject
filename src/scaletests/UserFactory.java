package scaletests;


import exceptions.PizzaException;
import wrapper.PizzeriaConfigAPI;

public class UserFactory
{
    //Given an identifier(in this case it is a string object), it creates and return the relevant object
    public static SimulatedUser getSimulator(int simulator, PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        switch (simulator)
        {
            case 1:
                return new SimulatedUser1(pizzeriaConfigAPI);
            case 2:
                return new SimulatedUser2(pizzeriaConfigAPI);
            case 3:
                return new SimulatedUser3(pizzeriaConfigAPI);
            case 4:
                return new SimulatedUser4(pizzeriaConfigAPI);
            case 5:
                return new SimulatedUser5(pizzeriaConfigAPI);
            case 6:
                return new SimulatedUser6(pizzeriaConfigAPI);
            default:
                return null;
        }
    }
}
