package util.multiplayer;

import greenfoot.Greenfoot;
import maps.levels.*;
import util.saves.SaveManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
/**
 * @author Mathilo
 */
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
            NetworkManager.getInstance().setConnectionTimeouted(false);
            System.out.println("Client: Connecting to " + hostIp + ":" + port);
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(hostIp, port), 50000);
            NetworkManager.getInstance().setConnected(true);
            System.out.println("Client: Successfully connected to host!");
            this.out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) { //endlosschleife wohoo --Mathilo
                String msg = in.readLine();
                if (msg != null) {
                    if (msg.startsWith("MAP:")) {
                        setMap(Integer.parseInt(msg.substring(4))); //to load a map
                    } else {
                        NetworkManager.getInstance().queueIncomingMessage(msg);
                    }
                } else {
                    NetworkManager.getInstance().setConnected(false);
                    NetworkManager.getInstance().setDisconnected(true);
                    System.out.println("disconnected@Client");
                    break;
                }
            }
        } catch (SocketTimeoutException e) {
            System.err.println("Client: Connection timed out! (host not found/available)"); //woah system.err looks cool --Mathilo
            NetworkManager.getInstance().setConnectionTimeouted(true);
            NetworkManager.getInstance().setConnected(false);
        } catch (IOException e) {
            System.err.println("Client Connection Error: " + e.getMessage());
            NetworkManager.getInstance().setConnected(false);
            NetworkManager.getInstance().setConnectionTimeouted(true);
        }
    }

    public void setMap(int mapNr) {
        GameMap nextWorld;
        switch (mapNr) {
            case 1: {
                nextWorld = new GameMap1(true, false);
                break;
            }
            case 2: {
                nextWorld = new GameMap2(true, false);
                break;
            }
            case 3: {
                nextWorld = new GameMap3(true, false);
                break;
            }
            case 4: {
                nextWorld = new GameMap4(true, false);
                break;
            }
            case 5: {
                nextWorld = new GameMap5(true, false);
                break;
            }
            case 6: {
                nextWorld = new GameMap6(true, false);
                break;
            }
            case 7: {
                nextWorld = new GameMap7(true, false);
                break;
            }
            case 8: {
                nextWorld = new GameMap8(true, false);
                break;
            }
            default: {
                throw new RuntimeException("recieved map is invalid");
            }
        }

        SaveManager.getInstance().setLastMap(mapNr);
    }


}
