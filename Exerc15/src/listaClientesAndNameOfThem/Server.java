package listaClientesAndNameOfThem;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server extends Thread {
    //ArrayList<Socket> clients = new ArrayList<>();
    private HashMap<String, Socket> clients = new HashMap<>();

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(4444);

            while (true) {
                System.out.println("Iniciando um novo cliente ....");
                Socket socket = server.accept();

               new Thread() { // Capturar as entradas de cada uma das Threads
                    @Override
                    public void run(){
                        try{
                            int contador = 0;
                            Scanner entradas = new Scanner(socket.getInputStream());
                            String getLinha;

                            while((getLinha = entradas.nextLine()) != null){
                                if(contador == 0) {
                                    if(novoCliente(socket, getLinha, currentThread()) == 0){
                                        entradas.close();
                                        socket.close();
                                        return;
                                    }

                                    contador++;
                                }
                                meuShell(currentThread(),getLinha);
//                                try{
//                                    join();
//                                }catch (InterruptedException e){
//                                    System.out.println("CLiente fora ...");
//                                }

                            }

                        }catch (IOException ee){
                            System.out.println("Err. Na entrada de dados.");
                        }
                    }
                }.start();

            }

        } catch (IOException e) {

        }
    }

    public String getComando(String linha){
        StringTokenizer token = new StringTokenizer(linha," ");
        return token.nextToken(); // Retorna uma String até antes do primeiro " " (ESPAÇO VAZIO)
    }

    public int novoCliente(Socket sock, String linha, Thread defineThread){
        if(getComando(linha).equals("ENTRAR")){
            if(clients.size() > 2)
                return 0;

            String nick = linha.replace(getComando(linha)+" ","");

            for(String verifica : clients.keySet()){
                if(verifica.equals(nick))
                    return 0;
            }
            defineThread.setName(nick);//DEfine o nick da Thread
            clients.put(nick,sock); //Adicona um cliente
            enviaNotificacao("Entrar",defineThread.getName()); // Notifica asd todos os clientes
            //Imprime as ultimas mensagens
            return 1;
        }
        return 0;
    }

    public int enviaMensagens(String threadName, String linha){
        String msg = linha.replaceFirst(getComando(linha)+" ", "");
        for(String i : clients.keySet()){
            try{
                Socket socketAtual = clients.get(i);
                PrintWriter saida = new PrintWriter(socketAtual.getOutputStream());
                saida.println("MSG "+threadName+" "+msg);
                saida.flush();
            }catch (IOException e){
                System.out.println("Err. Enviar Mensagens");
                break;
            }
        }
        return 0;
    }

    public int enviaNotificacao(String tipoNotific, String threadName){
        String finalMSG;
        if(tipoNotific.equals("Entrar")){
            finalMSG = "ENTROU "+threadName;
        }else if(tipoNotific.equals("Sair")){
            finalMSG = "SAIU "+threadName;
        }else{
            finalMSG = "Erro "+threadName;
        }

        for(String i : clients.keySet()){
            try{
                Socket socketAtual = clients.get(i);
                PrintWriter saida = new PrintWriter(socketAtual.getOutputStream());
                saida.println(finalMSG);
                saida.flush();
            }catch (IOException e){
                System.out.println("Err. Enviar Mensagens");
                break;
            }
        }

        return 0;
    }

    public void meuShell(Thread threadActual, String linha){
        if(getComando(linha).equals("MSG")){
            enviaMensagens(threadActual.getName(), linha);
        }
        if(getComando(linha).equals("SAIR")){
            kickarCliente(threadActual);
        }
    }

    public void kickarCliente(Thread thread){
        for(String i : clients.keySet()){
            if(thread.getName().equals(i)){
                Socket sock = clients.get(i);
                clients.remove(i,sock);
                thread.stop();
                enviaNotificacao("Sair", thread.getName());
            }
        }
    }

}
