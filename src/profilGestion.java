
public class profilGestion {
	
	public static boolean existe = false;
	public static Joueur playerOne = new Joueur();
	public static void gestion()
	{		
		if(existe == false)
		{			
			System.out.println("Quel est votre nom ?");
			playerOne.setName(TextIO.getlnWord());
			existe = true;
		}
		else
		{
			System.out.println("\nNom : " + playerOne.getName());
			System.out.println("Scores Pendu");
			playerOne.afficheScoreSPd();
			System.out.println("Score Plus ou Moins");
			playerOne.afficheScoreSPM();
		}
		
	}
	public static boolean existe()
	{
		return existe;			
	}
}
