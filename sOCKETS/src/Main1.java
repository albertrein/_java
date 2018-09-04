import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Servidor");
        try{
            ServerSocket server = new ServerSocket(7777);
            Socket socket = server.accept();
            Scanner entrada = new Scanner(socket.getInputStream());
            PrintWriter saida = new PrintWriter(socket.getOutputStream());

            String linha;
            while((linha = entrada.nextLine()) != null){
                //System.out.println(linha);
                saida.println("paquigrafo");
                saida.flush();
//                try{
//                    Thread.sleep(3000);
//                }catch(InterruptedException inex){
//                    inex.printStackTrace();
//                }
            }
            server.close();
            entrada.close();
            saida.close();


        }catch (UnknownHostException unknowEx){
            unknowEx.printStackTrace();
        }catch(IOException ioex) {
            ioex.getStackTrace();
        }
    }
}
