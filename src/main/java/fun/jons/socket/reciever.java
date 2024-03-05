package fun.jons.socket;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class reciever {

    public void ServerSocket(int port) {
        try {
            // Oppretter tilkoblingen
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                Socket socket = ss.accept(); // Etablerer tilkoblingen

                // Lagrer dataen som blir sendt.
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                // Gjør meldingen om til tekst.
                String message = (String) inputStream.readUTF();

                System.out.println(message);
            }

            //ss.close();

        } catch (Exception e ) { // Errorhåntering
            System.out.println("Error i etableringen av Socket: " + e.toString());
        }
    }
}
