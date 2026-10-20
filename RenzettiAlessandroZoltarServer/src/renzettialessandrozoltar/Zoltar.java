package renzettialessandrozoltar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Zoltar {

    public static void main(String[] args) {
        int porta = 5555;
        Zoltar zServer= new Zoltar();
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Zoltar è pronto sulla porta " + porta);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuovo utente connesso: " + clientSocket.getInetAddress());
                ThreadGestoreServer gestore = new ThreadGestoreServer(clientSocket, zServer);
                Thread t = new Thread(gestore);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Errore server: " + e.getMessage());
        }
    }
}