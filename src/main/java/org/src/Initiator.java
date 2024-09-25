package org.src;
import java.io.*;

/**
 * The Initiator class implements the Player interface and represents an initiator
 * that can send and receive messages either through function calls ( in the case of single proces )
 * or through pipes ( in the case of multi process ).
 */
public class Initiator implements Player {
    @Override
    public void sendMessage(String msg, Player receiver) {
        receiver.receiveMessage(msg,this);
    }
    @Override
    public void receiveMessage(String msg, Player sender){
        System.out.println("Message Received back By sender("+ msg+")");
    }

    public void sendMessagesSync(Player receiver){
        for (int i=1;i<=10;i++){
            String message = "Hello World ";
            System.out.println("message : ("+message + ") Sent to receiver, number " + i );
            sendMessage(message,receiver);
        }
    }
    public void sendMessagesByPipes() {
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Sender2Receiver"));
            for (int i = 1; i <= 10; i++) {
                String message = "Hello World ";
                System.out.println("message : ("+message + ") Sent to receiver, number " + i );
                Thread.sleep(20);
                writer.write(message+" from :"+this.hashCode());
                writer.newLine();
                writer.flush();
            }
            writer.close();
        }
        catch (IOException | InterruptedException e ) {
            e.printStackTrace();
        }
    }
    @Override
    public void receiveMessagesByPipes(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Receiver2Sender"));
            String line;
            while ((line=reader.readLine())!=null){
                System.out.println("Message Received back By sender("+ line+")");
            }
            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void runAsync()  {
        Thread asyncThread = new Thread(this::sendMessagesByPipes);
        Thread asyncThread2 = new Thread(this::receiveMessagesByPipes);
        asyncThread2.start();
        asyncThread.start();
        try {
            asyncThread2.join();
            asyncThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void runSync(Player receiver){
        sendMessagesSync(receiver);
    }
}
