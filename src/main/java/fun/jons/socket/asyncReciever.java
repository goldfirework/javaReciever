package fun.jons.socket;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class asyncReciever {

    private AsynchronousServerSocketChannel serverChannel;
    private Future<AsynchronousSocketChannel> acceptResults;
    private AsynchronousSocketChannel clientChannel;



    public void asyncReciever(int port) {
        try {
            serverChannel = AsynchronousServerSocketChannel.open();

            serverChannel.bind(new InetSocketAddress("127.0.0.1", port));

            acceptResults = serverChannel.accept();


        } catch (Exception e){
            System.out.println("asyncReciever fikk feil: \n" + e.toString());
        }
    }

    public void runserver(int port) {
        try {
            clientChannel = acceptResults.get();

            if ((clientChannel != null) && (clientChannel.isOpen())) {
                while (true) {
                    ByteBuffer buffer = ByteBuffer.allocate(32);
                    Future<Integer> readResult = clientChannel.read(buffer);

                    // Her kan det gjøres kalkulasjoner

                    readResult.get();

                    buffer.flip();
                    String message = new String(buffer.array()).trim();
                    if (message.equals("bye")) {
                        break;
                    }
                    buffer = ByteBuffer.wrap(new String(message).getBytes());
                    Future<Integer> writeResult = clientChannel.write(buffer);

                    // Gjør kalkulasjoner

                    writeResult.get();
                    buffer.clear();

                }

                clientChannel.close();
                serverChannel.close();


            }

        }catch (Exception e) {
            System.out.println("runServer fikk feil \n" + e.toString());
        }
    }

    public static void asyncRecieverMain(String args) {
        asyncReciever server = new asyncReciever();
        server.runserver(Integer.parseInt(args));
    }

}
