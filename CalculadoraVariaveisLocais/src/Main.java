import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String particoes = in.nextLine();
        String[] arrayParticoes = particoes.split(",");
        int[] listaParticoes = new int[arrayParticoes.length];

        for (int i = 0; i < arrayParticoes.length; i++) {
            listaParticoes[i] = Integer.parseInt(arrayParticoes[i]);
            System.out.println(listaParticoes[i]);
        }

        String solicitacoes = in.nextLine();
        String[] arraySolicitacoes = solicitacoes.split(",");
        int[] listaSolicitacoes = new int[arraySolicitacoes.length];

        for (int i = 0; i < arraySolicitacoes.length; i++) {
            listaSolicitacoes[i] = Integer.parseInt(arraySolicitacoes[i]);
            System.out.println(listaSolicitacoes[i]);
        }

        firsFit(listaParticoes, listaSolicitacoes);

    }

    public static void firsFit(int[] list1, int[] list2){
        int[] partcoes = list1;

        for(int i = 0; i < list2.length; i++){
            for (int j = 0; j < list1.length; j++) {
                if(list1[j] >= list2[i]){
                    list1[j] -= list2[i];
                    break;
                }

            }
        }

        for (int i = 0; i < list1.length; i++) {
            System.out.println(">>"+list1[i]);
        }




    }
}
