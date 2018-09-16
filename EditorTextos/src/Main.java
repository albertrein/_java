import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Digite o nome e endereço do arquivo: ");
        String local = in.nextLine();
        File archive = new File(local);
        try(FileReader reader = new FileReader(archive.getAbsoluteFile())){
            Scanner leitor = new Scanner(reader.toString());

            while(leitor.nextLine() != null){
                System.out.println(leitor);
            }

            reader.close();
            leitor.close();

        }catch(IOException ioex){
            ioex.printStackTrace();
        }

    }
}
