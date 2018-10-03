import java.util.*;

public class Main {
    private static final int tamanhoMatriz = 8;

    public void geraLixos(Ambiente[][] matriz){
        do{
            int x = randomCoordenada(), y = randomCoordenada();
            if(matriz[x][y] != null)
                continue;

            matriz[x][y] = new Lixo("O");
        }while (true);
    }


    public static void geraAmbiente(Ambiente[][] novaMatrizAmbiente){
        novaMatrizAmbiente[1][6] = new Lixeira("LS ");
        novaMatrizAmbiente[6][1] = new Lixeira("LO ");
        geraLixos(novaMatrizAmbiente);
    }

    public static int randomCoordenada(){
        Random rand = new Random();
        return rand.nextInt(tamanhoMatriz-1);
    }

    public static void main(String[] args) {
        Ambiente[][] ambiente = new Ambiente[8][8];
        geraAmbiente(ambiente);


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
