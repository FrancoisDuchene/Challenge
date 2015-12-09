/**
 * @author Vinsifroid && Bivisi
 * @version 1.2
 */
public class challenge
{ 
	private static int language = 1;
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
			return "connecte";
		}
		else
		{
			return "non connecte";
		}
	}    
	public static void menuPrincipal()
	{
		byte choix = 0;
		do
		{       
			System.out.println("#  $$$$  $$ $$   $$$   $$    $$    $$$$$  $   $   $$$$  $$$$$");
			System.out.println("# $$     $$ $$  $$ $$  $$    $$    $$     $$  $  $$     $$");
			System.out.println("# $      $$$$$  $$$$$  $$    $$    $$$$   $ $ $  $   $$ $$$");
			System.out.println("# $$     $$ $$  $$ $$  $$    $$    $$     $  $$  $$   $ $$");
			System.out.println("#  $$$$  $$ $$  $$ $$  $$$$$ $$$$$ $$$$$  $   $   $$$$  $$$$$");


			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\t\tCHALLENGE - MINIGAMES");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println((language == 1)?"\nMenu Principal :\n====================": "\nMain Menu :\n====================");
			System.out.println((language == 1)?"\nStatut : " + connecte():"\nStatus : " + connecte() );
			System.out.println((language == 1)?"\n1. Jouer\n2. Credits\n3. Profil\n4. Language\n5. Quitter":"\n1. Play\n. Credits\n3. Profile\n4. Language\n5. Exit");    

			choix = TextIO.getByte();
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
				System.out.print((language == 1)?"Etes-vous sur de vouloir quitter ? (o/n)":"Are-you sure do you want to quit ? (y/n)");
				do{
					c = TextIO.getChar();
					switch(c)
					{
					case 'o':
						System.out.println("Au revoir alors !");
						System.exit(0);
						break;
					case 'y':
						System.out.println("Goodbye then !");
						System.exit(0);
						break;
					case 'n':
						System.out.println((language == 1)?"J'etais sur que vous resteriez !":"I was sure you wanted to stay !");
						break;
					}
				}while(c != 'n' && c != 'o' && c != 'y');            
				break;
			default :
				System.out.println("Veuillez indiquer 1, 2, 3, 4 ou 5 !");
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
			System.out.println("LISTE DE JEUX\n^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("1. Pendu\n2. PlusMoins\n3. Mastermind\n4. Quitter");
			choix = TextIO.getByte();
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
			case 4 : 
				menuPrincipal();
				break;
			default :
				System.out.println("Veuillez indiquer 1,2 ou 3 !");
				break;
			}
		}while(choix != 3);
	}
	public static void credit()
	{
		clear();
		System.out.println("Developpeurs Principaux :\n=====================");
		System.out.println("Vinsifroid\nBivisi\nMelvinMajor\n\n\n");
	}
	public static void choseLanguage()
	{
		byte choix = 0;
		clear();
		System.out.println("Quel Language preferez-vous ?\n\t1. Francais\n2. English");
		do{
			choix = TextIO.getByte();
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
		}while(choix != 1 || choix != 2);    	
	}
	public static int getLanguage()
	{
		return language;
	}
	/**
	 * a function who "clear" the screen
	 */
	public static void clear(){
		for (int i = 0; i<25; i++)
		{System.out.println("\n");}
	}
	public static void dormirSystem(long n)
	{
		try
		{
			Thread.sleep(n);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
