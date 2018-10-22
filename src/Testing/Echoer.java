package Testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*This is TCP/IP demonstration*/

public class Echoer extends Thread
{
    private Socket socket;

    public Echoer(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            while (true)
            {
                String echoString = input.readLine();
                System.out.println("ServerThread receieved: " + echoString);
                if (echoString.equals("exit"))
                    break;
/*                try
                {
                    Thread.sleep(6000);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Thread interrupted");
                }*/

                output.println(echoString);
            }
        } catch (IOException e)
        {
            System.out.println("Ooops: " + e.getMessage());
        } finally
        {
            try
            {
                socket.close();
            } catch (IOException e)
            {

            }
        }
    }

}
