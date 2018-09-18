import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class FIleReaderr {
    public static void main(String[] args) {
        try{
            FileReader reader = new FileReader("messagesSer2verList.txt");
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }
    }
}
