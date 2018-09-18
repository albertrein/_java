package Threads.interruptTest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Main ...");
        Scanner in = new Scanner(System.in);
        Thread t = new Thread(){ //Thread entrada
            @Override
            public void run(){
                try{
                    while(true){
                        System.out.println("Digite um valor: ");
                        String msg = in.nextLine();
                        join();
                    }
                }catch (InterruptedException e){
                    System.out.println("OUT");
                    return;
                }
            }
        };

        t.start();

        new Thread(){ //Thread entrada
            @Override
            public void run(){
                try{
                    currentThread().sleep(7000);
                    t.join();
                    System.out.println("out-2");
                    return;
                }catch (InterruptedException e){

                }
            }
        }.start();


    }
}
