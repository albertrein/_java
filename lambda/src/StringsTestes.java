import java.util.Scanner;

import static javax.swing.text.html.HTML.Tag.U;

public class StringsTestes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] findEmoji = {":)", ":(", ";)", ":p"};
        String[] emoji = {"\ud83d\ude02", "\ud83d\ude21", "\ud83d\ude09", "\ud83d\ude0b"};

        String entrada = in.nextLine();
        if(!entrada.equals("SAIR")) {
            entrada = "MGS " + entrada;
            for(int l = 0; l < findEmoji.length; l++){
                entrada = entrada.replace(findEmoji[l],emoji[l]);
            }
        }

        System.out.println(entrada);
    }
}
