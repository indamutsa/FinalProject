package Testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class MultiThreadClient
{
    public static void main(String[] args)
    {
        try (Socket socket = new Socket("localhost", 5000)) //127.0.0.1
        {
//            socket.setSoTimeout(3200);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringtoEcho = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do
            {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                stringtoEcho.println(echoString);
                if (!echoString.equals("exit"))
                {
                    response = input.readLine();
                    System.out.println(response);
                }
            } while (!echoString.equals("exit"));

            //To terminate the client
        } catch (SocketTimeoutException e)
        {
            System.out.println("The socket timed out");
        } catch (IOException e)
        {
            System.out.println("Client error: " + e.getMessage());
        }

    }
}
