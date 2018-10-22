package serverUtils;
/**
 * This program process the request from the client using strategy pattern
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import util.Courrier;
import wrapper.PizzeriaAPI;
import wrapper.PizzeriaConfigAPI;

import java.io.ObjectOutputStream;

public class StrategyContext
{
    private Strategy strategy;

    public StrategyContext(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void executeStrategy(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)
    {
        strategy.processRequest(oos, cargo, api);
    }

}
