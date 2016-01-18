import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class profilGestion {

	public static boolean premierOuverture = true;
	public static boolean existe = false;
	public static boolean confExiste = false;
	public static Joueur playerOne = new Joueur();
	public static String name = "";
	public static Config playerConf = null;
	public static int language = 1;

	public static void menuGestion()
	{
		afficheMenu();
		byte choix = 0;
		do
		{
			choix = InOut.getByte();
			switch(choix)
			{
			case 1:
				afficheProfil();
				break;
			case 2:
				playerOne.afficheScoreSPd();
				break;
			case 3:
				playerOne.afficheScoreSPM();
				break;
			case 4:
				playerOne.afficheScoreSMM();
				break;
			case 5:
				break;
			default:
				if(language == 1){System.out.println("Veuillez indiquer 1, 2, 3, 4, 5 ou 6 !");}
                else{System.out.println("Please indicate 1, 2, 3, 4, 5 or 6");}
				break;
			}
		}while(choix != 6);
	}
	public static void afficheMenu()
	{
		if(language == 1)
		{
			System.out.println("#### PROFIL -" + name + " ####");
			System.out.println(playerOne.toString() + "\n");
			System.out.println("1. Tableau des scores general");
			System.out.println("2. Score Pendu");
			System.out.println("3. Score PlusMoins");
			System.out.println("4. Score MasterMind");
			System.out.println("5. Configuration");
			System.out.println("6. Changer d'utilisateur");
			System.out.println("\n7. Quitter");
		}else if(language == 2)
		{
			System.out.println("#### PROFILE -" + name + " ####");
			System.out.println(playerOne.toString() + "\n");
			System.out.println("1. General HighScore");
			System.out.println("2. Hangman Score");
			System.out.println("3. HighLow Score");
			System.out.println("4. MasterMind Score");
			System.out.println("6. Change user");
			System.out.println("\n7. Quit");
		}
	}

	public static void gestion()
	{		
		//pour la premiere ouverture de cette fonction dans ce programme
		if(premierOuverture)
		{
			language = challenge.getLanguage();
			if(language == 1){System.out.println("Quel est votre nom ?");}
			else{System.out.println("What is your name ?");}		
			name = InOut.Mot(InOut.getLine());
			playerOne.setName(name);
			if(fichierExiste(".sav"))
			{
				existe = true;
				if(fichierExiste(".cf"))
				{
					confExiste = true;
				}
			}
			playerConf = new Config(name);
			premierOuverture = false;
		}
		//Si c'est la premiere fois qu'on cree le fichier de profil
		if(existe == false)
		{
			saveProfil();			
			existe = true;
		}
		//Si le fichier de profil existe deja
		else
		{
			chargeProfil();
			gestionConfig(false);
			afficheProfil();
		}		
		if(confExiste)
		{
			gestionConfig(false);
		}
		else
		{
			gestionConfig(true);
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
	 * 
	 * @return true if a profile is not created on this session and false if a player is already logged
	 */
	public static boolean getPremierOuverture()
	{
		return premierOuverture;
	}
	/**
	 * the function add the score of the HangmanGame
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
	 * the function add the score of the HighLowGame
	 * @param score is the score to add at the profile
	 */
	public static void ajoutePtsPlusMoins(int score)
	{
		if(score >= 0)
		{
			playerOne.ajouteScoreSPM(score);
		}
	}

	public static void ajoutePtsMasterMind(int score)
	{
		if(score >= 0)
		{
			playerOne.ajouteScoreSMM(score);
		}
	}
	/**
	 * Print the profile at screen
	 */
	public static void afficheProfil()
	{
		if(language == 1)
		{
			System.out.println("\nNom : " + playerOne.getName());
			System.out.println("Scores Pendu");
			playerOne.afficheScoreSPd();
			System.out.println("Score Pendu Total : " + playerOne.scorePenduTotal());
			System.out.println("\nScore Plus ou Moins\n");
			playerOne.afficheScoreSPM();
			System.out.println("Score PlusMoins : " + playerOne.scorePlusMoinsTotal());
			System.out.println("\nScore MasterMind\n");
			playerOne.afficheScoreSMM();
			System.out.println("Score MasterMind : " + playerOne.scoreMasterMindTotal());
			System.out.println("Score Total : " + playerOne.scoreTotal());
		}else{
			System.out.println("\nName : " + playerOne.getName());
			System.out.println("Hangmann Score");
			playerOne.afficheScoreSPd();
			System.out.println("Hangmann Total score : " + playerOne.scorePenduTotal());
			System.out.println("\nHighLow score\n");
			playerOne.afficheScoreSPM();
			System.out.println("HighLow Total score : " + playerOne.scorePlusMoinsTotal());
			System.out.println("\nMasterMind Score\n");
			playerOne.afficheScoreSMM();
			System.out.println("MasterMind Score : " + playerOne.scoreMasterMindTotal());
			System.out.println("Total Score : " + playerOne.scoreTotal());
		}
	}
	/**
	 * Save the player Profile with the score and the name
	 */
	public static void saveProfil()
	{
		playerOne.savePlayer();
	}

	/**
	 * Charge the player Profile
	 */
	public static void chargeProfil()
	{
		if(fichierExiste(".sav"))
		{			
			String str = "";
			Fichier fi = new Fichier();
			fi.ouvrir("saves/" + name + ".sav", "L",true);
			str = fi.lire();
			playerOne.setName(str);
			fi.lire();
			int [] scorePendu = new int[10];
			int [] scorePlusMoins = new int[10];
			int [] scoreMasterMind = new int[10];
			for(int i=0;!(str.equals(".B"));i++)
			{
				str = fi.lire();
				if(!str.equals(".B"))
				{
					scorePendu[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScorePendu(scorePendu);
			for(int i=0;!(str.equals(".C"));i++)
			{
				str = fi.lire();
				if(!str.equals(".C"))
				{
					scorePlusMoins[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScorePlusMoins(scorePlusMoins);
			for(int i=0;!(str.equals(".end"));i++)
			{
				str = fi.lire();
				if(!str.equals(".end"))
				{
					scoreMasterMind[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScoreMasterMind(scoreMasterMind);
			fi.fermer();
		}
	}
	public static boolean fichierExiste(String extension)
	{
		BufferedReader vador = null;
		boolean bon = false;
		try{
			String filename = name + extension;
			if(extension.contains(".sav"))	// pour savoir si c'est dans le dossier saves
			{
				filename = "saves/" + filename;
			}
			vador = new BufferedReader(new FileReader(filename));
			bon = true;
		}catch(FileNotFoundException e){
			bon = false;
		}finally{
			try{
				if(bon == true)
					vador.close();
			}catch(IOException e)
			{}
		}
		return bon;
	}
	/**
	 * 
	 * @param mode true pour sauvegarder et false pour charger
	 */
	public static void gestionConfig(boolean mode)
	{
		if(mode)
		{
			playerConf.paramInto();
			playerConf.saveConfig();
		}else
		{
			playerConf.chargeConfig();
			playerConf.paramExo();
		}
	}

}
