package teste1_manyClients;

public class Main {
    public static void main(String[] args) {
        Cliente[] cl = new Cliente[10000];

        for(int i = 0; i < cl.length; i++){
            cl[i] = new Cliente();
            cl[i].start("#"+i);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }


//        Cliente cl1 = new Cliente();
//        Cliente cl2 = new Cliente();
//        Cliente cl3 = new Cliente();
//
//        cl1.start("#1");
//        cl2.start("#2");
//        cl3.start("GUILHERMEEEEE");



    }
}
