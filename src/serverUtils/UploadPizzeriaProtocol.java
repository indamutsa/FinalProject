package serverUtils;
/**
 * This program upload a property file of pizza
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
import java.util.Properties;

public class UploadPizzeriaProtocol implements Strategy
{
    @Override
    public void processRequest(ObjectOutputStream oos, Courrier cargo, PizzeriaAPI api)
    {
        try
        {
            Properties properties = (Properties) cargo.getObject();

            boolean check = api.configurePizzeria(properties);
            System.out.println(check);
            if(check)
            {
                oos.writeObject("   PizzeriaServer response: File uploaded successfully\n");
                System.out.println(api);
            }
            else
                oos.writeObject("   PizzeriaServer response: We already have this pizza in our database\n");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Upload pizzeria exception");
        }
    }
}
