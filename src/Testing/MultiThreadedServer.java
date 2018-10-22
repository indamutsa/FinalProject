package Testing;

import java.io.IOException;
import java.net.ServerSocket;


/*This was TCP/IP demonstration*/
public class MultiThreadedServer
{
    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(5000))
        {
            {
                while (true)
                {
                    new Echoer(serverSocket.accept()).start();
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
