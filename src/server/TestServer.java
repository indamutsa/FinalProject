package server;
/**
 * This program is runs the server side of this program
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import wrapper.PizzeriaAPI;

public class TestServer
{
    public static void main(String[] args)
    {
        if (args.length  > 0)
        {
            String db = args[0];
            System.out.println(db);
            PizzeriaServer pizzeriaServer = new PizzeriaServer();
            PizzeriaAPI api = pizzeriaServer.getAPI(db);

            if(api !=null)
                pizzeriaServer.runServer(5000, api);

        } else
        {
            System.out.println("You must enter 1 to choose linkedhashmap or 2 to choose database." +
                    "\nPlease try again");
        }
    }
}
