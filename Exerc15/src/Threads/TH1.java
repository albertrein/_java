package Threads;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TH1 extends Thread {
    private HashMap<String, Socket> clients = new HashMap<>();
    private ArrayList<Scanner> entradas;

    @Override
    public void run() {
        System.out.println("Iniciando Servidor ....");
        try (ServerSocket server = new ServerSocket(9090)) {

            while (true) {


            }

        } catch (IOException ioex) {
            System.out.println("Erro Server .::.");
            ioex.printStackTrace();
        } catch (NoSuchElementException e) {
        }
    }

    private void asd(){

    }

    private void comando(String i, Socket sock) {
        Socket client = sock;
        try (PrintWriter out = new PrintWriter(client.getOutputStream())) {
            if (i == "OI") {
                out.println("helloooo");
                out.flush();
            }

        } catch (IOException e) {
            System.out.println("Noa deu");
        }

    }
}
