package servidorComArquivos;

import java.util.*;
import java.net.*;
import java.io.*;

public class Server extends Thread{
    private HashMap<String, Socket> clientes = new HashMap<>();

    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(5555);

            while(true){
                Socket socket = new Socket();

                new Thread(){
                    @Override
                    public void run(){
                        try{
                            Scanner entrada = new Scanner(socket.getInputStream());
                            boolean inicio = true;
                            String chatEntrada;
                            while((chatEntrada = entrada.nextLine()) != null){
                                StringTokenizer token = new StringTokenizer(chatEntrada," ");
                                String comando = token.nextToken();

                                if(inicio && comando.equals("ENTRAR")){
                                    //NOVO cliente
                                    inicio = false; //Não é mais inicio
                                }else if(comando.equals("MSG")){

                                }else if(comando.equals("SAIR")){

                                }else{
                                    System.out.println("Comando não reconhecido:"+chatEntrada);
                                }


                            }

                        }catch (IOException ioexx){
                            System.out.println("Err. Mensageiro");
                        }
                    }
                }.start();
            }

        }catch (IOException ioex){
            System.out.println("Err. Server");
        }
    }
}
