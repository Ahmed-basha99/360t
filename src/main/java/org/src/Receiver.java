package org.src;

import java.io.*;
import java.util.HashMap;

/**
 * The Receiver class implements the Player interface and represents a receiver
 * that can process messages either synchronously or asynchronously.
 * It maintains a count of messages sent by each sender using a counter
 * HashMap and can interact with other players via message passing (in the case of single process)
 * or pipes in the case of multi process.
 */
public class Receiver implements Player {
    HashMap<Integer,Integer> counter = new HashMap<>(); // this counter hashmap keep count of number of msgs sent by each different sender, as required
    BufferedWriter writer;
    BufferedReader reader;

    public Receiver(boolean async){
        if (!async) return;
        try {
            writer = new BufferedWriter(new FileWriter("Receiver2Sender"));
            reader = new BufferedReader(new FileReader("Sender2Receiver"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void sendMessage(String msg, Player receiver){
        receiver.receiveMessage(msg,receiver);
    }

    @Override
    public void receiveMessage(String msg, Player sender){
        int senderHashCode = sender.hashCode();
        if (counter.containsKey(senderHashCode)) counter.put(senderHashCode,counter.get(senderHashCode)+1);
        else counter.put(senderHashCode,1);
        String msgToSend = msg + (counter.get(senderHashCode));
        System.out.println("message received by receiver & sent back to sender (" + msgToSend+")");
        sendMessage(msgToSend,sender);
    }

    private void sendBackMessageByPipes(String msg){
        try {
            writer.write(msg);
            writer.flush();
            writer.newLine();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void receiveMessagesByPipes() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                Integer senderHashCode = extractSenderHashCode(line);

                if (counter.containsKey(senderHashCode)) counter.put(senderHashCode,counter.get(senderHashCode)+1);
                else counter.put(senderHashCode,1);

                String messageBack = extractMessageBody(line)+counter.get(senderHashCode);
                System.out.println("message received by receiver & sent back to sender (" + messageBack+")");
                Thread.sleep(20);

                sendBackMessageByPipes(messageBack);
            }
        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void runAsync() {
       receiveMessagesByPipes();
    }
    @Override
    public void runSync(Player receiver){}

    private String extractMessageBody(String x){
        String newVal = "";
        for (int i=0;i<x.length();i++){
            if (x.startsWith("from", i)) break;
            newVal += x.charAt(i);
        }
        return newVal;
    }
    private Integer extractSenderHashCode(String x){
        int c = 0;
        for (int i=0;i<x.length();i++) {
            c++;
            if (x.charAt(i)==':') break;
        }
        String substring = x.substring(c).trim();
        return Integer.parseInt(substring);
    }
}
