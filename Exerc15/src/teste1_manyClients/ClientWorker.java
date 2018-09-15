package teste1_manyClients;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWorker extends Thread {
    private Socket socket;

    public ClientWorker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            Scanner entrada = new Scanner(this.socket.getInputStream());
            PrintWriter saida = new PrintWriter(this.socket.getOutputStream());

            String line;
            while ((line = entrada.nextLine()) != null) {
                System.out.println(Thread.currentThread()+" == "+line);
                saida.println("Recebi! "+line);
                saida.flush();
            }

        }catch(IOException ioex){
            System.out.println("Estou saindo");
            ioex.printStackTrace();
        }
    }

}
