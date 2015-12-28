
public class pendu {

	public static short vies = 7;
	public static byte joueurs = 1;   

	/*
	 * subroutine to play the game
	 * @pre : number of lifes (n>0) and number of players (1 or 2)
	 * @post: play the game
	 */
	public static void jeu()
	{   
		int score = 0;
		if(joueurs == 1)
		{
			score = UnJoueur();
			if(profilGestion.existe()) // on verifie que le profil existe deja
			{
				ajouteScore(score);
			}
		}
		else if(joueurs == 2)
		{
			score = choix2Jr();
			if(profilGestion.existe()) // on verifie que le profil existe deja
			{
				ajouteScore(score);
			}
		}
		profilGestion.saveProfil();
	}
	public static int choix2Jr()
	{
		byte choix = 0;
		do
		{
			System.out.println("\nDans le mode deux joueurs, soit\n1. l'un des joueurs choisit le mot à chercher pour l'autre");
			System.out.println("2. ou alors les deux joueurs jouent simultanément\n\nQue préferez-vous ?");
			System.out.println("(3 pour sortir)");
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
	public static int UnJoueur()
	{
		String MotSecret = "zygote";
		String MotUser = "";
		String LettresFausses = "";
		char LettreUser = 'a';  
		int LongueurMot = MotSecret.length();
		short vies_tmp = vies;
		boolean re = true, same = false;    

		if (joueurs == 2)
		{//condition to define the word
			System.out.println("Le joueur 1 peut entrer un mot\nNe pas mettre d'accent!!");
			MotSecret = InOut.getWord();
			challenge.clear();
			System.out.println("Le joueur 2 doit essayer de deviner le mot rentre par le joueur 1");
		}
		else
		{ 
			MotSecret = dico();
		}
		MotSecret.toLowerCase();
		LongueurMot = MotSecret.length();
		/*
		 *  Texte dédié Interface utilisateur
		 */
		MotUser = remplaceEtoiles(MotSecret);
		System.out.println("Trouvez le mot secret !");  

		while( vies_tmp != 0)
		{           
			challenge.clear();
			penduExt.pendre(vies_tmp);
			System.out.println(String.format("\n\n\nIl reste : %d vie(s)",(vies_tmp)));
			System.out.println(MotUser);
			System.out.println("Vous avez déjà proposé les lettres suivantes: " + LettresFausses);

			System.out.println("Ecrivez une lettre : ");
			same = false;
			re = true;          

			LettreUser = InOut.getChar();
			//If ASCII of letter is between 65 and 90 it's an UpperCase so we need to convert in a LowerCase
			LettreUser = minuscule(LettreUser);

			for(byte i=0; i < LongueurMot; i++) //loop to test all the string
			{   
				if (LettreUser == MotSecret.charAt(i)) //if it's ok => fonction to replace the chain
				{               
					MotUser = replaceCharAt(MotUser, LettreUser, i);
					re = false;
					//  changeChar(MotUser,i,LettreUser);
				}
			}
			for(int l=0; l < LettresFausses.length(); l++) // String with the wrong letters
			{
				if(LettreUser == LettresFausses.charAt(l))
				{
					same = true;
				}
			}

			if(victoire(MotUser, MotSecret, vies_tmp)) 
			{
				return calculScore(MotSecret.length(), vies_tmp);
			}

			if (re && !same) //if the letters was wrong and different of the wrong letters's string, minus 1 life
			{
				vies_tmp--;
				LettresFausses = LettresFausses + LettreUser + " ";
			}

			if(vies_tmp == 0)
			{
				challenge.clear();
				penduExt.pendre(vies_tmp);
				System.out.println("Il fallait trouver : " + MotSecret + "\n");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t GAME OVER\n~~~~~~~~~~~~~~~~~~~~~~~~~~");
				challenge.dormirSystem(2500);
				return 0;
			}
		}
		return 0;
	}
	public static int DeuxJoueur()
	{
		String MotSecret1 = "bite";
		String MotSecret2 = "couille";
		String motUser1 = "";
		String motUser2 = "";
		String LettresFausses1 = "";
		String LettresFausses2 = "";

		char LettreUser = 'a';       
		short vies_tmp1 = vies;
		short vies_tmp2 = vies;
		boolean re = true, same = false;

		System.out.println("Joueur 1");
		challenge.dormirSystem(2500);
		System.out.println("Veuillez indiquer le mot à chercher pour le joueur 2");
		MotSecret1 = InOut.Mot(InOut.getLine());
		challenge.clear();

		System.out.println("Joueur 2");
		challenge.dormirSystem(2500);
		System.out.println("Veuillez indiquer le mot à chercher pour le joueur 1");
		MotSecret2 = InOut.getWord();
		challenge.clear();

		MotSecret1.toLowerCase();
		MotSecret2.toLowerCase();

		motUser1 = remplaceEtoiles(MotSecret1);
		motUser2 = remplaceEtoiles(MotSecret2);

		while(vies_tmp1 != 0 || vies_tmp2 != 0)
		{		   
			boolean perdu = false;
			if(perdu == false)
			{
				//PLAYER 1

				challenge.clear();
				penduExt.pendre(vies_tmp1);
				System.out.println("JOUEUR 1");
				System.out.println(String.format("\n\n\nIl reste : %d vie(s)",(vies_tmp1)));
				System.out.println(motUser1);
				System.out.println("Vous avez déjà proposé les lettres suivantes: " + LettresFausses1);

				System.out.println("Ecrivez une lettre : ");
				same = false;
				re = true;          

				LettreUser = InOut.getChar();
				//If ASCII of letter is between 65 and 90 it's an UpperCase so we need to convert in a LowerCase
				LettreUser = minuscule(LettreUser);

				for(byte i=0; i < MotSecret1.length(); i++) //loop to test all the string
				{   
					if (LettreUser == MotSecret1.charAt(i)) //if it's ok => fonction to replace the chain
					{               
						motUser1 = replaceCharAt(motUser1, LettreUser, i);
						re = false;
						//  changeChar(MotUser,i,LettreUser);
					}
				}
				for(int l=0; l < LettresFausses1.length(); l++) // String with the wrong letters
				{
					if(LettreUser == LettresFausses1.charAt(l))
					{
						same = true;
					}
				}
				if(victoire(motUser1, MotSecret1, vies_tmp1)) 
				{
					return calculScore(MotSecret1.length(), vies_tmp1);
				}                       

				if (re && !same) //if the letters was wrong and different of the wrong letters's string, minus 1 life
				{
					vies_tmp1--;
					LettresFausses1 = LettresFausses1 + LettreUser + " ";
				}

				if(vies_tmp1 == 0)
				{
					perdu = true;
					challenge.clear();
					penduExt.pendre(vies_tmp1);	           	
					System.out.println("Il fallait trouver : " + MotSecret1 + "\n");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t GAME OVER\n~~~~~~~~~~~~~~~~~~~~~~~~~~");
					return 0;
				} 
			}

			if(perdu == false)
			{
				//PLAYER 2

				challenge.clear();
				penduExt.pendre(vies_tmp2);
				System.out.println("JOUEUR 2");
				System.out.println(String.format("\n\n\nIl reste : %d vie(s)",(vies_tmp2)));
				System.out.println(motUser2);
				System.out.println("Vous avez déjà proposé les lettres suivantes: " + LettresFausses2);

				System.out.println("Ecrivez une lettre : ");
				same = false;
				re = true;          

				LettreUser = InOut.getChar();
				//If ASCII of letter is between 65 and 90 it's an UpperCase so we need to convert in a LowerCase
				LettreUser = minuscule(LettreUser);

				for(byte i=0; i < MotSecret2.length(); i++) //loop to test all the string
				{   
					if (LettreUser == MotSecret2.charAt(i)) //if it's ok => fonction to replace the chain
					{               
						motUser2 = replaceCharAt(motUser2, LettreUser, i);
						re = false;
						//  changeChar(MotUser,i,LettreUser);
					}
				}
				for(int l=0; l < LettresFausses2.length(); l++) // String with the wrong letters
				{
					if(LettreUser == LettresFausses2.charAt(l))
					{
						same = true;
					}
				}
				if(victoire(motUser2, MotSecret2, vies_tmp2)) 
				{
					return calculScore(MotSecret2.length(), vies_tmp2);
				}                       

				if (re && !same) //if the letters was wrong and different of the wrong letters's string, minus 1 life
				{
					vies_tmp2--;
					LettresFausses2 = LettresFausses2 + LettreUser + " ";
				}

				if(vies_tmp2 == 0)
				{
					perdu = true;
					challenge.clear();
					penduExt.pendre(vies_tmp2);
					System.out.println("Il fallait trouver : " + MotSecret2 + "\n");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t GAME OVER\n~~~~~~~~~~~~~~~~~~~~~~~~~~");
					return 0;
				}
			}
		}		
		return 0;
	}

	/**
	 * 
	 * @param mU motUser is the Word gived by the User
	 * @param mS motSecret is the Word gived by the dictionary
	 * @param vies_tmp1 is the number of lifes of the player
	 * @return
	 */
	public static boolean victoire(String mU, String mS, short vies_tmp1)
	{
		if(mU.equals(mS))
		{
			challenge.clear();
			penduExt.pendre(vies_tmp1);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>BRAVO !!!<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println("Félicitation, vous avez trouvé le mot " + mU + " avec encore " + (vies_tmp1) + " vie(s) !!!");
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param MotSecret is the Word that the user must discover
	 * @return a String with the same number of character that the Secret Word with Stars *
	 */
	public static String remplaceEtoiles(String MotSecret)
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
	 * @return replace a char in a string in at a certain position
	 */
	public static String replaceCharAt(String s, char c, int pos)
	{
		return s.substring(0,pos) + c + s.substring(pos+1);
	}
	/**
	 * @return a String coming from the Dico.java
	 */
	public static String dico()
	{
		return Dico.lectureMots();
	}
	/**
	 *  give a menu to user and then redirect him
	 */
	public static void menu()
	{
		byte choix = 0; 

		do
		{
			System.out.println("==========================\n\t LE PENDU \n==========================");
			System.out.println("1.Jouer \n2.Options \n3.Quitter");

			choix = InOut.getByte();
			switch(choix)
			{
			case 1 : 
				System.out.println("\n C'est parti !");                   
				jeu();                                                   
				break;
			case 2 :
				menuOption();           
				break;           
			case 3 :               
				break;          
			default :
				System.out.println("Veuillez indiquer 1,2 ou 3 !");
				break;
			}

		}while(choix != 3);
	}
	//All of the following instructions are about the option menu
	/**
	 * print the settings menu and redirect with the choice of the player
	 */
	public static void menuOption()
	{
		byte choix = 0;     
		do
		{
			System.out.println("************************\n\tOptions:\n************************");
			System.out.println("Voulez-vous Changer :\n1. le nombre de vies ?\n2. le nombre de joueurs ?\n3. [Still in Dev]ajouter un mot au dico");
			System.out.println("\n4. Retourner en arrière");
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
				break;
			default:
				System.out.println("Veuillez indiquer 1,2,3 ou 4 !");
				break;
			}
		}while(choix != 4);
	}
	/**
	 * add the score to the playerProfile
	 * @param score is the score earned by the precedent game
	 */
	public static void ajouteScore(int score)
	{
		profilGestion.ajoutePtsPendu(score);
	}
	/**
	 * 
	 * @param lgTab set la longueur du mot qui fallait trouver
	 * @param nbrVies est le nombre de vies qui restaient à la fin de la partie
	 * @return le score final de la partie actuelle
	 */
	public static int calculScore(int lgTab, int nbrVies)
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
	/**
	 *  change the value of the member variable vies
	 */
	public static void option1()    //subroutines in the options to choose the number of life(42)
	{   
		System.out.println("Vous avez actuellement " + vies + " vies");
		System.out.println("Combien voulez vous de vies ?");
		vies = InOut.getShort();     
		if(vies == 0)
		{
			vies = 1;
		}
		else if(vies > 99)
		{
			vies = 99;
		}
	}
	/**
	 *  change the value of the member variable joueurs
	 */
	public static void option2()
	{   
		System.out.println("Vous êtes actuellement en mode " + joueurs + " joueur(s)");
		System.out.println("1. 1 Player\n2. 2 Players");
		joueurs = InOut.getByte();
	}
	/**
	 * add a word to the dictionary
	 */
	public static void option3()
	{    	
		System.out.println("Veuillez indiquer ci-dessous quel mot vous voudriez ajouter au dictionnaire");
		String mot = InOut.getWord();    	
		Dico.ecritureMot(mot);
	}        
	/**
	 * @param l Take a char
	 * @return the LowerCase of the char if it's an UpperCase and the char itself if it's a LowerCase.
	 */
	public static char minuscule(char l){
		if ((int)l <=90 && (int)l >= 65)
		{return (char)((int)l + 32);}
		else 
			return l;
	}
}