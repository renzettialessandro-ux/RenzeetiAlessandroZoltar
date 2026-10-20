package renzettialessandrozoltar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadGestoreServer implements Runnable {
    private Socket clientSocket;
    private GestoreDomande gestoreDomande;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean attivo = true;

    public ThreadGestoreServer(Socket clientSocket, Zoltar server) {
        this.clientSocket = clientSocket;
        this.gestoreDomande = new GestoreDomande();
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            while (attivo) {
                String domanda = in.readUTF();
                if (domanda.equalsIgnoreCase("exit")) {
                    System.out.println("Un client ha chiuso la sessione.");
                    break;
                }
                System.out.println("Domanda ricevuta da " + clientSocket.getInetAddress() + ": " + domanda);
                String risposta = gestoreDomande.getRisposta(domanda);

                // Invia la risposta
                inviaMessaggio(risposta);
            }
        } catch (IOException e) {
            System.out.println("Connessione interrotta con il client.");
        } finally {
            chiudiConnessione();
        }
    }
    public synchronized void inviaMessaggio(String messaggio) throws IOException {
        if (!attivo) return;
        out.writeUTF(messaggio);
        out.flush();
    }
    public synchronized void chiudiConnessione() {
        if (!attivo) return;
        attivo = false;
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
        } catch (IOException ignored) {}
    }
    
}