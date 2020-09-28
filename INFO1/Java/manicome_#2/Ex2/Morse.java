public class Morse {
	static void message(String[] words) {
		/* String text = "";
		for (String word : words) {
			text += word + " Stop. ";
		} */

		/* 3. StringBuilder permet de créer un  String mutable
		La méthode append renvoie un StringBuilder car elle répond au Builder Pattern,
		de ce fait il est plus simple de construire le String grâce à un appel en chaîne
		de la méthode append.
		Ceci est possible car la StringBuilder retourné à la même référence, donc est le même objet,
		que le StringBuilder original */

		StringBuilder text = new StringBuilder("");
		for (String word : words) {
			text.append(word);
			text.append(" Stop. ");
		}

		// 4. La version précédente nécessite de stocker en cache les String à chaque itération de 
		// la boucle for, on évite donc de stocker des valeurs à usage unique

		// 5. La première version nécessite plus d'opération
		// On utilise append lorsque l'on souhaite modifier / construire un String dynamiquement,
		// si on cherche à construire un paragraphe la méthode on utilise append, si on souhaite
		// ajouter une fois un caractère ou un mot on utilise +
		//
		// J'ai fais cette erreur, (j'ai ris en lisant la question), et c'est évidemment pas très
		// malin d'optimiser le code avec append pour mettre en argument la raison de l'optimisation.

		System.out.println(text);
	}
}