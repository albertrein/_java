import jdk.internal.org.objectweb.asm.Handle;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Digite uma mensagem para registrar");
        Scanner in = new Scanner(System.in);
        String messagem = in.nextLine();

        Thread e = new Thread(){
            @Override
            public void run(){
                try{
                    FileWriter escreve = new FileWriter("messagesServerList.txt",true);
                    escreve.write(messagem+"\n");
                    escreve.flush();
                    yield();
                    return;
                }catch(IOException e){
                    System.out.println("Err.");
                    return;
                }
            }
        };

        e.start();

        try {
            Thread.currentThread().join();
            System.out.println("fim");
        }catch (InterruptedException ee){

        }

    }
}
