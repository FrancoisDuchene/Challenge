package source;
import java.util.ResourceBundle;

import exceptions.INVALID_MODE;
import fichier.InOut;
import graph.Pendu_Window;


/**
 * 
 * @author vinsifroid and Bivisi
 * @since v1.0 alpha
 */
public class pendu {

	private static ResourceBundle LSD = challenge.getResbundle();
	private static short vies = 7;
	private static byte joueurs = 1;

	// Les Fonctions suivantes sont utilisees pour la naviguation et non pas
	// pour le fonctionnement du jeu en lui-même

	/**
	 * 
	 * @param gui true if in Graphic mode and false if in console mode
	 */
	public static void redirection(boolean gui)
	{
		if(gui)
		{
			//Pendu_Window pw = new Pendu_Window();
			//pw.setVisible(true);
		}else{
			menu();
		}
	}
	/**
	 *  give a menu to user and then redirect him
	 */
	public static void menu()
	{
		LSD = challenge.getResbundle();
		byte choix = 0; 

		do
		{
			System.out.println(LSD.getString("pen_menu_msg1"));
			challenge.dormirSystem(500);
			System.out.println(LSD.getString("pen_menu_msg2"));

			choix = InOut.getByte();
			switch(choix)
			{
			case 1 : 
				System.out.println(LSD.getString("pen_menu_msg3"));    
				jeu();                                                   
				break;
			case 2 :
				menuOption();           
				break;           
			case 3 :               
				break;          
			default :
				System.out.println(LSD.getString("prGe_optConfMe_msg2"));
				break;
			}

		}while(choix != 3);
	}

