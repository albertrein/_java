import java.util.Random;

public abstract class Ambiente {
    private String objectType;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public String toString(){
        return objectType;
    }



    private Lixo seco;
    private Lixo organico;
    private boolean isFull;

    public Lixo getSeco() {
        return seco;
    }

    public void setSeco(Lixo seco) {
        this.seco = seco;
    }

    public Lixo getOrganico() {
        return organico;
    }

    public void setOrganico(Lixo organico) {
        this.organico = organico;
    }
}
