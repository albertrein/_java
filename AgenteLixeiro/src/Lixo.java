public class Lixo extends Ambiente{
    private String tipo;

    public Lixo(String type){
        this.tipo = type;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void ambience() {

    }

    @Override
    public void sd(int val) {

    }
}
