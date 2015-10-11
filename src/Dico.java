//import java.io.IOException;

public class Dico 
{
	public static String lectureMots()
	{
		//Gestion des Flux I/O
		
		String str = "";
		String nomFichier = "src/dico.txt";
		Fichier fi = new Fichier();	
		
		fi.ouvrir(nomFichier, "Lecture");				
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
	public static int nbrRandom(Fichier fi)
	{
		double nbr = Math.random();
		nbr = nbr*1000;
		while(nbr > fi.longueurFichier())
		{
			nbr = (nbr/2) + (0.5*fi.longueurFichier()) -186.4;			
		}
		int nbrRandom = (int) nbr;
		return Math.abs(nbrRandom);		
	}
	
}
