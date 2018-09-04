public class Thread1 extends Thread{
    public Thread1(int i){
        i += 2;
    }
    @Override
    public void run(){
        while(true) {
            synchronized (Thread2.class) {
                System.out.print("Ping ");
                yield();
            }
        }
    }
}
