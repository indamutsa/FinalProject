package Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Properties;

public class Server extends Thread
{

    /**
     * The port where the client is listening.
     */
    private final int clientPort;

    public Server(int clientPort) {
        this.clientPort = clientPort;
    }
    public void run()
    {
        try
        {
            System.out.println("Server1 up & running ...");
            DatagramSocket socket = new DatagramSocket(clientPort);
            byte [] incomingData = new byte[65506];

            while (true)
            {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                System.out.println("Text received is: " + new String(incomingData, 0, incomingPacket.getLength()));

                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);

                try {
                    Properties properties = (Properties) is.readObject();
                    System.out.println("Student object received = "+properties.toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                String returnString = "echo: " + new String(incomingData, 0, incomingPacket.getLength());
                byte [] buffer2 = returnString.getBytes();
                InetAddress address = incomingPacket.getAddress();
                int port = incomingPacket.getPort();
                incomingPacket = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(incomingPacket);
            }
        }
        catch (SocketException e)
        {
            System.out.println("SocketException: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
