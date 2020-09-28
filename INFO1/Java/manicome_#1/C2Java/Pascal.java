public class Pascal {
	public static void main(String[] args) {
		System.out.println(" Cn, p = " + pascal(30000, 250));

		// la différence de vitesse s'explique par le fait que nous n'avons
		// pas besoin d'allouer la mémoire manuellement.
		
	}

	public static int pascal(int nBut, int pBut) {
		int[] tab = new int[nBut+1];
		int n, i;
		
		tab[0] = 1;

		for(n=1; n<=nBut; n++){
			tab[n] = 1;

			for(i=n-1; i>0; i--)
				tab[i] = tab[i-1] + tab[i];
		}

		int result=tab[pBut];
		return result;
	}
}
