package sendAndReceiveMessages;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server extends Thread{
    private HashMap<String, Socket> clientes = new HashMap<>();

    @Override
    public void run(){
        System.out.println("Iniciando Server: Send and Receiver Server.......");
        try{
            ServerSocket ss = new ServerSocket(3333);

            while(true){
                Socket s = ss.accept();

                new Thread(){
                    @Override
                    public void run(){
                        clientes.put(currentThread().toString(),s);
                        try {
                            Scanner in = new Scanner(s.getInputStream());
                            String linha;
                            while((linha = in.nextLine())!=null){
                                execComandos(linha);
                            }
                        }catch(IOException ee){
                            System.out.println("Err. Thread Menssageiro."+ee.getMessage());
                        }
                    }
                }.start();


            }

        }catch(IOException e){
            System.out.println("Err. Server Create."+e.getMessage());
        }
    }

    public void execComandos(String linha){
        // DO THingssss
        sendsMessageToAll(linha);
    }

    public void sendsMessageToAll(String msg){
        for(String i : clientes.keySet()){
            try{
                Socket socket = clientes.get(i);
                PrintWriter enviaMsg = new PrintWriter(socket.getOutputStream());
                enviaMsg.println(msg);
                enviaMsg.flush();
            }catch(IOException e){
                System.out.println("Err. Send message.");
            }

        }
    }
}
