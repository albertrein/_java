package teste1;
import java.io.Serializable;
public class Teste1 implements Serializable{
    private String name;

    public void setName(String newName){
        this.name = newName;
    }

    public String getName(){
        return this.name;
    }
}
