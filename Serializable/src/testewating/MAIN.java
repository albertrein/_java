package testewating;

import java.util.Scanner;

public class MAIN {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Thread t = new Thread(){
            public void run(){
                System.out.println("Thread ...");
                try{
                    currentThread().sleep(2000);
                    System.out.println("END");
                    throw new InternalError();
                }catch (InterruptedException e){
                    System.out.println("catch");
                    e.printStackTrace();
                }
            }
        };
        try{
            t.start();
            String gui = in.nextLine();
            System.out.println(gui);
        }catch (InternalError e){
            System.out.println("END END");
            return;
        }


    }
}
