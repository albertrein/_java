package Passwords;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SafePass safe = new SafePass();

        while(true) {
            System.out.println("'inse' ou 'Show' ou 'OUT' uma Senha?");
            String opcao = in.nextLine();

            if (opcao.equals("Show")) {
                safe.show();
            }else if (opcao.equals("inse")) {
                System.out.println("Digite a Senha:");
                String pass = in.nextLine();
                try {
                    safe.addPass(pass);
                } catch (InternalError e) {
                    System.out.println(e.getMessage() + " encerrando ...");
                    return;
                }
            }else if(opcao.equals("OUT")){
                break;
            }inse
        }

        new Thread(){
            @Override
            public void run(){
                System.out.println("Salvando ...");
                try{
                    FileOutputStream file = new FileOutputStream("Passes.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(safe);
                    out.flush();
                    out.close();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException ee){
                    ee.printStackTrace();
                }
            }
        }.start();

    }
}
