package source;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import fichier.InOut;
import graph.MThread;

/**
 * @author Vinsifroid && Bivisi
 * @version 1.0 delta
 * @since v1.0 alpha
 */
public class challenge
{
	/**
	 * 1 for french, 2 for english
	 */
	private static byte language = 1;
	private static ResourceBundle LSD = null;
	public static MThread musique = new MThread("Musique");
	public static MThread page = new MThread("PageP");
	public static boolean continuons = true;
	public static void main (String [] args)
	{
		profilGestion.confDefautLoad();
		Locale loc = null;
		switch(language)
		{
		case 1:
			loc = new Locale("fr", "BE");
			break;
		case 2:
			loc = new Locale("en");
			break;
		}
		LSD = ResourceBundle.getBundle("lang.challenge", loc);		
		HighScoreGestion.gestion();
		boolean mode_Affichage = messageAcceuil();
		musique.start();
		if(mode_Affichage)
		{
			page.start();
		}else{
			menuPrincipal();
		}
	}

	/**
	 * Cette fonction demande a l'utilisateur si il veut utiliser le programme en interface graphique
	 * ou en console
	 * @return true si c'est la GUI et false si c'est la console
	 */
	private static boolean messageAcceuil()
	{
		String[] options = new String[]{"GUI","Console"};
		String message = LSD.getString("chal_mesAc_msg");

		String choix = (String)JOptionPane.showInputDialog(null,message,
				"IMPORTANT",JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(choix.equals(options[0]))
		{
			return true;
		}else{
			return false;
		}
	}
	private static String connecte()
	{
		if(profilGestion.existe())
		{
			return LSD.getString("chal_conne_msg1");
		}
		else
		{
			return LSD.getString("chal_conne_msg2");
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
				profilGestion.gestion((byte)2);
				break;
			case 4:
				choseLanguage();
				break;
			case 5:
				HighScoreGestion.menuHighScore();
				break;
			case 6:
				if(continuons)
				{
					continuons = false;
				}else{
					continuons = true;
					musique = null;
					musique = new MThread("Musique");
					musique.start();
				}
				break;
			case 7:
				System.exit(0);
				break;
			default :
				System.out.println(LSD.getString("chal_mePri_msg"));
				break;
			}
		}while(choix != 7);
	}

	private static void menuJeu()
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
				System.out.println(LSD.getString("chal_meJeu_msg"));
				break;
			}
		}while(choix != 5);
	}
	private static void credit()
	{
		clear();		
		System.out.println(LSD.getString("chal_cred_msg1"));
		System.out.println(LSD.getString("chal_cred_msg2"));
		dormirSystem(4000);
	}
	private static void choseLanguage()
	{
		byte choix = 0;
		clear();
		System.out.println(LSD.getString("chLa_msg1"));

		do{
			choix = InOut.getByte();
			switch(choix)
			{
			case 1:
				language = 1;
				LSD = ResourceBundle.getBundle("lang.challenge", new Locale("fr", "BE"));
				System.out.println(LSD.getString("chal_chLa_msg2"));
				break;
			case 2:
				language = 2;
				LSD = ResourceBundle.getBundle("lang.challenge", new Locale("en"));
				System.out.println(LSD.getString("chal_chLa_msg2"));
				break;
			}
		}while(choix != 1 && choix != 2);
		if(!profilGestion.getPremierOuverture())
		{
			System.out.println(LSD.getString("chal_chLa_msg3"));
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
					System.out.println(LSD.getString("chal_chLa_msg4"));
					break;
				}
			}while(bleu < 2 && bleu > 1);
		}
	}
	/**
	 * This function just print the Menu at the screen
	 * @param i determine which menu print. 1 mean the main menu and 2 mans games menu
	 */
	private static void printMenuMessage(byte i)
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

			System.out.println(LSD.getString("chal_prMeMsg_msg1"));
			System.out.println(LSD.getString("chal_prMeMsg_msg2") + connecte());
			System.out.println(LSD.getString("chal_prMeMsg_msg3"));
		}else if(i==2){
			System.out.println(LSD.getString("chal_prMeMsg_msg4"));
			System.out.println(LSD.getString("chal_prMeMsg_msg5"));
		}
	}
	/**
	 * @return the current Language. 1 if french and 2 if english
	 */
	public static byte getLanguage()
	{
		return language;
	}
	public static void setLanguage(byte lang)
	{
		language = lang;
	}
	public static ResourceBundle getResbundle()
	{
		return LSD;
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