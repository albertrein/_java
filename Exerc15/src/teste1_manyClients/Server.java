package teste1_manyClients;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    public static void main(String[] args) {
    ArrayList<ClientWorker> cl = new ArrayList<>();

        try(ServerSocket server = new ServerSocket(9080) ){
            int i = 0;
            while(true) {
                Socket socket = server.accept();
                cl.add(new ClientWorker(socket));
                cl.get(i).start();

                PrintStream outToClient = null;
                outToClient = new PrintStream(socket.getOutputStream());
                outToClient.println("teste tcpsend");

                i++;

            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
