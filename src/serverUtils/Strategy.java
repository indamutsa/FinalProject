package serverUtils;
/**
 * Strategy interface
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

public interface Strategy
{
    void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api);
}
