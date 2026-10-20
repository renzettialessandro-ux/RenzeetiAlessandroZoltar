package renzettialessandrozoltar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RenzettiAlessandroZoltar {

    private int PORTA = 5555;
    private List<ThreadGestoreServer> clientConnessi = Collections.synchronizedList(new ArrayList<>());
    private Zoltar zServer= new Zoltar();
    public void avvia() {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Server avviato sulla porta " + PORTA + ". In attesa di client...");
            new Thread(this::gestisciConsole).start();
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuova connessione accettata da: " + socket.getRemoteSocketAddress());
                ThreadGestoreServer gestore = new ThreadGestoreServer(socket, zServer);
                aggiungiClient(gestore);
                new Thread(gestore).start();
            }

        } catch (IOException e) {
            System.err.println("Errore del ServerSocket: " + e.getMessage());
        }
    }

    private void gestisciConsole() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String testo = scanner.nextLine();
            broadcast("Server: " + testo, null);
        }
        scanner.close();
    }

    public void aggiungiClient(ThreadGestoreServer client) {
        clientConnessi.add(client);
    }

    public void rimuoviClient(ThreadGestoreServer client) {
        clientConnessi.remove(client);
    }
    public void broadcast(String messaggio, ThreadGestoreServer mittente) {
        synchronized (clientConnessi) {
            for (ThreadGestoreServer client : clientConnessi) {
                // Gestione dell'eccezione lanciata da inviaMessaggio()
                try {
                    client.inviaMessaggio(messaggio);
                } catch (IOException e) {
                    System.err.println("Impossibile inviare messaggio a un client.");
                }
            }
        }
    }
}