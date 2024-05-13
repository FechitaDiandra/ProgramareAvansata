package main.java.org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents a simple server that listens for incoming client connections
 * on a specified port.
 */
public class Server {
    /** The port number on which the server listens for incoming connections. */
    public static final int PORT = 8100;

    /**
     * Constructs a new Server object and starts listening for incoming connections.
     * @throws IOException if an I/O error occurs when creating the ServerSocket.
     */
    public Server() throws IOException {
        ServerSocket serverSocket = null ;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                System.out.printf("Client connected: %s:%d%n",
                        socket.getInetAddress(), socket.getPort());
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    /**
     * The main method to start the server.
     * @param args command line arguments (not used).
     * @throws IOException if an I/O error occurs when creating the Server object.
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}
