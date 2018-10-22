package Testing;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Properties;

public class Server1 extends Thread
{

    /**
     * The port where the client is listening.
     */
    private final int clientPort;

    public Server1(int clientPort) {
        this.clientPort = clientPort;
    }
    public void run()
    {
        try
        {
            System.out.println("Server1 up & running ...");
            DatagramSocket socket = new DatagramSocket(clientPort);
            byte [] buffer = new byte[65506];

            while (true)
            {
                //Instantiating and receiving the packet
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                //Retrieving the packet in bytes
                byte[] incomingData = packet.getData();

                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(incomingData);
                ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);

                //Casting back the retrieved object as Properties
                try
                {
                    Properties properties = (Properties) inputStream.readObject();
                    System.out.println("Properties object received" + properties);
                }catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }

                //Echoing  out what we received
                System.out.println("Text received is: " + new String(buffer, 0, packet.getLength()));
                String returnString = "echo: " + new String(buffer, 0, packet.getLength());

                //We are going to echo back what we recieved
                byte [] buffer2 = returnString.getBytes();

                //Getting the address of the incoming packet, port and packet
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                DatagramPacket sentpacket = new DatagramPacket(buffer2, buffer2.length, address, port);

                Thread.sleep(5000);

                //Sending the packet back
                socket.send(sentpacket);
            }
        }
        catch (SocketException e)
        {
            System.out.println("SocketException: " + e.getMessage());
        }
        catch (InterruptedException e)
        {

        }
        catch (IOException e)
        {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
