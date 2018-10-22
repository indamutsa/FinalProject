package server;

/*
 * This is the server of our program
 *
 * @author Arsene INDAMUTSA, Cathy Bishop
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import util.Courrier;
import wrapper.DBPizzeriaConfigAPI;
import wrapper.PizzeriaAPI;
import wrapper.PizzeriaConfigAPI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PizzeriaServer implements PizzeriaInterface
{
    static boolean keepRunning = true;


    /*This method is responsible of establishing
    the socket(connection) with the client and
    to run the server side of this program*/
    @Override
    public void runServer(int port, PizzeriaAPI api)
    {
        try (ServerSocket serverSocket = openSocket(port, 25896314))
        {
            System.out.printf("PizzeriaServer up & running at %d...\n", port);


            while (keepRunning)
            {
                Socket socket = acceptConnection(serverSocket);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                Courrier cargo = (Courrier) ois.readObject();

                ServerThread serverThread = new ServerThread(oos, ois, cargo, api);

                serverThread.start();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {

        }
    }

    /*This method maintains the socket until a given time,
     * It is terminated whern time is out
     * Wriiten by @Cathy Bishop, CMU-Africa DPSD*/
    @Override
    public ServerSocket openSocket(int portNumber, int acceptTimeout)
    {
        ServerSocket socket = null;
        try
        {
            socket = new ServerSocket(portNumber);
            if (acceptTimeout > 0)
                socket.setSoTimeout(acceptTimeout);
        } catch (BindException e)
        {
            System.err.println("PizzeriaServer cannot bind to port " + portNumber);
            System.err.println(e);
            System.exit(3);
        } catch (Exception e)
        {
            System.err.println("PizzeriaServer caught exception when trying to create socket ");
            System.err.println(e);
        }

        return socket;
    }


    /*This method accept the connection on a given socket,
     * Wriiten by @Cathy Bishop, CMU-Africa DPSD*/
    @Override
    public Socket acceptConnection(ServerSocket socket)
    {
        try
        {
            Socket clientSocket = socket.accept();
            return clientSocket;

        } catch (SocketTimeoutException e)
        {
            System.out.println("PizzeriaServer timed out waiting for a connection.");
        } catch (IOException e)
        {
            System.err.println("\nPizzeriaServer caught exception when trying to listen on port "
                    + socket.getLocalPort());
            System.err.println(e);
        }
        return null;
    }

    public PizzeriaAPI getAPI(String dbchoice)
    {
        PizzeriaAPI api = null;
        System.out.println("===>: " +dbchoice);

        if (dbchoice.equals("database"))
            api =  DBPizzeriaConfigAPI.getInstance();

        else if (dbchoice.equals("LHM"))
            api = PizzeriaConfigAPI.getInstance();

       return api;
    }
}

