public class Thread2 extends Thread{
    public Thread2(int i){
        i += 2;
    }

    @Override
    public void run(){
        while(true) {
            synchronized(Thread1.class){
                System.out.println("Pong");
                yield();
            }
        }
    }
}
