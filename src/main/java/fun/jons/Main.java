package fun.jons;

import java.io.*;
import java.net.*;

public class Main {

    // Må ha en args på hvilken port den skal bruke.
    public static void main(String[] args) {

        // Setter igang lyttingen
        new fun.jons.socket.reciever().ServerSocket(Integer.parseInt(args[0]));
        //new fun.jons.socket.reciever().ServerSocket(Integer.parseInt(args[1]));
    }
}