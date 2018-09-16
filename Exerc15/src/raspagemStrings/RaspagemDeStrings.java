package raspagemStrings;

import java.util.Scanner;
import java.util.StringTokenizer;

public class RaspagemDeStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String string = in.nextLine();

        StringTokenizer token = new StringTokenizer(string," ");//Cria objeto do tokem para o espaço vazio
        String comando = token.nextToken();//Retira o comando antes do primeiro Space

//        String msg = string.replaceFirst(comando+" ", "");

        System.out.println(string.replaceFirst(comando+" ", ""));

        /*
        if(comando.equals("ENTRAR")){
            System.out.println("Bem-vindo "+msg);
        }else if(comando.equals("MSG")){
            System.out.println(msg);
        }else if(comando.equals("SAIR")){
            System.out.println("Bye! Bye! :)");
        }else{
            System.out.println("Err. Comando não compreendido");
        }*/
    }
}
