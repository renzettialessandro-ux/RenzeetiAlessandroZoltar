/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package renzettialessandrozoltarclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author renze
 */
public class RenzettiAlessandroZoltarClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String indirizzoServer = "localhost";
        int porta = 5555;

        try (
            Socket socket = new Socket(indirizzoServer, porta);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("=== Sei di fronte al grande Zoltar ===");

            while (true) {
                System.out.print("\nFai una domanda (scrivi 'exit' per uscire): ");
                String messaggio = scanner.nextLine();

                out.writeUTF(messaggio);
                out.flush();

                if (messaggio.equalsIgnoreCase("exit")) {
                    System.out.println("Ti allontani da Zoltar...");
                    break;
                }

                String risposta = in.readUTF();
                System.out.println("Zoltar dice: " + risposta);
            }
        } catch (IOException e) {
            System.out.println("Impossibile connettersi al server Zoltar.");
        }
    }
    
}
