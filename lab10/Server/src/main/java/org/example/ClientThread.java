package main.java.org.example;

import java.io.*;
import java.net.Socket;

/**
 * This class represents a thread that handles communication with a single client.
 */
class ClientThread extends Thread {
    /**
     * Processes the client's request and generates a response.
     * @param request the request received from the client.
     * @return the response to be sent back to the client.
     */
    public static String processRequest(String request) {
        String response;
        if(request.equals("stop")) {
            response = "Server stopped";
        } else {
            response = "Server received the request: " + request;
        }
        return response;
    }

    private Socket socket;

    /**
     * Constructs a new ClientThread object with the specified socket.
     * @param socket the socket associated with the client connection.
     */
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Sends a message to the client.
     * @param out the DataOutputStream used to send the message.
     * @param message the message to be sent.
     * @throws IOException if an I/O error occurs while sending the message.
     */
    public static void sendToClient(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();
        System.out.println("Server sent: " + message);
    }

    /**
     * Receives a message from the client.
     * @param in the DataInputStream used to receive the message.
     * @return the message received from the client.
     * @throws IOException if an I/O error occurs while receiving the message.
     */
    public static String receiveFromClient(DataInputStream in) throws IOException {
        String message = in.readUTF();
        System.out.println("Server received: " + message);
        return message;
    }

    /**
     * The main execution logic of the client thread.
     */
    public void run() {
        try {
            while(true) {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String request = receiveFromClient(in);
                String response = processRequest(request);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                sendToClient(out, response);
                if(request.equals("stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket... " + e);
            }
        }
    }
}
