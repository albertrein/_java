package Threads;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Simulando um servdor de chat
        TH1 t1 = new TH1();
        System.out.println("Iniciando THREAD");
        t1.start();
    }
}
