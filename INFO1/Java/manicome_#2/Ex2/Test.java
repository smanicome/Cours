public class Test {	
	public static void main(String[] args) {
		var first = args[0];
		var second = args[1];
		var last = args[2];
		System.out.println(first + ' ' + second + ' ' + last);

		// On peut utiliser ' ' pour remplacer " ", car ' ' désigne le caractère qui est ensuite converti
		// en String, on constate le même résultat si on remplace la caractère par un entier.
		// String accepte exceptionnellement l'opérateur +, et converti les types primaire lors de 
		// la concaténation.

		// Il semble que javap -c affiche la pile d'execution
	}
}