	//All of the following instructions are about the option menu
	/**
	 * print the settings menu and redirect with the choice of the player
	 */
	private static void menuOption()
	{
		byte choix = 0;     
		do
		{
			System.out.println(LSD.getString("pen_menuO_msg1"));
			System.out.println(LSD.getString("pen_menuO_msg2")
					+ LSD.getString("pen_menuO_msg3"));
			System.out.println(LSD.getString("pen_menuO_msg4"));
			choix = InOut.getByte();

			switch(choix)
			{
			case 1:
				option1();
				break;
			case 2:
				option2();
				break;
			case 3:
				option3();
				break;
			case 4:
				option4();
				break;
			case 5:
				break;
			default:
				System.out.println(LSD.getString("pen_menuO_msg5"));
				break;
			}
		}while(choix != 5);
	}
	/**
	 *  change the value of the member variable vies
	 */
	private static void option1()    //subroutines in the options to choose the number of life(42)
	{   
		System.out.println((LSD.getString("pen_opt1_msg1") + vies + LSD.getString("pen_opt1_msg2")));
		System.out.println(LSD.getString("pen_opt1_msg3"));
		final short vie = InOut.getShort();
		setVies(vie);
	}
	/**
	 *  change the value of the member variable joueurs
	 */
	private static void option2()
	{   
		System.out.println(LSD.getString("pen_opt2_msg1") + joueurs + LSD.getString("pen_opt2_msg2"));
		System.out.println(LSD.getString("pen_opt2_msg3"));
		final byte joueur = InOut.getByte();
		setJoueurs(joueur);
	}
	/**
	 * add a word to the dictionary
	 */
	private static void option3()
	{       
		System.out.println(LSD.getString("pen_opt3_msg1"));
		String mot = InOut.getWord();       
		Dico.ecritureMot(mot);
	}
	private static void option4()
	{
		System.out.println(LSD.getString("pen_opt4_msg1")
				+ LSD.getString("pen_opt4_msg2")
				+ LSD.getString("pen_opt4_msg3"));
		byte choix=0;
		do{
			choix=InOut.getByte();
			if(choix!=1 && choix!=2)
			{
				System.out.println(LSD.getString("pen_opt4_mg4"));
			}
		}while(choix!=1 && choix!=2);
		Dico.setChoixDico(choix);
	}
	/**
	 * subroutine to play the game
	 * @pre : number of lifes (n>0) and number of players (1 or 2)
	 * @post: play the game
	 */
	private static void jeu()
	{   
		int score = 0;
		if(joueurs == 1)
		{
			score = UnJoueur();
			if(profilGestion.existe()) // on verifie que le profil existe deja
			{
				ajouteScore(score);
				try {
					HighScoreGestion.ajouterValeur((byte)1, profilGestion.getName(), score);
				} catch (INVALID_MODE e) {
					System.err.println(e.getMessage());
				}
				profilGestion.saveProfil();
			}
		}
		else if(joueurs == 2)
		{
			score = choix2Jr();
			if(profilGestion.existe()) // on verifie que le profil existe deja
			{
				ajouteScore(score);
				try {
					HighScoreGestion.ajouterValeur((byte)1, profilGestion.getName(), score);
				} catch (INVALID_MODE e) {
					System.err.println(e.getMessage());
				}
				profilGestion.saveProfil();
			}
		}
	}
	/**
	 * This function is determined for the choice of the two differents two-players mode
	 * @return the score of the player this game- 
	 */
	private static int choix2Jr()
	{
		byte choix = 0;
		do
		{
			System.out.println(LSD.getString("pen_choix2JR_msg1"));
			System.out.println(LSD.getString("pen_choix2JR_msg2"));
			System.out.println(LSD.getString("pen_choix2JR_msg3"));
			choix = InOut.getByte();
			switch(choix)
			{
			case 1:            
				return UnJoueur();
			case 2:
				return DeuxJoueur();
			}
		}while(choix !=3);   
		return 0;
	}
	/**
	 * Is the main game for the one player game
	 * @return the score of the game
	 */
	private static int UnJoueur()
	{
		String MotUser = "";
		String LettresFausses = "";
		char LettreUser = 'a';
		short vies_tmp = vies;
		boolean lettreDif = true; // Pour vérifier si c'est lettre fausse
		boolean sameLetter = false; // Pour vérifier si cette lettre fausse fait deja partie de la liste

		final String MotSecret = intro((byte)1);

		MotUser = remplaceEtoiles(MotSecret);
		System.out.println(LSD.getString("pen_UnJoueur_msg1"));  

		while( vies_tmp != 0)
		{           
			sameLetter = false; lettreDif = true;  

			LettreUser = interaction(vies_tmp, MotUser, LettresFausses, (byte)1);

			// On verifie si l'utilisateur ne veut pas sortir du programme
			if(LettreUser == '0')
				return 0;

			// Dans cette boucle, on loop pour verifier si une des lettres n'est pas correcte
			for(byte i=0; i < MotSecret.length(); i++) //loop to test all the string
			{   
				if (LettreUser == MotSecret.charAt(i)) //if it's ok => fonction to replace the chain
				{               
					MotUser = replaceCharAt(MotUser, LettreUser, i);
					lettreDif = false;
					//  changeChar(MotUser,i,LettreUser);
				}
			}

			if(victoire(MotUser, MotSecret, vies_tmp)) 
				return calculScore(MotSecret.length(), vies_tmp);			

			for(int l=0; l < LettresFausses.length(); l++) // String with the wrong letters
			{
				if(LettreUser == LettresFausses.charAt(l))
				{
					sameLetter = true;
				}
			}			

			if (lettreDif && !sameLetter) //if the letter was wrong and different of the wrong letters's string, minus 1 life
			{
				vies_tmp--;
				LettresFausses = LettresFausses + LettreUser + " ";
			}

			if(vies_tmp == 0)
				return gameOver(vies_tmp, MotSecret, (byte) 1);
		}
		return 0;
	}
	private static int DeuxJoueur()
	{
		String LettresFausses1 = "";
		String LettresFausses2 = "";

		char LettreUser = 'a';       
		short vies_tmp1 = vies;
		short vies_tmp2 = vies;
		boolean lettreDif = true;	// Pour vérifier si c'est lettre fausse
		boolean sameLetter = false; // Pour vérifier si cette lettre fausse fait deja partie de la liste

		final String MotSecret1 = intro((byte)2);
		final String MotSecret2 = intro((byte)3);

		String motUser1 = remplaceEtoiles(MotSecret1);
		String motUser2 = remplaceEtoiles(MotSecret2);
		boolean perdu = false;

		while(vies_tmp1 != 0 || vies_tmp2 != 0)
		{          
			if(perdu == false)
			{
				//PLAYER 1

				sameLetter = false;
				lettreDif = true;

				LettreUser = interaction(vies_tmp1, motUser1, LettresFausses1, (byte)2);

				if(LettreUser == '0')
				{
					return 0;
				}

				for(byte i=0; i < MotSecret1.length(); i++) //loop to test all the string
				{   
					if (LettreUser == MotSecret1.charAt(i)) //if it's ok => fonction to replace the chain
					{               
						motUser1 = replaceCharAt(motUser1, LettreUser, i);
						lettreDif = false;
						//  changeChar(MotUser,i,LettreUser);
					}
				}
				for(int l=0; l < LettresFausses1.length(); l++) // String with the wrong letters
				{
					if(LettreUser == LettresFausses1.charAt(l))
					{
						sameLetter = true;
					}
				}
				if(victoire(motUser1, MotSecret1, vies_tmp1)) 
					return calculScore(MotSecret1.length(), vies_tmp1);  

				if (lettreDif && !sameLetter) //if the letters was wrong and different of the wrong letters's string, minus 1 life
				{
					vies_tmp1--;
					LettresFausses1 = LettresFausses1 + LettreUser + " ";
				}

				if(vies_tmp1 == 0)
				{
					perdu = true;
					return gameOver(vies_tmp1, MotSecret1, (byte) 2);
				} 
			}

			if(perdu == false)
			{
				//PLAYER 2

				sameLetter = false; lettreDif = true;

				LettreUser = interaction(vies_tmp2, motUser2, LettresFausses2, (byte)3);

				if(LettreUser == '0')
				{
					return 0;
				}

				for(byte i=0; i < MotSecret2.length(); i++) //loop to test all the string
				{   
					if (LettreUser == MotSecret2.charAt(i)) //if it's ok => fonction to replace the chain
					{               
						motUser2 = replaceCharAt(motUser2, LettreUser, i);
						lettreDif = false;
						//  changeChar(MotUser,i,LettreUser);
					}
				}
				for(int l=0; l < LettresFausses2.length(); l++) // String with the wrong letters
				{
					if(LettreUser == LettresFausses2.charAt(l))
					{
						sameLetter = true;
					}
				}
				if(victoire(motUser2, MotSecret2, vies_tmp2)) 
					return calculScore(MotSecret2.length(), vies_tmp2);

				if (lettreDif && !sameLetter) //if the letters was wrong and different of the wrong letters's string, minus 1 life
				{
					vies_tmp2--;
					LettresFausses2 = LettresFausses2 + LettreUser + " ";
				}

				if(vies_tmp2 == 0)
				{
					perdu = true;
					return gameOver(vies_tmp2, MotSecret2, (byte) 3);
				}
			}
		}       
		return 0;
	}

