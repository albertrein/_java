import java.io.IOException;
import java.util.*;

public class Main {
    //Atribs
    private static final int tamanhoMatriz = 8;
    private static Ambiente[][] ambiente;


    //methods
    public static int randomCoordenada(){
        Random rand = new Random();
        return rand.nextInt(tamanhoMatriz-1);
    }

    public static void geradorAmbiente(Ambiente novoAmbiente){
        int x, y;
        do{
            x = randomCoordenada();
            y = randomCoordenada();
            if(ambiente[x][y] != null)
                continue;

            ambiente[x][y] = novoAmbiente;
            break;
        }while (true);
    }

    public static void geradorAmbiente(Ambiente novoAmbiente, int linha, int coluna){
        if(linha >= tamanhoMatriz || coluna >= tamanhoMatriz){
            System.out.println("Err. Não foi possível criar alocação");
            return;
        }
        ambiente[linha][coluna] = novoAmbiente;
    }

    public static void imprimeAmbiente(){
        for (int i = 0; i < tamanhoMatriz; i++) {
            for (int j = 0; j < tamanhoMatriz; j++) {
                if(ambiente[i][j] == null)
                    System.out.print(" - ");
                else
                    System.out.print(ambiente[i][j]);

                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ambiente = new Ambiente[tamanhoMatriz][tamanhoMatriz];

        geradorAmbiente(new Lixeira("Ls "), 1, 6);
        geradorAmbiente(new Lixeira("Lo "), 6, 1);
        geradorAmbiente(new Agente(" A "));
        geradorAmbiente(new Lixo(" O "));
        geradorAmbiente(new Lixo(" S "));

        new AgentHandler(ambiente).start();

    }
}
