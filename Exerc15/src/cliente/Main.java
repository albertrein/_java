package cliente;

import java.sql.SQLOutput;
import java.util.*;
import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Cliente ...");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o IP do servidor: ");
        String ip = entrada.nextLine();
        System.out.println("Digite a PORTA do servidor: ");
        int porta = entrada.nextInt();
        entrada.nextLine();

        try {
            Socket socket = new Socket(ip, porta);

            System.out.println("Digite seu nickname:");
            String nick = entrada.nextLine();

            Scanner entradaSocket = new Scanner(socket.getInputStream());
            PrintWriter saidaSocket = new PrintWriter(socket.getOutputStream());

            saidaSocket.println("ENTRAR " + nick);
            saidaSocket.flush();

            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        String linha;
                        while ((linha = entradaSocket.nextLine()) != null) {
                            System.out.println(linha);
                        }

                    } catch (NoSuchElementException e) {
                        System.out.println("Saindo do grupo. " + e.getMessage());
                        entradaSocket.close();
                        saidaSocket.close();
                        entrada.close();
                        return;
                    }
                }
            };
            t.start();

            while (true) {
                if (!t.isAlive()) {
                    System.out.println("saindo ...");
                    return;
                }
                String chatMessage = entrada.nextLine();
                saidaSocket.println(chatMessage);
                saidaSocket.flush();
            }
        } catch (IOException e) {
            System.out.println("Err. Ao abrir socket " + e.getMessage());
        }
    }
}