	// Toutes les fonctions suivantes ssont utilisees lors d'une partie



	/**
	 * Cette fonction effectue les premieres operations d'une partie
	 * @param a is wich message to print
	 * @return le mot secret
	 */
	private static String intro(byte a)
	{
		String MotSecret = "zygote";
		if(a==1)
		{
			if(joueurs == 2)
			{
				System.out.println(LSD.getString("pen_intro_msg1"));
				MotSecret = InOut.getWord();
				challenge.clear();
				System.out.println(LSD.getString("pen_intro_msg2"));
			}
			else{
				MotSecret = Dico.lectureMots();
			}
		}else if(a==2){
			System.out.println(LSD.getString("pen_intro_msg3"));
			challenge.dormirSystem(2500);
			System.out.println(LSD.getString("pen_intro_msg4"));
			MotSecret = InOut.Mot(InOut.getWord());
			challenge.clear();
		}else{
			System.out.println(LSD.getString("pen_intro_msg5"));
			challenge.dormirSystem(2500);
			System.out.println(LSD.getString("pen_intro_msg6"));
			MotSecret = InOut.getWord();
			challenge.clear();
		}
		MotSecret.toLowerCase();
		return MotSecret;
	}

	/**
	 * 
	 * @param MotSecret is the Word that the user must discover
	 * @return a String with the same number of character that the Secret Word with Stars *
	 */
	private static String remplaceEtoiles(String MotSecret)
	{
		String MotUser = "";
		for (byte o = 0; o < MotSecret.length(); o++) // loop to have the same number of * than the letters in the "MotSecret"
		{
			if(MotSecret.charAt(o) == '-')
			{MotUser = MotUser + "-";}
			else {MotUser = MotUser + "*";}
		}
		return MotUser;
	}

