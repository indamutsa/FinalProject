package Test;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient
{
    public static void main(String args[])
    {
        try
        {
            Socket s = new Socket("localhost", 2002);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            testobject to = new testobject(1, "object from client");
            oos.writeObject(to);
            oos.writeObject(new String("another object from the client"));

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}