package t3;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class File {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        java.io.File arq = new java.io.File(file);
        Path path = Paths.get("nio.txt");
    }
}