	/**
	 * Methode qui effectue l'interaction avec l'utilisateur dans une partie
	 * @param vies_tmp le nombre de vies restantes
	 * @param MotUser le mot de l'utilisateur
	 * @param LettresFausses la liste de lettres fausses
	 * @param a correspond a quel type de message afficher
	 * @return le choix de l'utilisateur
	 */
	private static char interaction(short vies_tmp, String MotUser, String LettresFausses, byte a)
	{
		challenge.clear();
		penduExt.pendre(vies_tmp);
		if(a==2)
		{
			System.out.println(LSD.getString("pen_inter_msg1"));
		}else if(a==3)
		{
			System.out.println(LSD.getString("pen_inter_msg2"));
		}
		System.out.println(String.format("\n\n\n" + LSD.getString("pen_inter_msg3") + " " + "%d" + " " + LSD.getString("pen_inter_msg4"),(vies_tmp)));
		System.out.println(MotUser);
		System.out.println(LSD.getString("pen_inter_msg5") + " " + LettresFausses);
		System.out.println(LSD.getString("pen_inter_msg6"));

		final char LettreUser = InOut.getChar();

		//If ASCII of letter is between 65 and 90 it's an UpperCase so we need to convert in a LowerCase
		return minuscule(LettreUser); 
	}

	/**
	 * 
	 * @param mU motUser is the Word gived by the User
	 * @param mS motSecret is the Word gived by the dictionary
	 * @param vies_tmp is the number of lifes of the player
	 * @return
	 */
	private static boolean victoire(String mU, String mS, short vies_tmp)
	{
		if(mU.equals(mS))
		{
			challenge.clear();
			penduExt.pendre(vies_tmp);
			System.out.println(LSD.getString("pen_vict_msg1"));
			System.out.println(LSD.getString("pen_vict_msg2") + " " + mU + " " + LSD.getString("pen_vict_msg3") + " " + (vies_tmp) + " " + LSD.getString("pen_vict_msg4"));
			return true;
		}
		return false;
	}

	private static int gameOver(short vies_tmp, String MotSecret, byte a)
	{
		challenge.clear();
		penduExt.pendre(vies_tmp);
		if(a==2)
		{
			System.out.println(LSD.getString("pen_gaOv_msg1"));
		}else if(a==3)
		{
			System.out.println(LSD.getString("pen_gaOv_msg2"));
		}
		System.out.println(LSD.getString("pen_gaOv_msg3") + MotSecret + "\n");
		System.out.println(LSD.getString("pen_gaOv_msg4"));
		if(a==1)
			challenge.dormirSystem(2500);

		return 0;
	}

	/**
	 * @return replace a char in a string in at a certain position
	 */
	private static String replaceCharAt(String s, char c, int pos)
	{
		return s.substring(0,pos) + c + s.substring(pos+1);
	}

	/**
	 * @param l Take a char
	 * @return the LowerCase of the char if it's an UpperCase and the char itself if it's a LowerCase.
	 */
	private static char minuscule(char l){
		if ((int)l <=90 && (int)l >= 65)
		{return (char)((int)l + 32);}
		return l;
	}

	// Les fonctions suivantes sont a propos du calcul du score pour le profil

	/**
	 * add the score to the playerProfile
	 * @param score is the score earned by the precedent game
	 */
	private static void ajouteScore(int score)
	{
		profilGestion.ajoutePtsPendu(score);
	}
	/**
	 * 
	 * @param lgTab set la longueur du mot qui fallait trouver
	 * @param nbrVies est le nombre de vies qui restaient à la fin de la partie
	 * @return le score final de la partie actuelle
	 */
	private static int calculScore(int lgTab, int nbrVies)
	{
		if(lgTab < 5)
		{
			return (lgTab*nbrVies)*3;
		}
		else if(lgTab > 8)
		{
			return (lgTab*nbrVies)*2;
		}
		else
		{
			return lgTab*nbrVies;
		}      
	}

	// Les fonctions ci-dessous sont destinees a l'interaction de la classe avec les autres classes

	public static short getVies()
	{
		return vies;
	}
	public static byte getJoueurs()
	{
		return joueurs;
	}
	/**
	 * 
	 * @param vies_p 
	 * @param J le nombre de joueurs
	 */
	public static void setParam(short vies_p, byte J)
	{
		vies = vies_p;
		joueurs = J;
	}
	public static void setVies(short vie)
	{
		if(vie <= 0){
			vies = 1;
		}else if(vie > 26){
			vies = 26;
		}else{
			vies = vie;
		}
		if(!profilGestion.getPremierOuverture())
		{
			profilGestion.gestionConfig(true);
		}
	}
	public static void setJoueurs(byte J)
	{
		if(J>2){
			joueurs=2;
		}else if(J<1){
			joueurs=1;
		}else{
			joueurs = J;
		}
		if(!profilGestion.getPremierOuverture())
		{
			profilGestion.gestionConfig(true);
		}
	}
}