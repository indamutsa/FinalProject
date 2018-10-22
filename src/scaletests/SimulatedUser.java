package scaletests;

import wrapper.PizzeriaConfigAPI;

public class SimulatedUser extends Thread
{
    protected PizzeriaConfigAPI pizzeriaConfigAPI;

    public SimulatedUser(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        this.pizzeriaConfigAPI = pizzeriaConfigAPI;
    }

    public static void pause(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) {}
    }

    @Override
    public String toString()
    {
        return "SimulatedUser{" +
                "pizzeriaConfigAPI=" + pizzeriaConfigAPI +
                '}';
    }
}
