public class Main {
    public static void main(String[] args) {
        int i = 0;
        Thread1 th1 = new Thread1(i);
        Thread2 th2 = new Thread2(i);
        th1.start();
        th2.start();

    }
}
