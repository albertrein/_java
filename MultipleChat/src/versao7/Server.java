package versao7;

import java.net.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Server extends Thread {
    private HashMap<String, Socket> clientes = new HashMap<>();
    private final int getNumeroLinhasArq = 20;
    private final int numeroMaximoClientes = 1;

    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(7777);
            while(true){
                Socket newCliente = server.accept();

                new Thread(){ //Thread para capturar as mensagens
                    @Override
                    public void run(){
                        try{
                            Scanner in = new Scanner(newCliente.getInputStream());
                            boolean isInicio = true;
                            String entrada;
                            while((entrada = in.nextLine())!=null){
                                if(isInicio){
                                     if(getComand(entrada).equals("ENTRAR")){
                                         if(!insertNewClient(newCliente, currentThread(), getMsg(entrada))) {
                                             sendErrorMessages(newCliente,"Você não pode se conectar. Saindo.");
                                             newCliente.close();
                                         }
                                     }else{
                                         sendErrorMessages(newCliente,"Você não pode se conectar. Saindo.");
                                         newCliente.close();
                                     }
                                    isInicio = false;
                                }
                                if(getComand(entrada).equals("MSG")){
                                    newMessage(currentThread(),getMsg(entrada));
                                }else if(getComand(entrada).equals("SAIR")){
                                    kickUser(currentThread());
                                }else{
                                    System.out.println("Mensagem não reconhecida");
                                }
                            }
                        }catch(IOException ioe){
                            System.out.println("Não foi possivel criar a entrada de dados");
                        }catch (NoSuchElementException e){
                            System.out.println(currentThread()+" saiu do grupo");
                            kickUser(currentThread());
                        }
                    }
                }.start();
            }


        }catch (IOException ioex){
            System.out.println("Erro ao iniciar o servidor.");
        }

    }//run end

    public String getComand(String completeMessage){
        StringTokenizer token = new StringTokenizer(completeMessage, " ");
        return token.nextToken();
    }
    public String getMsg(String completeMessage){
        StringTokenizer token = new StringTokenizer(completeMessage, " ");
        String comando = token.nextToken();
        return completeMessage.replaceFirst(comando+" ","");
    }

    public boolean sendErrorMessages(Socket cliente, String msg){
        try{
            PrintWriter saida = new PrintWriter(cliente.getOutputStream());
            saida.println(msg);
            saida.flush();
        }catch (IOException e){
            System.out.println("Err. Ao enviar mensagem de erro para o cliente:");
            return false;
        }catch (NullPointerException ee){
            System.out.println("NUlPoint");
        }
        return true;
    }

    public boolean sendMessagesFromAll(String message){
        for(String i : clientes.keySet()){
            try {
                PrintWriter notificacao = new PrintWriter(clientes.get(i).getOutputStream());
                notificacao.println(message);
                notificacao.flush();
            }catch(IOException e){
                System.out.println("Err. Ao enviar mensagens");
                return false;
            }
        }
        return true;
    }


    public boolean insertNewClient(Socket currSocket, Thread currThread, String nick){
        if(this.clientes.size() > numeroMaximoClientes)
            return false;

        for(String i : clientes.keySet()){
            if(i.equals(nick))
                return false;
        }
        currThread.setName(nick); // SETA O NOME DA THREAD COM O NICK DIGITADO
        clientes.put(nick, currSocket); //ADICIONA UM NOVO SOCKET
        getLastMessages(currSocket); //Pega a ultimas mensagens
        sendMessagesFromAll("MSG "+currThread.getName()+" Entrou no grupo"); //Envia mensagem para todos os clientes
        return true;
    }

    public boolean newMessage(Thread currThread, String mensagem){
        PrintWriter enviaMsg = null;
        String msg = "";
        for(String i : clientes.keySet()){
            try{
                enviaMsg = new PrintWriter(clientes.get(i).getOutputStream());
                msg = "MSG "+currThread.getName()+" "+mensagem;
                enviaMsg.println(msg);
                enviaMsg.flush();
            }catch (IOException e){
                msg = "Err. Ao receber mensagem";
            }
        }
        saveMessage(mensagem, currThread);
        return true;
    }

    public boolean kickUser(Thread currThread){
        try{
            Socket saida = clientes.get(currThread.getName());
            saida.close();
            clientes.remove(currThread.getName(),saida);
            sendMessagesFromAll("MSG "+currThread.getName()+" saiu do grupo");
        }catch (IOException e){
            System.out.println("Err. Ao excluir funcionario.");
            return false;
        }catch (NullPointerException ee){

        }
        return true;
        //manda mensagem de sair para todos
        //manda mensagem do erro ou motivo para o especifico
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

    public void getLastMessages(Socket sock){ //Envia as ultimas mensagens para cliente
        new Thread(){
            @Override
            public void run(){
                File arq = new File("messageServer.txt");
                Scanner leitor = null;
                try {
                    leitor = new Scanner(arq);

                    int numeroLinhas = contadorLinhas(arq);
                    int posicao;

                    if(numeroLinhas >= getNumeroLinhasArq){
                        posicao = numeroLinhas - getNumeroLinhasArq;
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
