
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
			try
			{
				HGpendu = new HighScore((byte)1);
				HGplusMoins = new HighScore((byte)2);
				HGmastermind = new HighScore((byte)3);
				HGpuissance4 = new HighScore((byte)4);
			}
			catch(INVALID_MODE e)
			{
				System.err.println(e.getMessage());
			}

			if(!(profilGestion.fichierExiste(".hg", "HighScore/pendu")))
				HGpendu.save();
			if(!(profilGestion.fichierExiste(".hg", "HighScore/plusMoins")))
				HGplusMoins.save();
			if(!(profilGestion.fichierExiste(".hg", "HighScore/mastermind")))
				HGmastermind.save();
			if(!(profilGestion.fichierExiste(".hg", "HighScore/puissance4")))
				HGpuissance4.save();
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
