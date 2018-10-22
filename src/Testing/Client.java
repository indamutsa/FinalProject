package Testing;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoString;

            System.out.println("Enter string to be echoed: ");
            echoString = scanner.nextLine();
            byte [] buffer = new byte[65506];

            while (!echoString.equals("exit"))
            {
                //Reading the plain file to the properties object
                Properties props= new Properties();
                FileInputStream in = new FileInputStream("PizzaData.properties");
                props.load(in);

                //Serializing the object
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(props);

                //Retrieving the serialized object and sending it as a packet
                byte [] outData = outputStream.toByteArray();
                DatagramPacket outPacket = new DatagramPacket(outData, outData.length, address, 6333);
                datagramSocket.send(outPacket);

                //Message echoed back from the server
                System.out.println("Message sent from Server1: ");
                DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(inPacket);

                //Getting an object to display it
                String response = new String(inPacket.getData());
                System.out.println("Response from server:" + response);

                Thread.sleep(2000);

                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();
            }
        } catch (SocketException e)
        {
            System.out.println("The socket timed out");
        }
        catch (InterruptedException e)
        {

        }
        catch (IOException e)
        {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
