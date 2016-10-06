package source;

import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import fichier.FichierR;
import fichier.InOut;
import graph.Profil_Windows;

/**
 * 
 * @author vinsifroid
 * @since v1.0-gamma
 */
public class profilGestion {

	private static ResourceBundle LSD = challenge.getResbundle();
	private static boolean premierOuverture = true;
	private static boolean existe = false;
	private static boolean confExiste = false;
	public static Joueur playerOne = new Joueur();
	private static String name = "";
	public static Config playerConf = null;
	/**
	 * if mode == 0, it's the console mode that is activated
	 * if mode == 1, it's the GUI mode that is activated
	 */
	public static byte mode=0;

	/**
	 * Cette fonction gère les interraction principale avec le profil de l'utilisateur. Elle intervient tout particulièrement au début du
	 * programme, lorsqu'il il s'agit d'effectuer plusieurs étapes de maintenance
	 * @param md = 1 lorsque l'interface est en mode graphique, 2 quand c'est en mode console et 3 quand en mode graphique mais option "se connecter"
	 */
	public static void gestion(byte md)
	{		
		LSD = challenge.getResbundle();
		mode = md;
		if(md==3)
			mode = (byte)1;

		//pour la premiere ouverture de cette fonction dans ce programme
		if(premierOuverture)
		{			
			String nom = "";
			if(md == 1 || md == 3)
			{
				nom = demandeNomGUI();
			}else{
				nom = demandeNomConsole();
			}

			//Pour différencier les boutons 'se connecter' et 'creer un nouveau compte'
			while(md==3 && !InOut.fichierExiste("saves/", nom + ".sav"))
			{
				final String message = LSD.getString("prGe_gesti_msg");
				JOptionPane.showMessageDialog(null, message, "Avertissement", JOptionPane.WARNING_MESSAGE, null);
				if(md == 1 || md == 3)
				{
					nom = demandeNomGUI();
				}else{
					nom = demandeNomConsole();
				}
			}
			name = nom;
			playerOne.setName(nom);

			if(InOut.fichierExiste("saves/",nom + ".sav" ))
			{
				existe = true;
				if(InOut.fichierExiste("saves/", nom + ".cf"))
				{
					confExiste = true;
				}
			}
			playerConf = new Config(nom);
			if(confExiste)
			{
				gestionConfig(false);
			}
			else
			{
				gestionConfig(true);
			}
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
		}

		//Le menu affiché en fonction du mode initial

		if(md == 1 || md == 3)
		{
			Profil_Windows pw = new Profil_Windows();
			pw.isAlwaysOnTop();

		}else{
			menuGestion();
		}		
	}

	private static String demandeNomConsole()
	{
		String nom = "";
		do
		{
			System.out.println(LSD.getString("prGe_dNoCo_msg1"));		
			nom = InOut.Mot(InOut.getLine());
			if(nom.contains(" "))
			{
				System.out.println(LSD.getString("prGe_dNoCo_msg2"));
			}else if(nom.equals("default"))
			{
				System.out.println(LSD.getString("prGe_dNoCo_msg3"));
			}
		}while(nom.contains(" ") || nom.equals("default"));
		return nom;
	}

	private static String demandeNomGUI()
	{
		String nom = "";
		do
		{
			nom = (String) JOptionPane.showInputDialog(null, "Quel est votre nom ?", "Gestion de profil", JOptionPane.QUESTION_MESSAGE);
			if(nom.contains(" "))
			{
				final String messageE1 = LSD.getString("prGe_deNoGui_msg1"), titleE1 = LSD.getString("prGe_deNoGUI_msg2");
				JOptionPane.showMessageDialog(null, messageE1, titleE1, JOptionPane.WARNING_MESSAGE, null);        		
			}else if(nom.equals("default"))
			{
				final String messageE2 = LSD.getString("prGe_deNoGUI_msg3"), titleE2 = LSD.getString("prGe_deNoGUI_msg2");
				JOptionPane.showMessageDialog(null, messageE2, titleE2, JOptionPane.WARNING_MESSAGE, null);       		
			}
		}while(nom.contains(" ") || nom.equals("default"));
		return nom;
	}

