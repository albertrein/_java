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
                                execComandos(linha,currentThread());
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

    public void execComandos(String linha, Thread thread){
        StringTokenizer token = new StringTokenizer(linha," ");
        String comando = token.nextToken();

        if(comando.equals("ENTRAR")){
            String nick = linha.replaceFirst(comando+" ","");
            //Verificacoes
            thread.setName(nick);
            System.out.println("Bem-vindo "+nick);


        }else if(comando.equals("MSG")){
            sendsMessageToAll(linha.replaceFirst(comando+" ",""),thread.getName());

        }else if(comando.equals("SAIR")){
            System.out.println("Bye! Bye! :)");

        }else{
            System.out.println("Err. Comando não compreendido");
        }



    }

    public void sendsMessageToAll(String msg, String nameThread){
        for(String i : clientes.keySet()){
            try{
                Socket socket = clientes.get(i);
                PrintWriter enviaMsg = new PrintWriter(socket.getOutputStream());
                enviaMsg.println("MSG "+nameThread+" "+msg);
                enviaMsg.flush();
            }catch(IOException e){
                System.out.println("Err. Send message.");
            }

        }
    }
}
