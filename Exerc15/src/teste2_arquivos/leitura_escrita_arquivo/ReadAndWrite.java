package teste2_arquivos.leitura_escrita_arquivo;

import java.io.*;
import java.util.Scanner;

public class ReadAndWrite {
    public static void main(String[] args) {
        try {
            File arq = new File("/home/willian/teste.txt");
            FileWriter escreve = new FileWriter(arq, true); //true o append, para adicionar no final do arquivo

            escreve.write("Craraiooooo");
            escreve.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ee) {
            ee.printStackTrace();
        }

    }
}
