import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;
import java.io.*;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Cliente");
        try{
            Socket socket = new Socket("127.0.0.1", 7777);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("mensagem");
            out.flush();
            String line;
            while((line = in.nextLine()) != null){
                System.out.print("Papi ");
                out.println("mensagem");
                out.flush();
                System.out.println(line);
            }
            in.close();
            out.close();
            socket.close();
        }catch(UnknownHostException  e){
            e.printStackTrace();
        }catch(IOException ec){
            ec.printStackTrace();
        }
    }
}
