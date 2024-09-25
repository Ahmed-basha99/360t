package org.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * The Main class provides the entry point to the application,
 * allowing users to choose between running the program in a single process or
 * in two separate processes with different PIDs. Depending on the user's choice,
 * it will either run synchronously in a single process or start two separate processes
 * for the Initiator and Receiver players.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the number to choose one of the following options :\n1-run the program in a single process\n2-run the program in two processes(different PIDs)\n3-close");
        int choice = Integer.parseInt(System.console().readLine());
        if (choice==1) {
            PlayerFactory playerFactory = new PlayerFactory();
            Player initiator = playerFactory.CreateNewPlayer(PlayerType.Initiator,false);
            Player receiver = playerFactory.CreateNewPlayer(PlayerType.Receiver,false);
            initiator.runSync(receiver);
        }

        else if (choice==2) {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "org.src.InitiatorRunner");
            ProcessBuilder processBuilder2 = new ProcessBuilder("java", "org.src.ReceiverRunner");
            try {
                Process process = processBuilder.start();
                Process process2 = processBuilder2.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
                Thread printOutputFromProcess1 = new Thread(()->{
                   String line;
                   try {
                       while ((line = reader.readLine()) != null) System.out.println(line);
                   } catch(IOException e){e.printStackTrace();}
                });
                Thread printOutputFromProcess2= new Thread(()->{
                    String line;
                    try {
                        while ((line = reader2.readLine()) != null) System.out.println(line);
                    } catch(IOException e){e.printStackTrace();}
                });
                printOutputFromProcess1.start();
                printOutputFromProcess2.start();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
