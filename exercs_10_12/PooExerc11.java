package pooecerc11;

import java.util.Scanner;

class Salario{
    String nome;
    double salario;

    void setNome(String nome){
      this.nome = nome;
    }
    String getNome(){
      return this.nome;
    }

    void setSalario(double salario){
      this.salario = salario;
    }
    double getSalario(){
      return this.salario;
    }

    void aumentaSalario(double porcentagem){
      double novoSalario = ((getSalario() * porcentagem)/100);
      novoSalario += getSalario;
      setSalario(novoSalario);
    }

}

class PooExerc11{
    public static void main(String[] args){
      Scanner input = new Scanner();
      Salario obj = new Salario();

      System.out.println("Digite o nome: ");
      String nome = input.nextLine();
      obj.setNome(nome);
      System.out.println("Digite o salario: ");
      double salario = input.nextDouble();
      if(salario <= 0 ){
        salario = 0;
      }
      obj.setSalario(salario);
      System.out.println("Digite o percentual de aumento: ");
      double percentual = input.nextDouble();

      obj.aumentaSalario(percentual);

    }
}
