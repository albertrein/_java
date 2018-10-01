public class Lixeira extends Ambiente{
    private String tipoLixeira;

    public Lixeira(){}

    public Lixeira(String tipo){
        setTipoLixeira(tipo);
    }

    public void setTipoLixeira(String lixeiraTipo){
        this.tipoLixeira = lixeiraTipo;
    }

    public String getTipoLixeira(){
        return this.tipoLixeira;
    }

    @Override
    public void ambience(){
        System.out.println("Lixeirar");
    }

    @Override
    public void sd(int valor){ //IMpares e soma
        Integer soma = 0;
        for (; valor > 0 ; valor--) {
            if((valor%2)!=0){
                System.out.print(valor+"|");
                soma += valor;
            }
        }

        System.out.println(soma);
    }

    @Override
    public String toString(){
        return this.tipoLixeira;
    }
}
