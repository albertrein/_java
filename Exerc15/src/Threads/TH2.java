package Threads;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TH2 extends Thread{
    private Socket socket;

    public TH2(Socket e){
        this.socket = e;
    }

    @Override
    public void run(){
        try{
            PrintWriter out = new PrintWriter(this.socket.getOutputStream());
            Scanner in = new Scanner(this.socket.getInputStream());
            String entrada;


            while((entrada = in.nextLine())!=null){
                System.out.println("Resposta:"+entrada);
            }
        }catch(IOException e){
            System.out.println("TH2");
            e.printStackTrace();
        }catch (NoSuchElementException e){
            System.out.println(currentThread()+" caiuu fora");
        }
    }

}
