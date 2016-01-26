/**
 * @author Vinsifroid && Bivisi
 * @version 1.1
 */
public class challenge
{
	private static byte language = 1;
	public static void main (String [] args)
	{
		/*
		 * ==========================
		 *        LE PENDU
		 * ==========================
		 *
		 * game to play "le pendu". Here is the main class were all the
		 * program is executed
		 *
		 *There are actually 6 functions:
		 *
		 * 1) main
		 * 2) jeu where the game is played
		 * 3) replaceCharAt subroutines of the game
		 * 4) menu
		 * 5) option1 to decide the number of lifes
		 * 6) option2 to decide the number of players
		 */

		//ChallengeInterface CI = new ChallengeInterface();
		//CI.launch();

		menuPrincipal();

	}
	public static String connecte()
	{
		if(profilGestion.existe())
		{
			if(language == 1)
			{
				return "connecte";
			}else{
				return "connected";
			}
		}
		else
		{
			if(language == 1)
			{
				return "non connecte";
			}else{
				return "not connected";
			}
		}
	}
	public static void menuPrincipal()
	{
		byte choix = 0;
		do
		{
			printMenuMessage((byte)1);

			choix = InOut.getByte();
			switch(choix)
			{
			case 1 :
				clear();
				menuJeu();
				break;
			case 2 :
				credit();
				break;
			case 3:
				profilGestion.gestion();
				break;
			case 4:
				choseLanguage();
				break;
			case 5:
				char c = 'n';
				if(language == 1){System.out.println("Etes-vous sur de vouloir quitter ? (o/n)");}
				else{System.out.println("Are-you sure do you want to quit ? (y/n)");}
				do{
					c = InOut.getChar();
					switch(c)
					{
					case 'o':
						if(language == 1){System.out.println("Au revoir alors !");}
						else{System.out.println("GoodBye Then !");}
						System.exit(0);
						break;
					case 'y':
						if(language == 1){System.out.println("Au revoir alors !");}
						else{System.out.println("Goodbye then !");}
						System.exit(0);
						break;
					case 'n':
						if(language == 1){System.out.println("J'etais sur que vous resteriez !");}
						else{System.out.println("I was sure you wanted to stay !");}
						break;
					}
				}while(c != 'n' && c != 'o' && c != 'y');
				break;
			default :
				if(language == 1){System.out.println("Veuillez indiquer 1, 2, 3, 4 ou 5 !");}
				else{System.out.println("Please Indicate 1, 2, 3, 4 or 5 !");}
				break;
			}

		}while(choix != 5);
	}

	public static void menuJeu()
	{
		byte choix = 0;
		do
		{
			clear();
			printMenuMessage((byte)2);
			choix = InOut.getByte();
			switch(choix)
			{
			case 1 :
				clear();
				pendu.menu();
				break;
			case 2 :
				clear();
				plusMoins.menu();
				break;
			case 3 :
				clear();
				Mastermind.menu();
				break;
			case 4 :
				clear();
				Puissance4.menu();
				break;
			case 5 :
				menuPrincipal();
				break;
			default :
				if(language == 1){System.out.println("Veuillez indiquer 1, 2, 3, 4 ou 5 !");}
				else{System.out.println("Please indicate 1, 2, 3, 4 or 5 !");}
				break;
			}
		}while(choix != 5);
	}
	public static void credit()
	{
		clear();
		if(language == 1){System.out.println("Developpeurs Principaux :\n=====================");}
		else{System.out.println("Principal Developper's crew");}
		System.out.println("Vinsifroid\nBivisi\nMelvinMajor\n\n\n");
		dormirSystem(4000);
	}
	public static void choseLanguage()
	{
		byte choix = 0;
		clear();
		if(language == 1){System.out.println("Quel Language preferez-vous ?\n1. Francais\n2. English");}
		else{System.out.println("What language would you like ?");}

		do{
			choix = InOut.getByte();
			switch(choix)
			{
			case 1:
				language = 1;
				System.out.println("La langue par default est maintenant le francais");
				break;
			case 2:
				language = 2;
				System.out.println("The Default language is now English");
				break;
			}
		}while(choix != 1 && choix != 2);
		if(!profilGestion.getPremierOuverture())
		{
			if(language==1){System.out.println("Voulez-vous enregistrer ce parametre comme defaut ? (1.oui 2.non)");}
			else{System.out.println("Want you save this parameter as default ? (1.yes/2.no)");}
			
			byte bleu = 0;
			do{
				bleu = InOut.getByte();
				switch(bleu)
				{
				case 1:
					profilGestion.gestionConfig(true);
					break;
				case 2:
					break;
				default:
					if(language==1){System.out.println("S'il vous plait indiquez 1 ou 2");}
					else{System.out.println("S'il vous plait indiquez 1 ou 2");}
					break;
				}
			}while(bleu < 2 && bleu > 1);
		}		
	}
	public static void printMenuMessage(byte i)
	{
		if(i==1)
		{
			System.out.println("#   $$$$  $$ $$   $$$   $$    $$    $$$$$  $   $   $$$$  $$$$$  #");
			System.out.println("#  $$     $$ $$  $$ $$  $$    $$    $$     $$  $  $$     $$     #");
			System.out.println("#  $      $$$$$  $$$$$  $$    $$    $$$$   $ $ $  $   $$ $$$    #");
			System.out.println("#  $$     $$ $$  $$ $$  $$    $$    $$     $  $$  $$   $ $$     #");
			System.out.println("#   $$$$  $$ $$  $$ $$  $$$$$ $$$$$ $$$$$  $   $   $$$$  $$$$$  #");

			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\t\tCHALLENGE - MINIGAMES");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			if(language == 1){
				System.out.println("\nMenu Principal :\n====================");
				System.out.println("\nStatut : " + connecte());
				System.out.println("\n1. Jouer\n2. Credits\n3. Profil\n4. Language\n5. Quitter");
			}else if (language == 2){
				System.out.println("\nMain Menu :\n====================");
				System.out.println("\nStatus : " + connecte() );
				System.out.println("\n1. Play\n2. Credits\n3. Profile\n4. Language\n5. Exit");
			}
		}else if(i==2){
			if(language == 1){
				System.out.println("LISTE DE JEUX\n^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("1. Pendu\n2. PlusMoins\n3. Mastermind\n4. Puissance 4\n5. Quitter");}
			else{
				System.out.println("LIST OF GAMES\n^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("1. HangMann\n2. HighLow\n3. Mastermind\n4. Connect 4\n5. Quit");
			}
		}
	}
	/**
	 * @return the current Language
	 */
	public static byte getLanguage()
	{
		return language;
	}
	public static void setLanguage(byte lang)
	{
		language = lang;
	}
	/**
	 * a function who "clear" the screen
	 */
	public static void clear(){
		for (int i = 0; i<25; i++)
		{System.out.println("\n");}
	}
	/**
	 * The system sleep in function of the milliseconds
	 * @param n the number of milliseconds
	 */
	public static void dormirSystem(long n)
	{
		try {
			Thread.sleep(n);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
