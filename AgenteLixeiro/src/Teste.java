import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Process exet;
        try{
            exet = Runtime.getRuntime().exec("git push ");
            exet.waitFor();
            Scanner entrada = new Scanner(exet.getInputStream());

            String line;
            while((line = entrada.nextLine()) != null){
                System.out.println(line);
            }


        }catch (Exception e){

        }
    }
}
