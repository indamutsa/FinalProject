package server;
/**
 * This program handles the multi-threading of the program
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import serverUtils.StrategyContext;
import serverUtils.StrategyFactory;
import util.Courrier;
import wrapper.PizzeriaAPI;
import wrapper.PizzeriaConfigAPI;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class ServerThread extends Thread
{
    //    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private PizzeriaAPI api;
    private Courrier cargo;
    ;
    private List<String> listPizza;
    private List<String> listOptionSet;

    private StrategyContext strategyContext;

    /*The constructor for the thread*/
    public ServerThread(ObjectOutputStream oos, ObjectInputStream ois, Courrier cargo, PizzeriaAPI api)
    {
        this.objectInputStream = ois;
        this.objectOutputStream = oos;
        this.api = api;
        this.cargo = cargo;
    }


    /*Our thread per se*/
    @Override
    public void run()
    {
        StrategyContext pizzaProtocol = StrategyFactory.getProtocol(cargo);
        pizzaProtocol.executeStrategy(objectOutputStream, cargo, api);
    }


}
