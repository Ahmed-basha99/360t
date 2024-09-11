package org.src;

public class PlayerFactory {
    public Player CreateNewPlayer(PlayerType type){
        if (type==PlayerType.Receiver) return new Receiver();
        else if (type==PlayerType.Initiator) return new Initaitor();
        else throw new IllegalArgumentException("Unknown notification type " + type);
    }
}
