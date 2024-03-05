package fun.jons;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {

        // Setter igang lyttingen
        new fun.jons.socket.reciever().ServerSocket(Integer.parseInt(args[0]));
    }
}