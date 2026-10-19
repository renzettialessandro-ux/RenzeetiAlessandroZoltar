package renzettialessandrozoltar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Zoltar {

    public static void main(String[] args) {
        int porta = 5555;

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Zoltar è pronto sulla porta " + porta + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuovo utente connesso: " + clientSocket.getInetAddress());

                // Istanzia il thread per gestire la connessione del client
                ThreadGestoreClient gestore = new ThreadGestoreClient(clientSocket);
                Thread t = new Thread(gestore);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Errore server: " + e.getMessage());
        }
    }
}