package org.src;

import java.util.HashMap;

public class Receiver implements Player {
    HashMap<Player,Integer> counter = new HashMap<>(); // this counter hashmap keep count of number of msgs sent by each different sender, as required
    @Override
    public void sendMessage(String msg, Player receiver){
        receiver.receiveMessage(msg,receiver);
    }

    @Override
    public void receiveMessage(String msg, Player sender){
        Initaitor initaitor = (Initaitor) sender;
        if (counter.containsKey(initaitor)) counter.put(initaitor,counter.get(initaitor)+1);
        else counter.put(initaitor,1);
        String msgToSend = String.valueOf(counter.get(initaitor)) + " " + msg;
        System.out.println("Receiver received the message : " + msgToSend);
        sendMessage(msgToSend,sender);
    }
}
