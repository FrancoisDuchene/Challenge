package source;
import exceptions.INVALID_MODE;
import fichier.Fichier;
import fichier.InOut;

public class HighScoreGestion {


	private static byte language =1;

	private static HighScore HGpendu;
	private static HighScore HGplusMoins;
	private static HighScore HGmastermind;
	private static HighScore HGpuissance4;

	private static boolean premiereOuverture = true;

	public static void gestion()
	{
		if(premiereOuverture)
		{
			language = challenge.getLanguage();
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

			if(!(Fichier.fichierExiste("saves/", "HighScore/pendu.hg"))){
				HGpendu.save();
			}else{
				HGpendu.charge();
			}

			if(!(Fichier.fichierExiste("saves/", "HighScore/plusMoins.hg"))){
				HGplusMoins.save();
			}else{
				HGplusMoins.charge();
			}

			if(!(Fichier.fichierExiste("saves/", "HighScore/mastermind.hg"))){
				HGmastermind.save();
			}else{
				HGmastermind.charge();
			}

			if(!(Fichier.fichierExiste("saves/", "HighScore/puissance4.hg"))){
				HGpuissance4.save();
			}else{
				HGpuissance4.charge();
			}				
			premiereOuverture = false;
		}
	}
	public static void menuHighScore()
	{
		language = challenge.getLanguage();
		byte choix = 0;		

		do
		{
			if(language==1)
			{
				System.out.println("\nQuel Highscore souhaiteriez vous consulter ?\n"
						+ "1. Pendu\n"
						+ "2. PlusMoins\n"
						+ "3. Mastermind\n"
						+ "4. Puissance4\n"
						+ "\n0. Retour\n");
			}else{
				System.out.println("\nWhich HighScore do you want to see ?\n"
						+ "1. Hangman\n"
						+ "2. HighLow\n"
						+ "3. Mastermind\n"
						+ "4. Connect4"
						+ "\n0. Return\n");
			}
			choix = InOut.getByte();
			switch(choix)
			{
			case 0:
				break;
			case 1:
				HGpendu.afficheHighScore();
				break;
			case 2:
				HGplusMoins.afficheHighScore();
				break;
			case 3:
				HGmastermind.afficheHighScore();
				break;
			case 4:
				HGpuissance4.afficheHighScore();
				break;
			default:
				if(language==1){ System.out.println("Entrez une valeur entre 0 et 4 !");}
				else{System.out.println("Enter a value between 0 and 4 !");}
				break;
			}
		}while(choix!=0);		
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