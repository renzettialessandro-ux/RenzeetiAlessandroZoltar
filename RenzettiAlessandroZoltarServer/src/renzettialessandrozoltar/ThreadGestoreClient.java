package renzettialessandrozoltar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadGestoreClient implements Runnable {

    private Socket clientSocket;
    private GestoreDomande gestoreDomande;

    public ThreadGestoreClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.gestoreDomande = new GestoreDomande();
    }

    @Override
    public void run() {
        try (
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            while (true) {
                // Legge la domanda inviata dal client
                String domanda = in.readUTF();

                if (domanda.equalsIgnoreCase("exit")) {
                    System.out.println("Un client ha chiuso la sessione.");
                    break;
                }

                System.out.println("Domanda ricevuta da " + clientSocket.getInetAddress() + ": " + domanda);

                // Ottiene la risposta (puoi personalizzare GestoreDomande)
                String risposta = gestoreDomande.getRisposta(domanda);

                // Invia la risposta al client
                out.writeUTF(risposta);
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("Connessione interrotta con il client.");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Errore durante la chiusura del socket.");
            }
        }
    }
}