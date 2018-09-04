public class Main {
    public static void main(String[] args) {
        NumeroPrimo n = new NumeroPrimo();

        try{
            System.out.println(n.isPrimoTrivial(-5));
        }catch(ValorInvalidoException e){
            System.out.println(e.getMessage());
        }
        //System.out.println(n.isPrimoTrivial(9));

        //System.out.println(n.isPrimoAdvanced(9));
        //n.isPrimoAdvanced(99999);
        //n.isPrimoTrivial(99999);
    }
}
