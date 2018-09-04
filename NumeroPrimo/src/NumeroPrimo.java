public class NumeroPrimo{

    public boolean isPrimoTrivial(int val) throws ValorInvalidoException{
        if(!(val > 0)){
            throw new ValorInvalidoException("Valor Inválido");
        }
        int contador = 0;
        boolean retorno = false;

        for(int y = 1; y <= val; y++){
            for(int i = y; i > 0; i--){
                if(y % i == 0){
                    contador++;
                }
            }
            if(contador == 2){
                retorno = true;
            }
            contador = 0;
            System.out.println("#"+y+" eh:"+retorno);
            retorno = false;
        }

        return false;
    }

    public boolean isPrimoAdvanced(int val){
        int contador = 0;
        boolean retorno = true;

        for(int y = 1; y <= val; y++){
            for(int i = 1; i < y; i++){
                if((y % i == 0)){
                    contador++;
                    if(contador == 2){
                        retorno = false;
                        break;
                    }
                }
            }
            contador = 0;
            System.out.println("#"+y+" eh:"+retorno);
            retorno = true;

        }
        return true;
    }
}
