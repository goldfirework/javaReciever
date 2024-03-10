package fun.jons.socket;

import com.sun.javafx.reflect.FieldUtil;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class reciever {

    public void ServerSocket(int port) {
        try {
            // Oppretter tilkoblingen
            ServerSocket ss = new ServerSocket(port);

            System.out.println("Listening on port: " + port);


            while (true) {
                Socket socket = ss.accept(); // Etablerer tilkoblingen

                // Lagrer dataen som blir sendt.
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                // Gjør dataen om til bildet
                byte[] bytes = inputStream.readAllBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                BufferedImage image = ImageIO.read(bais);

                File outputfile = new File("bilde.jpg");
                ImageIO.write(image, "jpg", outputfile);

                System.out.println(port + " recieved!");

            }

            // Slutter å lytte etter data.
            //ss.close();

        } catch (Exception e ) { // Errorhåntering
            System.out.println("Error i etableringen av Socket: " + e.toString());
        }
    }
}
