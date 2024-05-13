package main.java.org.example;

import java.io.*;
import java.net.Socket;

/**
 * This class represents a simple client that communicates with a server
 * over a network socket.
 */
public class Client {
    /**
     * Sends a message to the server.
     * @param out the DataOutputStream used to send the message.
     * @param message the message to be sent.
     * @throws IOException if an I/O error occurs while sending the message.
     */
    public static void sendToServer(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();
        System.out.println("Client sent: " + message);
    }

    /**
     * Receives a message from the server.
     * @param in the DataInputStream used to receive the message.
     * @return the message received from the server.
     * @throws IOException if an I/O error occurs while receiving the message.
     */
    public static String receiveFromServer(DataInputStream in) throws IOException {
        String message = in.readUTF();
        System.out.println("Client received: " + message);
        return message;
    }

    /**
     * The main method to start the client.
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (true) {
                System.out.println("Type a message to send to the server, or 'exit' to quit:");
                String userInput = keyboard.readLine();

                sendToServer(out, userInput);

                String response = receiveFromServer(in);
                System.out.println("Response from server: " + response);
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
