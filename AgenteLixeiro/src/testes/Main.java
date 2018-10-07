package testes;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args){
        Teste1[] x = new Teste1[4];
        x[1] = new Teste1(x);

        x[1].start();

        System.out.println("+++++++++++++++");
        for (Teste1 t: x) {
            System.out.println(t);
        }



    }
}
