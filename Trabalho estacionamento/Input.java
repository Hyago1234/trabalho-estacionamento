import java.util.Scanner;

public class Input {
    static Scanner in = new Scanner(System.in);

    static public int LerInteiro(String atributo){
        while(true){
			System.out.print("Digite o "+atributo+": ");
			if(in.hasNextInt())
				return in.nextInt();
			else System.err.println("Valor inv�lido: "+in.nextLine());
		}
    }
}
