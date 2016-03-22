
public class HighScoreGestion {
	
	private static HighScore HGpendu;
	private static HighScore HGplusMoins;
	private static HighScore HGmastermind;
	private static HighScore HGpuissance4;
	
	private static boolean premiereOuverture = true;
	
	/**
	 * 
	 * @param mode 1 pour pendu, 2 pour plusMoins, 3 pour MasterMind, 4 pour Puissance4
	 */
	public static void gestion(byte mode)
	{
		if(premiereOuverture)
		{
			
			premiereOuverture = false;
		}
	}

	public static void saveHighScore(byte mode)
	{
		
	}
	
	public static void chargeHighScore()
	{
		
	}
}
