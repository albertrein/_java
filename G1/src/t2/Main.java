package t2;

public class Main {
    public static void main(String[] args) {
        TesteThread t1 = new TesteThread("Guilherme");
        TesteThread t2 = new TesteThread("Alberto");
        TesteThread t3 = new TesteThread("Reinheimer");

        t1.start();
        t2.start();
        t3.start();


    }
}
