package Test;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Properties;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Properties props= new Properties();
        FileInputStream in = new FileInputStream("PizzaData.properties");
        props.load(in);

        try
        {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoString;

            System.out.println("Enter string to be echoed: ");
            echoString = scanner.nextLine();

            while (!echoString.equals("exit"))
            {
                // Serialize to a byte array
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutput objectOutput = new ObjectOutputStream(outputStream);
                objectOutput.writeObject(props);


                byte[] buffer = outputStream.toByteArray();
                DatagramPacket sentPacket = new DatagramPacket(buffer, buffer.length, address, 6333);

                datagramSocket.send(sentPacket);

                System.out.println("Message sent from the client: ");

                byte[] incomingBuffer = new byte[65506];
                DatagramPacket incomingPacket = new DatagramPacket(incomingBuffer, incomingBuffer.length);

                datagramSocket.receive(sentPacket);

                objectOutput.close();
                System.out.println("Text received is: " + new String(incomingPacket.getData(), 0, sentPacket.getLength()));

                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

            }
        } catch (SocketException e)
        {
            System.out.println("The socket timed out");
        } catch (IOException e)
        {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
