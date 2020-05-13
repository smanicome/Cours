import java.util.Arrays;

public class Sum {
	public static void main(String[] args) {
		int[] array = parseStringArray(args);
		System.out.println(Arrays.toString(array));
		int sum = sumArray(array);
		System.out.println(sum);

		// Une méthode statique est une méthode qui peut être éxectuer sans que l'on ai à instancier
		// la classe dans laquelle la méthode est contenue.

		// Si un mot passé n'est pas un nombre, une exception est jetée
	}

	public static int[] parseStringArray(String[] args) {
		int[] array = new int[args.length];

		for(int i=0; i<args.length; i++) {

			try {
				array[i] = Integer.parseInt(args[i]);
			} catch (Exception e) {
				System.out.println(args[i] + " is not an int");
			}
		}
		return array;
	}

	public static int sumArray(int[] array) {
		int sum = 0;
		for(int elt: array) {
			sum += elt;
		}	
		return sum;
	}
}
