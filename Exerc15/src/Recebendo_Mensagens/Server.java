package Recebendo_Mensagens;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server extends Thread{
    //attrbs

    //methds
    @Override
    public void run(){
        System.out.println("Server .....");
        try{
            ServerSocket n = new ServerSocket(1111);

            while(true){
                Socket s = n.accept();
                new Thread(){
                    public void run(){
                        System.out.println("Nova thread");
                        try(Scanner in = new Scanner(s.getInputStream())){
                            String line;
                            while((line = in.nextLine())!=null){
                                comando(line);
                            }
                        }catch (IOException e){}
                    }
                }.start();



            }//Fim while de clients

        }catch(IOException e){
            System.out.println("Err. Server create");
        }

    }


    public void comando(String i){
        System.out.println("Message Receive:"+i);
        if(i.equals("oi"))
            System.out.println("Olááá");
    }
}
