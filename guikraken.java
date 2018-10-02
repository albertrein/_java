import java.io.IOException;
import java.util.Scanner;

class Guikraken{
	public static void main(String args[]){
		// if(args[0].equals("--help"))
		// 	Help.help();

		// Scanner in = new Scanner(System.in);
		// String msg = in.nextLine();
		String msg = "git commit -m \'"+(String)args[0]+"\'";
		new GuitKraken(msg).start();

	}
}

class GuitKraken extends Thread{
	private Process execute;
	private String comando;

	public GuitKraken(String command){
		this.comando = command;
	}



	@Override
	public void run(){
		System.out.println(">>"+this.comando);
		executeComand();
	}


	synchronized public void executeComand(){
		try{
			this.execute = Runtime.getRuntime().exec(this.comando);
			this.execute.waitFor();
			Scanner entrada = new Scanner(this.execute.getInputStream());

            String line;
            while((line = entrada.nextLine()) != null){
                System.out.println(line);
            }
		}catch (IOException e){
        	System.out.println("Err. Comando não foi executado.");
        }catch (InterruptedException ee){
        	ee.printStackTrace();
        }
        yield();
	}

}


class Help{
	public static void help(){
		System.out.println("*** How to use Guikraken ***");
		System.out.println("java Guikraken <TextToCommit>");
		System.out.println(" --------------------------- ");
		System.exit(0);
	}
}