import java.io.IOException;

//import java.io.IOException;

public class Dico 
{
	public static String lectureMots()
	{
		//Gestion des Flux I/O
		
		String str = "";
		Fichier fi = ouvrirDico();				
		int nbrRandom = nbrRandom(fi);			
		str = fi.lire(nbrRandom);				
		try {
				fi.fermer();
			}
		catch (Exception e) 
			{					
				e.printStackTrace();
			}			
		return str;
	}
	public static Fichier ouvrirDico()
	{
		String nomFichier = "src/dico.txt";
		Fichier fi = new Fichier();
		fi.ouvrir(nomFichier, "Lecture");
		return fi;
	}
	public static void ecritureMot(String mot)
	{
		Fichier fi = ouvrirDico();
		if(fi.equalsMots(mot, fi))
		{
			fi.toEnd();
			fi.introduireEspace();
			try {
				fi.ecrireString(mot);
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Desole ce mot existe déjà dans le dictionnaire");
		}
		
	}
	public static int nbrRandom(Fichier fi)
	{
		double nbr = Math.random();
		nbr = nbr*fi.longueurFichier();		
		int nbrRandom = (int) nbr;
		return Math.abs(nbrRandom);		
	}
	
}
