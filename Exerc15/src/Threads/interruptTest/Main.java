package Threads.interruptTest;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Main ...");
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("Iniciando Thread ...");
//                try{
//                    yield();
//                }
//               return;


            }
        };

        t.start();
        try {
            Thread.currentThread().sleep(4000);
            t.interrupt();
            System.out.println(t.getName());
        } catch (InterruptedException e) {
        }


    }
}
