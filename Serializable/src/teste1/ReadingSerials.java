package teste1;

import java.io.*;

public class ReadingSerials {
    public static void main(String[] args) {
        //Lendo Objetos serializadoos
        Teste1 teste1 = null;
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("teste1.ser"));
            teste1 = (Teste1) entrada.readObject();
            entrada.close();
        }catch (IOException e){

        }catch (ClassNotFoundException ee){

        }
        System.out.println(teste1.getName());

    }
}
