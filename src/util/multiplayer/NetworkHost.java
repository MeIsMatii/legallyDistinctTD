package util.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkHost implements MultiplayerConnection{
    private final int port;
    private PrintWriter out;

    public NetworkHost(int port) {
        this.port = port;
    }

    public void send(String msg) {
        if(out != null) {
            out.println(msg);
        }
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Host: Waiting for client on port " + port + "...");
            Socket clientsocket = serverSocket.accept();
            System.out.println("Host: Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            this.out = new PrintWriter(clientsocket.getOutputStream(), true);

            while (true)  { //endlosschleife wohoo --Mathilo
                String msg = in.readLine();
                if(msg != null) {
                    NetworkManager.getInstance().queueIncomingMessage(msg);
                    System.out.println("Incoming message: " + msg);
                }
            }
        } catch (IOException e) {
            System.err.println("Host Connection Error: " + e.getMessage());
        }
    }
}
