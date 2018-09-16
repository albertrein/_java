import java.io.*;
import java.util.*;

public class Handler {

    public static int escreve(String messge){
        System.out.println("Stating Escreve ...");

        File arq = new File("messagesServerList.txt");
        try{
            FileWriter escriba = new FileWriter(arq,true);
            escriba.write(messge+"\n");
            escriba.flush();
            return 1;
        }catch(IOException e){
            System.out.println("Erro no arquivo");
            return 0;
        }
    }


}