	private static void menuGestion()
	{		
		byte choix = 0;
		do
		{
			afficheMenu();
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
				playerOne.afficheScoreSPU();
				break;
			case 6:				
				optionConfigurationMenu();
				break;
			case 7:
				premierOuverture = true;
				existe = false;
				confExiste = false;
				challenge.menuPrincipal();
				break;
			case 8:
				break;
			default:
				System.out.println(LSD.getString("prGe_meGest_msg"));
				break;
			}
		}while(choix != 8);
	}

	private static void afficheMenu()
	{
		System.out.println(LSD.getString("prGe_affMe_msg1") + name + " ####");
		System.out.println(playerOne.toString() + LSD.getString("prGe_affMe_msg2"));
		System.out.println(LSD.getString("prGe_affMe_msg3"));
		System.out.println(LSD.getString("prGe_affMe_msg4"));
		System.out.println(LSD.getString("prGe_affMe_msg5"));
		System.out.println(LSD.getString("prGe_affMe_msg6"));
		System.out.println(LSD.getString("prGe_affMe_msg7"));
		System.out.println(LSD.getString("prGe_affMe_msg8"));
		System.out.println(LSD.getString("prGe_affMe_msg9"));
		System.out.println(LSD.getString("prGe_affMe_msg10"));
		System.out.println();
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
	public static void ajouteScoreSPU(int score)
	{
		if(score >= 0)
		{
			playerOne.ajouteScoreSPU(score);
		}
	}
	/**
	 * Print the profile at screen
	 */
	public static void afficheProfil()
	{
		LSD = challenge.getResbundle();
		
		System.out.println(LSD.getString("prGe_affMe_msg2") + LSD.getString("prGe_affPr_msg1") + playerOne.getName());
		System.out.println(LSD.getString("prGe_affPr_msg2"));
		playerOne.afficheScoreSPd();
		System.out.println(LSD.getString("prGe_affPr_msg3") + playerOne.scorePenduTotal());
		System.out.println(LSD.getString("prGe_affMe_msg2") + LSD.getString("prGe_affPr_msg4") + LSD.getString("prGe_affMe_msg2"));
		playerOne.afficheScoreSPM();
		System.out.println(LSD.getString("prGe_affPr_msg5") + playerOne.scorePlusMoinsTotal());
		System.out.println(LSD.getString("prGe_affMe_msg2") + LSD.getString("prGe_affPr_msg6") + LSD.getString("prGe_affMe_msg2"));
		playerOne.afficheScoreSMM();
		System.out.println(LSD.getString("prGe_affPr_msg7") + playerOne.scoreMasterMindTotal());
		System.out.println(LSD.getString("prGe_affMe_msg2") + LSD.getString("prGe_affPr_msg8") + LSD.getString("prGe_affMe_msg2"));
		playerOne.afficheScoreSPU();
		System.out.println(LSD.getString("prGe_affPr_msg9") + playerOne.scorePuissanceTotal());
		System.out.println(LSD.getString("prGe_affPr_msg10") + playerOne.scoreTotal());
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
	private static void chargeProfil()
	{
		if(InOut.fichierExiste("saves/", name + ".sav"))
		{			
			String str = "";
			FichierR f = new FichierR("saves/" + name + ".sav");
			f.ouvrirFluxReader();
			str = f.lire();
			playerOne.setName(str);
			f.lire();
			int [] scorePendu = new int[10];
			int [] scorePlusMoins = new int[10];
			int [] scoreMasterMind = new int[10];
			int [] scorePuissance =new int[10];
			for(int i=0;!(str.equals(".B"));i++)
			{
				str = f.lire();
				if(!str.equals(".B"))
				{
					scorePendu[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScorePendu(scorePendu);
			for(int i=0;!(str.equals(".C"));i++)
			{
				str = f.lire();
				if(!str.equals(".C"))
				{
					scorePlusMoins[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScorePlusMoins(scorePlusMoins);
			for(int i=0;!(str.equals(".D"));i++)
			{
				str = f.lire();
				if(!str.equals(".D"))
				{
					scoreMasterMind[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScoreMasterMind(scoreMasterMind);
			for(int i=0;!(str.equals(".end"));i++)
			{
				str = f.lire();
				if(!str.equals(".end"))
				{
					scorePuissance[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScorePuissance4(scorePuissance);
			f.fermerFluxReader();
		}
	}

	public static void confDefautLoad()
	{
		playerConf = new Config("default");
		if(InOut.fichierExiste("saves/", "default.properties"))
		{
			playerConf.chargeConfig();
			playerConf.paramExo();				
		}		
	}

	public static void optionConfigurationMenu()
	{
		byte choix = 0;
		LSD = challenge.getResbundle();
		System.out.println("###CONFIGURATION MENU###");
		System.out.println(LSD.getString("prGe_optConfMe_msg1"));
		do{
			choix = InOut.getByte();
			switch(choix)
			{
			case 1:
				playerConf.getProp().list(System.out);
				break;
			case 2:
				gestionConfig(true);
				break;
			case 3:
				break;
			default:
				System.out.println(LSD.getString("prGe_optConfMe_msg2"));
				break;
			}
		}while(choix != 3);
	}

	/**
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

	public static String getName()
	{
		return playerOne.getName();
	}
	/**
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
	public static void setPremierOuverture(boolean arg)
	{
		premierOuverture = arg;
	}
	public static void setExiste(boolean arg)
	{
		existe = arg;
	}
	public static void setConfExiste(boolean arg)
	{
		confExiste = arg;
	}
}