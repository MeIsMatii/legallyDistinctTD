package util.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkClient implements MultiplayerConnection{
    private final String hostIp;
    private final int port;
    private PrintWriter out;

    public NetworkClient(String hostIP, int port) {
        this.hostIp = hostIP;
        this.port = port;
    }

    public void send(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Client: Connecting to " + hostIp + ":" + port + "...");
            Socket socket = new Socket(hostIp, port);
            System.out.println("Client: Successfully connected to host!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true)  { //endlosschleife wohoo --Mathilo
                String msg = in.readLine();
                if(msg != null) {
                    NetworkManager.getInstance().queueIncomingMessage(msg);
                    System.out.println("Incoming message: " + msg);
                }
            }

        } catch (IOException e) {
            System.err.println("Client Connection Error: " + e.getMessage());
        }
    }
}
