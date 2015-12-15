import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class profilGestion {

	public static boolean existe = false;
	public static Joueur playerOne = new Joueur();
	public static String name = "";
	public static int language = 1;
	public static void gestion()
	{		
		if(existe == false)
		{				
			language = challenge.getLanguage();
			if(language == 1){System.out.println("Quel est votre nom ?");}
			else{System.out.println("What is your name ?");}			
			name = InOut.Mot(InOut.getLine());
			playerOne.setName(name);
			saveProfil();
			existe = true;
		}
		else
		{
			chargeProfil();
			afficheProfil();
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

	public static void chargeProfil()
	{
		if(fichierExiste())
		{			
			String str = "";
			Fichier fi = new Fichier();
			fi.ouvrir(name + ".sav", "L");
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
	public static boolean fichierExiste()
	{
		BufferedReader bF = null;
		boolean bon = false;
		try{
			bF = new BufferedReader(new FileReader(name + ".sav"));
			bon = true;
		}catch(FileNotFoundException e){
			bon = false;
		}finally{
			try{
				bF.close();
			}catch(IOException e)
			{
				System.err.println(e.getMessage());
				System.exit(-1);
			}
		}
		return bon;
	}

}
