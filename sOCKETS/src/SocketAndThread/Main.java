package SocketAndThread;

public class Main {
    public static void main(String[] args) {
        Client cli = new Client();
        Server serv = new Server();

        serv.start();
        cli.start();



    }
}
