package util.multiplayer;

import greenfoot.Greenfoot;
import maps.levels.*;
import maps.menu.LoadingScreen;
import util.saves.SaveManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class NetworkClient implements MultiplayerConnection {
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
            System.out.println("Client: Connecting to " + hostIp + ":" + port + " ...");
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(hostIp, port), 5000);
            System.out.println("Client: Successfully connected to host!");
            this.out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) { //endlosschleife wohoo --Mathilo
                String msg = in.readLine();
                if(msg != null) {
                    if (msg.startsWith("MAP:")) {
                        setMap(Integer.parseInt(msg.substring(4))); //to load a map
                    } else {
                        NetworkManager.getInstance().queueIncomingMessage(msg);
                        System.out.println("Incoming message: " + msg);
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            System.err.println("Client: Connection timed out! (host not found/available)");

        } catch (IOException e) {
            System.err.println("Client Connection Error: " + e.getMessage());
        }
    }

    public void setMap(int mapNr) {
        GameMap nextWorld;
        switch (mapNr) {
            case 1:
                nextWorld = new GameMap1();
                break;
            case 2:
                nextWorld = new GameMap2();
                break;
            case 3:
                nextWorld = new GameMap3();
                break;
            case 4:
                nextWorld = new GameMap4();
                break;
            case 5:
                nextWorld = new GameMap5();
                break;
            case 6:
                nextWorld = new GameMap6();
                break;
            case 7:
                nextWorld = new GameMap7();
                break;
            case 8:
                nextWorld = new GameMap8();
                break;
            case 9:
                nextWorld = new GameMap9();
                break;

            default:
                throw new RuntimeException("recieved map is invalid");
        }
        LoadingScreen ls = new LoadingScreen();
        Greenfoot.setWorld(ls);
        ls.setNextWorld(nextWorld);

        SaveManager.getInstance().setLastMap(mapNr);
        nextWorld.getGameSaveManager().saveGame();
    }
}
