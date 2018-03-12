class Data{
    //atributos
    private int dia, mes, ano;

    //metodos
    void setDia(int dia){
        this.dia = dia;
    }
    int getDia(){
        return this.dia;
    }

    void setMes(int mes){
        this.mes = mes;
    }
    int getMes(){
        return this.mes;
    }

    void setAno(int ano){
        this.ano = ano;
    }
    void getAno(){
        return this.ano;
    }

    String mostraData(){
      String concatena =  this.dia+"/"+this.mes+"/"+this.ano;
      return concatena;
    }

  }

class PooExerc10{
  public static void main(String[] args){
    Data obj = new Data();
    obj.setDia(11);
    obj.setMes(03);
    obj.setAno(2018);

    System.out.println(obj.mostraData());

  }
}
