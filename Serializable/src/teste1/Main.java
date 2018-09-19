package teste1;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Main {
    public static void main(String[] args) {
        //Salvando objeto no arquivo
        Teste1 t1 = new Teste1();
        t1.setName("Alberto.");
        String file = "teste1.ser";
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(t1);
            out.flush();
            out.close();
            System.out.println("Saved!");
        }catch (IOException e){
            System.out.println("Not Saved. "+e.getMessage());
        }

    }
}
