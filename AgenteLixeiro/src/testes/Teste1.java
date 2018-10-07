package testes;

public class Teste1 extends Thread{
    Teste1[] x;
    public Teste1(Teste1[] novaPos){
        x = novaPos;
    }

    @Override
    public void run(){
        pulaPosicao();
    }


    public void pulaPosicao(){
        for (Teste1 t: x) {
            System.out.println(t);
        }
        x[1] = null;
        System.out.println(">>"+x[1]);
        try{
            Thread.currentThread().sleep(2000);
        }catch (InterruptedException e){

        }
    }

}
