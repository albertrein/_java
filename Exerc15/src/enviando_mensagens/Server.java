package enviando_mensagens;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server extends Thread{
    private HashMap<String, Socket> clients = new HashMap<>();

    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(1111);
            while(true){
                Socket sock = server.accept();
                new Thread(){
                    @Override
                    public void run(){
                        adicionaNovoSocket(currentThread().toString(),sock);
                    }
                }.start();
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void enviaMensagens(){
        for(String i : clients.keySet() ){
            try {
                Socket s = clients.get(i);
                PrintWriter outAll = new PrintWriter(s.getOutputStream());
                outAll.println("Olá grupo");
                outAll.flush();
            }catch(IOException e){
                System.out.println("Err. Enviar mensagens");
            }
        }
    }

    public void adicionaNovoSocket(String nome, Socket sock){
        clients.put(nome, sock);
        if(clients.size() > 2){
            enviaMensagens();
        }
    }


}
