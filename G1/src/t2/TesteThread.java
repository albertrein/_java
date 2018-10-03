package t2;

public class TesteThread extends Thread {
    private String frase;
    public TesteThread(String str){
        this.frase = str;
    }


    @Override
    public void run(){
        showFrase();
    }

    public void showFrase() {
        synchronized (this){
            System.out.println(this.frase);
        }
    }
}
