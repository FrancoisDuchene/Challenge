public class Dico {
	public static String lectureMots() {
		//Gestion des Flux I/O

		String str = "";
		Fichier fi = ouvrirDico();
		int nbrRandom = nbrRandom(fi.longueurFichier());
		str = fi.lire(nbrRandom);
		fi.fermer();
		return str;
	}
	public static Fichier ouvrirDico() {
		String nomFichier = "src/dico.txt";
		Fichier fi = new Fichier();
		fi.ouvrir(nomFichier, "Lecture");
		return fi;
	}
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
	public static int nbrRandom(int longueur) {
		double nbr = Math.random();
		nbr = nbr*longueur;
		int nbrRandom = (int) nbr;
		return Math.abs(nbrRandom);
	}
}
