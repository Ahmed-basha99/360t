package org.src;

public interface Player {
    public void sendMessage(String msg, Player receiver);
    public void receiveMessage(String msg, Player sender);
}
