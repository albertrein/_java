package versao7.cliente;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
    public static void main(String[] args) {
        String[] findEmoji = {":)", ":(", ";)", ":p"};
        String[] emoji = {"\ud83d\ude02", "\ud83d\ude21", "\ud83d\ude09", "\ud83d\ude0b"};
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
                        System.out.println("Saindo do CHAT.");
                        entradaSocket.close();
                        saidaSocket.close();
                        System.exit(0);
                    }
                }
            };

            t.start();
            while (true) {
                String chatMessage = entrada.nextLine();
                if(!chatMessage.equals("SAIR")){
                    chatMessage = "MSG "+chatMessage;
                    for(int l = 0; l < findEmoji.length; l++){
                        chatMessage = chatMessage.replace(findEmoji[l],emoji[l]);
                    }
                }
                saidaSocket.println(chatMessage);
                saidaSocket.flush();
            }


        } catch (IOException e) {
            System.out.println("Err. Ao abrir socket " + e.getMessage());
        }
    }

}
