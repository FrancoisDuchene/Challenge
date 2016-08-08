package source;

import fichier.FichierR;
import fichier.FichierW;

public class Dico {
	private static byte choixDico=challenge.getLanguage();	
	/**
	 * This function read the dictionary and return a random word
	 * @return a random word
	 */
	public static String lectureMots() {
		//Gestion des Flux I/O

		String str = "";
		FichierR fi = ouvrirDicoR();
		final int longueur = fi.longueurFichier();
		final int nbrRandom = nbrRandom(longueur);			
		str = fi.lire(nbrRandom);		
		fi.fermerFluxReader();		
		return str;
	}
	/**
	 * This function open the dico with the path per default "res/data/dico_fr.txt"
	 * @return a file describing the dico 
	 */
	public static FichierR ouvrirDicoR() {
		String nomFichier = null;
		switch(choixDico)
		{
		case 1:
			nomFichier = "res/data/dico_fr.txt";
			break;
		case 2:
			nomFichier = "res/data/dico_en.txt";
			break;
		}
		FichierR f = new FichierR(nomFichier);
		f.ouvrirFluxReader();
		return f;
	}
	
	public static FichierW ouvrirDicoW(){
		String nomFichier = null;
		switch(choixDico)
		{
		case 1:
			nomFichier = "res/data/dico_fr.txt";
			break;
		case 2:
			nomFichier = "res/data/dico_en.txt";
			break;
		}
		FichierW f = new FichierW(nomFichier);
		f.ouvrirFuxWriter(true);
		return f;
	}
	/**
	 * This function write a word gived in parameter to the end of the current file
	 * @param mot a word to write
	 */
	public static void ecritureMot(String mot) {
		FichierR f = ouvrirDicoR();
		FichierW fi = ouvrirDicoW();
		if(f.equalsMots(mot))
		{
			fi.introduireEspace();			
			fi.ecrireString(mot);			
		}
		else {
			System.out.println("Desole ce mot existe déjà dans le dictionnaire");
		}
	}
	/**
	 * 
	 * @param longueur the length of the file currently used
	 * @return a random number between 0 and the file length
	 */
	private static int nbrRandom(int longueur) {
		double nbr = Math.random();
		nbr = nbr*longueur;
		int nbrRandom = (int) nbr;
		return Math.abs(nbrRandom);
	}
	public static void setChoixDico(byte choixD)
	{
		choixDico=choixD;
	}
}