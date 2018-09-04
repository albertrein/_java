package SocketAndThread;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server extends Thread{
    @Override
    public void run(){
        try(Socket socket = new ServerSocket(7777).accept()){
            Scanner entrada = new Scanner(socket.getInputStream());
            PrintWriter saida = new PrintWriter(socket.getOutputStream());

            String rope;
            while((rope = entrada.nextLine()) != null){
                try{
                    Thread.sleep(3000);
                    saida.println("Recebido");
                    saida.flush();
                }catch(InterruptedException inex){
                    inex.printStackTrace();
                }
                System.out.println(rope);
            }
            socket.close();
            entrada.close();
            saida.close();
        }catch (UnknownHostException ukex){
            ukex.printStackTrace();
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
    }
}
