import java.io.IOException;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String x = in.nextLine();
        x = "git commit -m '"+x+"'";
        //String x = " git status";



        Process exet;
        try{
            exet = Runtime.getRuntime().exec(x);
            exet.waitFor();
            Scanner entrada = new Scanner(exet.getInputStream());

            String line;
            while((line = entrada.nextLine()) != null){
                System.out.println(line);
            }


        }catch (IOException){

        }

    }
}
