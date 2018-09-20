package Passwords;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SafePass implements Serializable {
    private ArrayList<String> ss = new ArrayList();

    public void addPass(String pass) throws InternalError {
        if (ss.contains(pass)) {
            throw new InternalError("Valor já inserido.");
        }
        ss.add(pass);
    }

    public void show(){
        System.out.println("&&&&&&&&&&&");
        for(int i = 0; i < ss.size(); i++){
            System.out.println(">>> "+ss.get(i));
        }
        System.out.println("&&&&&&&&&&&");
    }
}
