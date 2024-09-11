package org.src;

public class Initaitor implements Player {
    @Override
    public void sendMessage(String msg, Player receiver) {
        receiver.receiveMessage(msg,this);
    }
    @Override
    public void receiveMessage(String msg, Player sender){
        System.out.println("Message received back by the sender : " + msg);
    }
}
