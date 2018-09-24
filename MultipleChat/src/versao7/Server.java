package versao7;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server extends Thread {
    private HashMap<String, Socket> clientes = new HashMap<>();
    private final int getNumeroLinhasArq = 7;

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
                                         if(!insertNewClient(newCliente, currentThread(), getMsg(entrada)))
                                             //kickar usuario
                                         isInicio = false;
                                     }else{
                                         //kickar
                                     }
                                }else if(getComand(entrada).equals("MSG")){
                                    newMessage(currentThread(),getMsg(entrada));
                                }else if(getComand(entrada).equals("SAIR")){
                                    //removerCLiente
                                }else{
                                    System.out.println("Mensagem não reconhecida");
                                }
                            }

                        }catch(IOException ioe){
                            System.out.println("Não foi possivel criar a entrada de dados");
                        }catch (NoSuchElementException e){
                            System.out.println(currentThread()+" saiu do grupo");
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
        if(this.clientes.size() > 99)
            return false;

        for(String i : clientes.keySet()){
            if(i.equals(nick))
                return false;
        }
        currThread.setName(nick); // SETA O NOME DA THREAD COM O NICK DIGITADO
        clientes.put(nick, currSocket); //ADICIONA UM NOVO SOCKET
        sendMessagesFromAll("ENTROU "+currThread); //Envia mensagem para todos os clientes
        getLastMessages(currSocket); //Pega a ultimas mensagens
        return true;
    }

    public boolean newMessage(Thread currThread, String mensagem){
        PrintWriter enviaMsg = null;
        String msg = "";
        for(String i : clientes.keySet()){
            try{
                enviaMsg = new PrintWriter(clientes.get(i).getOutputStream());
                msg = "MSG "+currThread.getName()+" "+mensagem;
                saveMessage(mensagem, currThread);
            }catch (IOException e){
                msg = "Err. Ao receber mensagem";
            }finally {
                enviaMsg.println(msg);
                enviaMsg.flush();
            }
        }
        return true;
    }

    public void kickOut(){
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
