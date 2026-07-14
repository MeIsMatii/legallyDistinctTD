package util.multiplayer;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NetworkManager{
    //bc singleton
    private static NetworkManager instance;

    //the active runner (host/client) to recieve & send data
    private Thread networkThread;
    private MultiplayerConnection activeWorker;

    //incoming messages
    private final ConcurrentLinkedQueue<String> incomingMessages = new ConcurrentLinkedQueue<>();

    private boolean isHost = true; //singleplayer

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
        if(isActive()) {
            return;
        } //its already running
        isHost = true;
        NetworkHost host = new NetworkHost(port);
        this.activeWorker = host;

        networkThread = new Thread(host);
        networkThread.setDaemon(true);
        networkThread.start();

    }

    public void startClient(String ip, int port) {
        if(isActive()) {
            return;
        } //its already running
        isHost = false;

        NetworkClient client = new NetworkClient(ip, port);
        this.activeWorker = client;

        networkThread = new Thread(client);
        networkThread.setDaemon(true);
        networkThread.start();

    }

    public void sendData(String msg) {
       if(activeWorker != null) {
           activeWorker.send(msg);
       }
    }


    public void queueIncomingMessage(String msg) {
        incomingMessages.add(msg);
    }

    public ConcurrentLinkedQueue<String> getInboundQueue() {
        return incomingMessages;
    }
}
