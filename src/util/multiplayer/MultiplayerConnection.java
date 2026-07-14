package util.multiplayer;

public interface MultiplayerConnection extends Runnable{
    void send(String msg);
}
