/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renzettialessandrozoltar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author renzetti.alessandro
 */
public class Zoltar {
    public void leggiRispondi() throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String clientMessage = dataInputStream.readUTF();
            System.out.println("Messaggio dal client: " + clientMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            String response = "Ciao, client!";
            dataOutputStream.writeUTF(response);
            clientSocket.close();
        }
    }

}
