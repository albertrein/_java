public class TesteException {
    public static void main(String[] args) {

        int val1 = 10, val2 = 0;

        Thread t =new Thread(){
            public void run(){
                int result;
                if(val1 == 10)
                    throw new ArithmeticException();

            }
        };

        try{
            t.start();
        }catch (InternalError e){
            System.out.println("asdasd");
        }
    }
}
