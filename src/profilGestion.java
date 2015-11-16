
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
			System.out.println("Scores Pendu\n");
			playerOne.afficheScoreSPd();
			System.out.println("Score Pendu Total : " + playerOne.scorePenduTotal());
			System.out.println("Score Plus ou Moins\n");
			playerOne.afficheScoreSPM();
			System.out.println("Score PlusMoins : " + playerOne.scorePlusMoinsTotal());
			System.out.println("Score Total : " + playerOne.scoreTotal());
		}		
	}
	public static boolean existe()
	{
		return existe;	
	}
	public static void ajoutePtsPendu(int score)
	{
		if(score >= 0)
		{
			playerOne.ajouteScoreSPd(score);
		}		
	}
	public static void ajoutePtsPlusMoins(int score)
	{
		if(score >= 0)
		{
			playerOne.ajouteScoreSPM(score);
		}
	}
	public static void afficheStatut()
	{
		assert(playerOne != null) : "L'utilisateur ne s'est pas connecté";
	}
}
