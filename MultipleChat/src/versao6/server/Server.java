package versao6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Server extends Thread {
    private HashMap<String, Socket> clientes = new HashMap<>();

    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(8976);
            System.out.println("Starting Server ...");

            while(true){
                Socket socket = server.accept();

                new Thread(){
                    @Override
                    public void run(){
                        try{
                            Scanner entrada = new Scanner(socket.getInputStream());
                            boolean inicio = true;
                            String chatEntrada;
                            while((chatEntrada = entrada.nextLine()) != null){
                                StringTokenizer token = new StringTokenizer(chatEntrada," ");
                                String comando = token.nextToken();

                                if(inicio){
                                    if(comando.equals("ENTRAR") && novoCliente(socket, currentThread(), getMensagem(chatEntrada,comando)) != 1){
                                        inicio = false; //Não é mais inicio
                                    }else{
                                        System.out.println("#Fora");
                                        entrada.close();
                                        socket.close();
                                        return;
                                    }
                                }

                                if(comando.equals("MSG")){
                                    if(enviaMensagens(currentThread(), getMensagem(chatEntrada,comando)) == 1)
                                        System.out.println("Err. Enviar msg.");

                                }

                                if(comando.equals("SAIR")){
                                    if(removeCliente(currentThread()) == 0){
                                        entrada.close();
                                        return;
                                    }

                                }

                            }

                        }catch (IOException ioexx){
                            System.out.println("Err. Mensageiro");
                            ioexx.printStackTrace();
                        }catch (NoSuchElementException e){
                            System.out.println("num vi");
                        }finally {
                            System.out.println("CLiente saiu");
                        }
                    }
                }.start();
            }

        }catch (IOException ioex){
            System.out.println("Err. Não foi possível criar o servidor.");
        }
    } //Fim da run

    public String getMensagem(String linha, String comando){
        return linha.replaceFirst(comando+" ","");
    }

    public int novoCliente(Socket socket, Thread currentThread, String nick){
        if(clientes.size() > 99)
            return 1;
        for(String i : clientes.keySet()){
            if(i.equals(nick))
                return 1;
        }
        currentThread().setName(nick);
        clientes.put(nick, socket);
        notificaTodos("ENTRAR",currentThread);

        sendLastMessages(socket);

        return 0;

    }

    public int enviaMensagens(Thread currThread, String msg){
        for(String i : clientes.keySet()){
            try{
                PrintWriter enviaMsg = new PrintWriter(clientes.get(i).getOutputStream());
                enviaMsg.println("MSG "+currThread.getName()+" "+msg);
                enviaMsg.flush();
            }catch (IOException e){
                System.out.println("Err. Ao enviar mensagem");
                return 1;
            }
        }
        saveMessage(msg, currThread);
        return 0;
    }

    public void sendError(Thread client, String msgErro){ ///!!! REVER ESTE MÉTODO
        for(String i : clientes.keySet()){
            if(i.equals(client.getName())){
                try{
                    PrintWriter out = new PrintWriter(clientes.get(i).getOutputStream());
                    out.println(msgErro);
                    out.flush();
                }catch (IOException e){
                    System.out.println("Err. Ao enviar mensagem de erro.");
                }
            }
        }
    }

    public int removeCliente(Thread currThread){
        for(String i : clientes.keySet()){
            if(currThread.getName().equals(i)){
                Socket socket = clientes.get(i);
                if(clientes.remove(i,socket)){
                    notificaTodos("SAIR", currThread);
                    return 0;
                }
            }
        }
        return 1;
    }

    public void notificaTodos(String tipoMensagem, Thread currThread){
        String mensagem = "";
        switch (tipoMensagem){
            case "ENTRAR":
                mensagem = "ENTROU "+currThread.getName();
                break;
            case "SAIR":
                mensagem = "MSG "+currThread.getName()+" saiu do grupo";
                break;
            default:
                mensagem = "Err. "+currThread.getName(); /*!!! RE-VER, ENVIAR SOMENTE PARA QUEM ESTÁ GERANDO O PROBLEMA!!!*/
                break;
        }

        for(String i : clientes.keySet()){
            try {
                PrintWriter notificacao = new PrintWriter(clientes.get(i).getOutputStream());
                notificacao.println(mensagem);
                notificacao.flush();
            }catch(IOException e){
                System.out.println("Err. Ao enviar mensagens");
                return;
            }
        }
    }

    public void saveMessage(String msgSave, Thread currThread){
        new Thread(){
            @Override
            public void run(){
                try{
                    FileWriter save = new FileWriter("messageServer.txt",true);
                    save.write("MSG "+currThread.getName()+" "+msgSave+"\n");
                    save.flush();
                }catch(IOException e){
                    System.out.println("Err. Salvar mensagem.");
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void sendLastMessages(Socket sock){ //REtorna mensagem para cliente especifico
        new Thread(){
            @Override
            public void run(){
                File arq = new File("messageServer.txt");
                Scanner leitor = null;
                try {
                    leitor = new Scanner(arq);

                    int numeroLinhas = contadorLinhas(arq);
                    int posicao;

                    if(numeroLinhas >= 7){
                        posicao = numeroLinhas - 7;
                    }else{
                        posicao = 0;
                    }
                    PrintWriter saida = new PrintWriter(sock.getOutputStream());
                    String linha;
                    for(int i = 0; leitor.hasNextLine(); i++){
                        linha = leitor.nextLine();
                        if(i >= posicao){
                            saida.println(linha);
                            saida.flush();
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }catch (IOException ee){
                    ee.printStackTrace();
                }

            }

            public int contadorLinhas(File file){
                try {
                    Scanner leitor = new Scanner(file);
                    int contador = 0;
                    for(; leitor.hasNextLine(); contador++)
                        leitor.nextLine();
                    return contador;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }.start();
    }
}
