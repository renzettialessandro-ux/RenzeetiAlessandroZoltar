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
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author renzetti.alessandro
 */
public class Utente {

    public void invia()throws IOException{
        String msg;
        Scanner s = new Scanner(System.in);
        Socket client = new Socket("localhost", 5555);
        OutputStream outputStream = client.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        InputStream inputStream = client.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        while (true) {
            System.out.print("Cosa vuoi scrivere al server?: ");
            msg = s.nextLine();
            dataOutputStream.writeUTF(msg);
            String response = dataInputStream.readUTF();
            System.out.println(response);
        }
    } 
    
}
