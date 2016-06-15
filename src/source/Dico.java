package source;

import fichier.Fichier;

public class Dico {
	private static byte choixDico=challenge.getLanguage();	
	/**
	 * This function read the dictionary and return a random word
	 * @return a random word
	 */
	public static String lectureMots() {
		//Gestion des Flux I/O

		String str = "";
		Fichier fi = ouvrirDico();
		final int longueur = fi.longueurFichier();
		int nbrRandom = nbrRandom(longueur);			
		str = fi.lire(nbrRandom);		
		fi.fermer();		
		return str;
	}
	/**
	 * This function open the dico with the path per default "res/data/dico_fr.txt"
	 * @return a file describing the dico 
	 */
	public static Fichier ouvrirDico() {
		String nomFichier = "res/data/dico_fr.txt";
		switch(choixDico)
		{
		case 1:
			nomFichier = "res/data/dico_fr.txt";
			break;
		case 2:
			nomFichier = "res/data/dico_en.txt";
			break;
		}
		
		Fichier fi = new Fichier();
		fi.ouvrir(nomFichier, "Lecture",true);
		return fi;
	}
	/**
	 * This function write a word gived in parameter to the end of the current file
	 * @param mot a word to write
	 */
	public static void ecritureMot(String mot) {
		Fichier fi = ouvrirDico();
		if(fi.equalsMots(mot))
		{			
			fi.toEnd();
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