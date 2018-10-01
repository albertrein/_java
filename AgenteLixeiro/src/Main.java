import java.util.*;

public class Main {
    public static Ambiente[][] geraAmbiente(Ambiente[][] novaMatrizAmbiente){
        novaMatrizAmbiente[1][6] = new Lixeira("LS ");
        novaMatrizAmbiente[6][1] = new Lixeira("LO ");
        return novaMatrizAmbiente;
    }




    public static void main(String[] args) {
        Ambiente[][] ambiente = new Ambiente[8][8];
        ambiente = geraAmbiente(ambiente);



        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(ambiente[i][j] == null)
                    System.out.print(" - ");
                else
                    System.out.print(ambiente[i][j]);

                System.out.print(" | ");
            }
            System.out.println();
        }


        System.out.println("---------------");
       // ambiente[4][4].sd(15);

    }
}
