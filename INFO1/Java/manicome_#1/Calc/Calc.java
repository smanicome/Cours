import java.util.Scanner;

public class Calc {
	public static void main(String[] args) {
		// variable scanner de type Scanner
		Scanner scanner = new Scanner(System.in);
		// variable value de type int
		int value = scanner.nextInt();
		int value1 = scanner.nextInt();
		scanner.close();
		System.out.println("+: " + value + value1);
		System.out.println("-: " + (value - value1));
		System.out.println("*: " + value * value1);
		System.out.println("/: " + value / value1);
		System.out.println("%: " + value % value1);

		// nextInt n'est pas un fonction car elle est rattachée à une classe,
		// c'est donc une méthode
		
		// import java.util.Scanner permet d'importer la librairie qui permet d'utiliser
		// scanner.
	}
}