package util.multiplayer;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NetworkManager {
    //bc singleton
    private static NetworkManager instance;

    //the active runner (host/client) to recieve & send data
    private Thread networkThread;
    private MultiplayerConnection activeWorker;

    //incoming messages
    private final ConcurrentLinkedQueue<String> incomingMessages = new ConcurrentLinkedQueue<>();

    private boolean isHost = true; //singleplayer
    private boolean isMultiplayer = false; //singleplayer

    private int mapNr = 0;

    private boolean isConnectionTimeout = false;
    private boolean isConnected = false;

    private NetworkManager() {

    }

    public static synchronized NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }


    // stuff happens here

    public boolean isActive() {
        return (networkThread != null && networkThread.isAlive());
    }

    public boolean isHost() {
        return isHost;
    }

    public void startHost(int port) {
        if (isActive()) {
            return;
        } //its already running
        isHost = true;
        isMultiplayer = true;
        NetworkHost host = new NetworkHost(port);
        this.activeWorker = host;

        networkThread = new Thread(host);
        networkThread.setDaemon(true);
        networkThread.start();

        System.out.println("Started host instance on port " + port + ".");
    }

    public void startClient(String ip, int port) {
        if (isActive()) {
            return;
        } //its already running
        isHost = false;
        isMultiplayer = true;

        NetworkClient client = new NetworkClient(ip, port);
        this.activeWorker = client;

        networkThread = new Thread(client);
        networkThread.setDaemon(true);
        networkThread.start();

        System.out.println("Trying to connect to " + ip + " on port " + port + ".");

    }

    public void sendData(String msg) {
        if (activeWorker != null) {
            System.out.println("Sending: " + msg);
            activeWorker.send(msg);
        }
    }


    public void queueIncomingMessage(String msg) {
        System.out.println("Recieved: " + msg);
        incomingMessages.add(msg);
    }

    public ConcurrentLinkedQueue<String> getInboundQueue() {
        return incomingMessages;
    }

    public void setMapNr(int nr) {
        this.mapNr = nr;
    }

    public int getMapNr() {
        return this.mapNr;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    public boolean isConnectionTimedOut() {
        return isConnectionTimeout;
    }

    public void setConnectionTimeouted(boolean connectionTimeouted) {
        isConnectionTimeout = connectionTimeouted;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
