package teste2_arquivos.teste_leitura_normal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ultimas = 5;
        try{
            File arq = new File("/home/willian/teste.txt");
            Scanner linha = new Scanner(arq);
            int contador;
            for(contador = 0; linha.hasNextLine(); contador++)
                linha.nextLine();

            Scanner linha2 = new Scanner(arq);

            for(int i = 0; linha2.hasNextLine(); i++){
                String line = linha2.nextLine();
                if(i >= 15){
                    System.out.println(line);
                }
            }

        }catch (FileNotFoundException e){
                e.printStackTrace();
        }
        //System.out.println(muaha);
    }
}
