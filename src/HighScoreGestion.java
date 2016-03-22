
public class HighScoreGestion {

	private static HighScore HGpendu;
	private static HighScore HGplusMoins;
	private static HighScore HGmastermind;
	private static HighScore HGpuissance4;

	private static boolean premiereOuverture = true;

	public static void gestion()
	{
		if(premiereOuverture)
		{
			try{
				HGpendu = new HighScore((byte)1);
				HGplusMoins = new HighScore((byte)2);
				HGmastermind = new HighScore((byte)3);
				HGpuissance4 = new HighScore((byte)4);
			}catch(INVALID_MODE e)
			{System.err.println(e.getMessage());}
			
			// Ici on regarde si les fichiers de highscore existent deja sur le disque dur,
			// si oui, on les charge, c'est sense etre les plus recents
			// si non, on les cree

			if(!(profilGestion.fichierExiste(".hg", "HighScore/pendu"))){
				HGpendu.save();
			}else{
				HGpendu.charge();
			}
				
			if(!(profilGestion.fichierExiste(".hg", "HighScore/plusMoins"))){
				HGplusMoins.save();
			}else{
				HGplusMoins.charge();
			}
				
			if(!(profilGestion.fichierExiste(".hg", "HighScore/mastermind"))){
				HGmastermind.save();
			}else{
				HGmastermind.charge();
			}
				
			if(!(profilGestion.fichierExiste(".hg", "HighScore/puissance4"))){
				HGpuissance4.save();
			}else{
				HGpuissance4.charge();
			}				
			premiereOuverture = false;
		}
	}
	public static boolean ajouterValeur(byte mode, String nom, int valeur) throws INVALID_MODE
	{
		switch(mode)
		{
		case 1:
			if(HGpendu.addValeur(nom, valeur))
			{return true;}
			else{return false;}
		case 2:
			if(HGplusMoins.addValeur(nom, valeur))
			{return true;}
			else{return false;}
		case 3:
			if(HGmastermind.addValeur(nom, valeur))
			{return true;}
			else{return false;}
		case 4:
			if(HGpuissance4.addValeur(nom, valeur))
			{return true;}
			else{return false;}
			default:
				throw new INVALID_MODE("Erreur mode rentre incorrect");
		}
	}
}
