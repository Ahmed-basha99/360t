package org.src;

/**
 * The PlayerFactory class is responsible for creating instances of different types of players
 * (e.g., Initiator, Receiver) based on the specified PlayerType. It provides a method to create
 * new Receiver player instances either asynchronously or synchronously.
 */
public class PlayerFactory {
    public Player CreateNewPlayer(PlayerType type, boolean isAsync){
        if (type==PlayerType.Receiver) return new Receiver(isAsync);
        else if (type==PlayerType.Initiator) return new Initiator();
        else throw new IllegalArgumentException("Unknown notification type " + type);
    }
}
