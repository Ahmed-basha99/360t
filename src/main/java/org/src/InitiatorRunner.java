package org.src;

import java.io.IOException;

/**
  This class is called in the main class by the process builder to spawn a new process
  After this class starts running, it creates a new Initiator Player, and run it asynchronously
 */
public class InitiatorRunner {
    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        Player player = playerFactory.CreateNewPlayer(PlayerType.Initiator,true);
        try {
            player.runAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
