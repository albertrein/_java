package teste1_manyClients;

import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente extends Thread {
    //Atributos
    private String msg;

    public static void main(String[] args) {
        System.out.println("Iniciando cliente manual MAIN");

        try( Socket socket = new Socket("0", 9080) ){

            Scanner entrada = new Scanner(socket.getInputStream());
            PrintWriter saida = new PrintWriter(socket.getOutputStream());

            saida.println("Cliente Main");
            saida.flush();

            String line;
            while((line = entrada.nextLine()) != null){
                System.out.println(line);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        clienteCriador();
    }

    public void clienteCriador(){
        System.out.println("Iniciando cliente "+this.msg);

        try( Socket socket = new Socket("0", 9080) ){

            Scanner entrada = new Scanner(socket.getInputStream());
            PrintWriter saida = new PrintWriter(socket.getOutputStream());

            saida.println(msg);
            saida.flush();

            String line;
            while((line = entrada.nextLine()) != null){
                System.out.println(line);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void start(String string){
        this.msg = string;
        super.start();

    }
}
