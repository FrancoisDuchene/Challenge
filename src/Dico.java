//import java.io.IOException;

public class Dico 
{
	public static void lectureMots()
	{
		//Gestion des Flux I/O
		boolean condiError = false;
		String str = "";
		String nomFichier = "src/dico.txt";
		Fichier fi = new Fichier();	
		
		fi.ouvrir(nomFichier, "Lecture");
		int nbrRandom = nbrRandom(fi);
		System.out.println(nbrRandom);						
		str = fi.lire(15);
		System.out.println(str);
		try {
				condiError = fi.fermer();
			}
		catch (Exception e) 
			{					
				e.printStackTrace();
			}			
		
	}
	public static int nbrRandom(Fichier fi)
	{
		double nbr = Math.random();
		nbr = nbr*1000;
		while(nbr > fi.longueurFichier())
		{
			nbr = nbr/2;
			nbr = nbr + 1.3;
		}
		int nbrRandom = (int) nbr;
		return nbrRandom;		
	}
	
}
