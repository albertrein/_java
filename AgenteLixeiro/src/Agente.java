public class Agente extends Ambiente {

    private Lixo seco;
    private Lixo organico;
    private boolean isFull;

    public Agente(String agente){
        setObjectType(agente);
    }


}
