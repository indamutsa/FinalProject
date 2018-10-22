package server;
/**
 * This is the interface for our pizzeria server
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import wrapper.PizzeriaAPI;
import wrapper.PizzeriaConfigAPI;

import java.net.ServerSocket;
import java.net.Socket;

public interface PizzeriaInterface
{
    void runServer(int port, PizzeriaAPI api);
    ServerSocket openSocket(int portNumber, int acceptTimeout);
    Socket acceptConnection(ServerSocket socket);
}
