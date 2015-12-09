
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
			System.out.println("Score Pendu Total : " + playerOne.scorePenduTotal());
			System.out.println("\nScore Plus ou Moins\n");
			playerOne.afficheScoreSPM();
			System.out.println("Score PlusMoins : " + playerOne.scorePlusMoinsTotal());
			System.out.println("Score Total : " + playerOne.scoreTotal());
		}		
	}
	/**
	 * 
	 * @return true if the player profile already exist and false if not
	 */
	public static boolean existe()
	{
		return existe;	
	}
	/**
	 * the fonction add the score of the HangmanGame
	 * @param score is the score to add at the profile
	 */
	public static void ajoutePtsPendu(int score)
	{
		if(score >= 0)
		{
			playerOne.ajouteScoreSPd(score);
		}		
	}
	/**
	 * the fonction add the score of the HighLowGame
	 * @param score is the score to add at the profile
	 */
	public static void ajoutePtsPlusMoins(int score)
	{
		if(score >= 0)
		{
			playerOne.ajouteScoreSPM(score);
		}
	}
	/**
	 * Save the player Profile with the score and the name
	 */
	public static void saveProfile()
	{
		playerOne.savePlayer();
	}

	public static void afficheStatut()
	{
		assert(playerOne != null) : "L'utilisateur ne s'est pas connecté";
	}

}
