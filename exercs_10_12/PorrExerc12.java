class Endereco{
  //atributos
  String pais, estado, cidade;

  //metodos
  void setEndereco(String pais, String estado, String cidade){
    this.pais = pais;
    this.estado = estado;
    this.cidade = cidade;
  }

  void getEndereco(){
    System.out.println("Pais: "+this.pais+" Estado: "+this.estado+" Cidade: "+this.cidade);
  }
}

class Aluno{
  Endereco ender = new Endereco();
  //atributos
  String nome;
  int cgu;

  void setAluno(String nome, int cgu, String pais, String estado, String cidade){
    this.nome = nome;
    this.cgu = cgu;
    ender.setEndereco(pais, estado, cidade);
  }

  void getAluno(){
    System.out.println("Nome: "+this.nome+" CGU: "+this.cgu);
    ender.getEndereco();
  }
}

class principal{
  public static void main(String[] args){
    Aluno al = new Aluno();
    al.setAluno("Guilherme",120437710,"Brazil","RS","Gravataí");
    al.getAluno();
  }
}
