package TestConfiguration;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        out.println("Salut Serveur !");
        System.out.println("Réponse du serveur : " + in.readLine());
        
        socket.close();
    }
}