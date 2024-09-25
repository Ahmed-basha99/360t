package org.src;

import java.io.IOException;

/**
 * The Player interface represents an entity that can send and receive messages.
 * It provides methods for sending & receiving messages between players(either synchronously through the first two methods,
 * and async through the third method
 * The Interface also includes two abstract methods to either run the Player object synchronously or asynchronously
 */
public interface Player {
    public void sendMessage(String msg, Player receiver);
    public void receiveMessage(String msg, Player sender);
    public void receiveMessagesByPipes();
    public void runAsync() throws IOException;
    public void runSync(Player player) ;
}
