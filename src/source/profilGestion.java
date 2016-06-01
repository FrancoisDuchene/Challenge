package source;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import fichier.Fichier;
import fichier.InOut;
import graph.Profil_Windows;
import graph.SimpleFenetre;

public class profilGestion {

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
				JOptionPane.showMessageDialog(null, "Cet utilisateur n'existe pas !", "Avertissement", JOptionPane.WARNING_MESSAGE, null);
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
			if(language == 1){System.out.println("Quel est votre nom ?");}
			else{System.out.println("What is your name ?");}		
			nom = InOut.Mot(InOut.getLine());
			if(nom.contains(" "))
			{
				if(language == 1){System.out.println("Veuillez a ne pas introduire d'espace");}
				else{System.out.println("Please don't put space in your name");}
			}else if(nom.equals("default"))
			{
				if(language == 1){System.out.println("Veuillez choisir un autre nom d'utilisateur que 'default' !");}
				else{System.out.println("Please choose another username than 'default' !");}
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
				if(language==1){final String messageE1 = "Désolé mais votre nom ne peut contenir d'espace !", titleE1 = "Erreur";
				JOptionPane.showMessageDialog(null, messageE1, titleE1, JOptionPane.WARNING_MESSAGE, null);}
				else{final String messageE1 = "Sorry but your name cannot contain space !", titleE1 = "Error";
				JOptionPane.showMessageDialog(null, messageE1, titleE1, JOptionPane.WARNING_MESSAGE, null);}        		
			}else if(nom.equals("default"))
			{
				if(language==1){final String messageE2 = "Désolé mais votre nom ne peut peut pas être 'default'", titleE2 = "Erreur";
				JOptionPane.showMessageDialog(null, messageE2, titleE2, JOptionPane.WARNING_MESSAGE, null);}
				else{final String messageE2 = "Sorry but your name cannot be 'default'", titleE2 = "Error";
				JOptionPane.showMessageDialog(null, messageE2, titleE2, JOptionPane.WARNING_MESSAGE, null);}        		
			}
		}while(nom.contains(" ") || nom.equals("default"));
		return nom;
	}

	public static void menuGestion()
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
				if(language == 1){System.out.println("Veuillez indiquer 1, 2, 3, 4, 5, 6 ou 7 !");}
				else{System.out.println("Please indicate 1, 2, 3, 4, 5, 6 ou 7 !");}
				break;
			}
		}while(choix != 8);
	}
	
	public static void afficheMenu()
	{
		if(language == 1)
		{
			System.out.println("\n#### PROFIL - " + name + " ####");
			System.out.println(playerOne.toString() + "\n");
			System.out.println("1. Tableau des scores general");
			System.out.println("2. Score Pendu");
			System.out.println("3. Score PlusMoins");
			System.out.println("4. Score MasterMind");
			System.out.println("5. Score Puissance4");
			System.out.println("6. Configuration");
			System.out.println("7. Changer d'utilisateur");
			System.out.println("\n8. Quitter");
		}else if(language == 2)
		{
			System.out.println("#### PROFILE - " + name + " ####");
			System.out.println(playerOne.toString() + "\n");
			System.out.println("1. General HighScore");
			System.out.println("2. Hangman Score");
			System.out.println("3. HighLow Score");
			System.out.println("4. MasterMind Score");
			System.out.println("5. Puissance4 Score");
			System.out.println("6. Configuration");
			System.out.println("7. Change user");
			System.out.println("\n8. Quit");
		}
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
		if(mode == 0)
		{
			if(language == 1)
			{
				System.out.println("\nNom : " + playerOne.getName());
				System.out.println("Scores Pendu");
				playerOne.afficheScoreSPd();
				System.out.println("Score Pendu Total : " + playerOne.scorePenduTotal());
				System.out.println("\nScore Plus ou Moins\n");
				playerOne.afficheScoreSPM();
				System.out.println("Score PlusMoins Total : " + playerOne.scorePlusMoinsTotal());
				System.out.println("\nScore MasterMind\n");
				playerOne.afficheScoreSMM();
				System.out.println("Score MasterMind Total : " + playerOne.scoreMasterMindTotal());
				System.out.println("\nScore Puissance4\n");
				playerOne.afficheScoreSPU();
				System.out.println("Score Puissance4 Total : " + playerOne.scorePuissanceTotal());
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
				System.out.println("\nPuissance4 Score\n");
				playerOne.afficheScoreSPU();
				System.out.println("Puissance4 Score : " + playerOne.scorePuissanceTotal());
				System.out.println("Total Score : " + playerOne.scoreTotal());
			}
		}else if(mode == 1)
		{		
			int[] tab1 = playerOne.getScorePendu();
			int[] tab2 = playerOne.getScorePlusMoins();
			int[] tab3 = playerOne.getScoreMasterMind();
			int[] tab4 = playerOne.getScorePuissance4();
			
			SimpleFenetre SF = new SimpleFenetre("Profil -"+ name,18,2);
			
			SF.addJlabel(new JLabel("Nom : " + name));	SF.addJlabel(new JLabel(""));	
			
			SF.addJlabel(new JLabel("Score Pendu")); SF.addJlabel(new JLabel(""));				
			SF.addJlabel(new JLabel(Integer.toString(tab1[1])));	SF.addJlabel(new JLabel(Integer.toString(tab1[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab1[3])));	SF.addJlabel(new JLabel(Integer.toString(tab1[4])));		
			SF.addJlabel(new JLabel("Score Pendu total : "));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scorePenduTotal())));
			
			SF.addJlabel(new JLabel("Score Plus ou Moins"));	SF.addJlabel(new JLabel(""));		
			SF.addJlabel(new JLabel(Integer.toString(tab2[1])));	SF.addJlabel(new JLabel(Integer.toString(tab2[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab2[3])));	SF.addJlabel(new JLabel(Integer.toString(tab2[4])));		
			SF.addJlabel(new JLabel("Score PlusMoins total : "));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scorePlusMoinsTotal())));
			
			SF.addJlabel(new JLabel("Score Mastermind"));	SF.addJlabel(new JLabel(""));
			SF.addJlabel(new JLabel(Integer.toString(tab3[1])));	SF.addJlabel(new JLabel(Integer.toString(tab3[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab3[3])));	SF.addJlabel(new JLabel(Integer.toString(tab3[4])));
			SF.addJlabel(new JLabel("Score Mastermind total : "));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scoreMasterMindTotal())));
			
			SF.addJlabel(new JLabel("Puissance 4"));	SF.addJlabel(new JLabel(""));
			SF.addJlabel(new JLabel(Integer.toString(tab4[1])));	SF.addJlabel(new JLabel(Integer.toString(tab4[2])));
			SF.addJlabel(new JLabel(Integer.toString(tab4[3])));	SF.addJlabel(new JLabel(Integer.toString(tab4[4])));
			SF.addJlabel(new JLabel("Score Puissance 4 total : ")); SF.addJlabel(new JLabel(Integer.toString(playerOne.scorePuissanceTotal())));
			
			SF.addJlabel(new JLabel("Total Score : "));	SF.addJlabel(new JLabel(Integer.toString(playerOne.scoreTotal())));
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
	public static void chargeProfil()
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
		System.out.println("###CONFIGURATION MENU###");
		System.out.println("\n1. Listing des propriétés 2. Charger la configuration 3. Sortir");
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
				if(language==1){System.out.println("Veuillez indiquer 1, 2 ou 3 !");}
				else{System.out.println("Please enter 1, 2 or 3 !");}
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