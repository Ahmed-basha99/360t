package org.src;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        Player initiator = playerFactory.CreateNewPlayer(PlayerType.Initiator);
        Player initiator2 = playerFactory.CreateNewPlayer(PlayerType.Initiator);
        Player receiver = playerFactory.CreateNewPlayer(PlayerType.Receiver);
        ProcessBuilder builder = new ProcessBuilder("java", "Initiator");
        ProcessBuilder builder2 = new ProcessBuilder("java", "Receiver");
        try {
            Process p = builder.start();

        } catch (IOException e) {
            System.out.println("could not run process");
        }
        for (int i=0;i<10;i++){
//            initiator.sendMessage("Hi " + String.valueOf(i+1),receiver);
//            initiator2.sendMessage("heyyy " + String.valueOf(i+11),receiver);
        }
    }
}