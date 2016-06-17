package source;

import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import fichier.Fichier;
import fichier.InOut;
import graph.Profil_Windows;
import graph.SimpleFenetre;

/**
 * 
 * @author vinsifroid
 * @since v1.0-gamma
 */
public class profilGestion {

	private static ResourceBundle LSD = challenge.getResbundle();
	public static boolean premierOuverture = true;
	public static boolean existe = false;
	public static boolean confExiste = false;
	public static Joueur playerOne = new Joueur();
	public static String name = "";
	public static Config playerConf = null;
	public static int language = 1;
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
		language = challenge.getLanguage();
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
			if(md==3 && !Fichier.fichierExiste("saves/", nom + ".sav"))
			{
				final String message = LSD.getString("prGe_gesti_msg");
				JOptionPane.showMessageDialog(null, message, "Avertissement", JOptionPane.WARNING_MESSAGE, null);
				return;
			}
			name = nom;
			playerOne.setName(nom);

			if(Fichier.fichierExiste("saves/",nom + ".sav" ))
			{
				existe = true;
				if(Fichier.fichierExiste("saves/", nom + ".cf"))
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
			new Profil_Windows();
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
		if(mode == 0)
		{
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
		}else if(mode == 1)
		{		
			int[] tab1 = playerOne.getScorePendu();
			int[] tab2 = playerOne.getScorePlusMoins();
			int[] tab3 = playerOne.getScoreMasterMind();
			int[] tab4 = playerOne.getScorePuissance4();

			SimpleFenetre SF = new SimpleFenetre("Profil -"+ name,18,2);

			SF.addJlabel(new JLabel(LSD.getString("prGe_affPr_msg1") + name));	SF.addJlabel(new JLabel(""));	

			SF.addJlabel(new JLabel(LSD.getString("prGe_affPr_msg2"))); SF.addJlabel(new JLabel(""));				
			SF.addJlabel(new JLabel(Integer.toString(tab1[1])));	SF.addJlabel(new JLabel(Integer.toString(tab1[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab1[3])));	SF.addJlabel(new JLabel(Integer.toString(tab1[4])));		
			SF.addJlabel(new JLabel(LSD.getString("prGe_affPr_msg3")));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scorePenduTotal())));

			SF.addJlabel(new JLabel(LSD.getString("prGe_affMe_msg4")));	SF.addJlabel(new JLabel(""));		
			SF.addJlabel(new JLabel(Integer.toString(tab2[1])));	SF.addJlabel(new JLabel(Integer.toString(tab2[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab2[3])));	SF.addJlabel(new JLabel(Integer.toString(tab2[4])));		
			SF.addJlabel(new JLabel(LSD.getString("prGe_affMe_msg5")));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scorePlusMoinsTotal())));

			SF.addJlabel(new JLabel(LSD.getString("prGe_affMe_msg6")));	SF.addJlabel(new JLabel(""));
			SF.addJlabel(new JLabel(Integer.toString(tab3[1])));	SF.addJlabel(new JLabel(Integer.toString(tab3[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab3[3])));	SF.addJlabel(new JLabel(Integer.toString(tab3[4])));
			SF.addJlabel(new JLabel(LSD.getString("prGe_affMe_msg7")));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scoreMasterMindTotal())));

			SF.addJlabel(new JLabel(LSD.getString("prGe_affMe_msg8")));	SF.addJlabel(new JLabel(""));
			SF.addJlabel(new JLabel(Integer.toString(tab4[1])));	SF.addJlabel(new JLabel(Integer.toString(tab4[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab4[3])));	SF.addJlabel(new JLabel(Integer.toString(tab4[4])));
			SF.addJlabel(new JLabel(LSD.getString("prGe_affMe_msg9"))); SF.addJlabel(new JLabel(Integer.toString(playerOne.scorePuissanceTotal())));

			SF.addJlabel(new JLabel(LSD.getString("prGe_affMe_msg10")));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scoreTotal())));
			SF.repaintSimpleF();
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
	private static void chargeProfil()
	{
		if(Fichier.fichierExiste("saves/", name + ".sav"))
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
			int [] scorePuissance =new int[10];
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
			for(int i=0;!(str.equals(".D"));i++)
			{
				str = fi.lire();
				if(!str.equals(".D"))
				{
					scoreMasterMind[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScoreMasterMind(scoreMasterMind);
			for(int i=0;!(str.equals(".end"));i++)
			{
				str = fi.lire();
				if(!str.equals(".end"))
				{
					scorePuissance[i] = Integer.parseInt(str);
				}				
			}
			playerOne.setScorePuissance4(scorePuissance);
			fi.fermer();
		}
	}

	public static void confDefautLoad()
	{
		playerConf = new Config("default");
		if(Fichier.fichierExiste("saves/", "default.properties"))
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

	public static String getName()
	{
		return playerOne.getName();
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