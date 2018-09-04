package SocketAndThread;
import java.net.*;
import java.io.*;
import java.util.*;

public class Client extends Thread{
    @Override
    public void run(){
        try(Socket socket = new Socket("0",7777)){
            Scanner entrada = new Scanner(socket.getInputStream());
            PrintWriter saida = new PrintWriter(socket.getOutputStream());

            saida.println("msg: Hi!"); //Coloca no buffer a mensagem a ser enviada
            saida.flush(); //Envia a mensagem do buffer para o host do socket

            String linha;
            while((linha = entrada.nextLine()) !=null){
                saida.println("msg: Hi, again!");
                saida.flush();
            }
            socket.close();
            entrada.close();
            saida.close();
        }catch(UnknownHostException ukex){
            ukex.printStackTrace();
        }catch(IOException ioex){
            ioex.printStackTrace();
        }

    }
}
