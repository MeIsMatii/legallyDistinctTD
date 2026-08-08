package util.multiplayer;
/**
 * @author Mathilo
 */
public interface MultiplayerConnection extends Runnable{
    void send(String msg);
}
