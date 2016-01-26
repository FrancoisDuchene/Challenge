
public class plusMoins {	
	public static int limMax = 100;
	public static byte joueurs = 1;
	public static byte language = 1;

	public static void menu() {
		byte choix = 0;
		language = challenge.getLanguage();
		do {
			if(language==1){
				System.out.println("\tPlusMoins\n/////////////////////////////");
				System.out.println("1. Jouer\n2. Options\n3. Quitter");
			}else{
				System.out.println("\tHighLow\n/////////////////////////////");
				System.out.println("1. Play\n2. Settings\n3. Quit");
			}

			choix = InOut.getByte();

			switch(choix) {
			case 1 :
				jeu();
				break;
			case 2 :
				menuOption();
				break;
			case 3 :
				break;
			default :
				if(language==1){System.out.println("Veuillez choisir 1, 2 ou 3 !");}
				else{System.out.println("Please chose 1,2 or 3 !");}				
				break;
			}
		}while(choix != 3);
	}
	public static void jeu() {
		int nbrAleatoire = 0;
		int nbrUser = 0;
		int coup = 0;
		int score = 110;
		byte ee = 0;
		boolean victoire = false;

		if(joueurs == 1) 
		{
			nbrAleatoire = nbrRandom() ;
			if(language==1){System.out.println("Le nombre que vous recherchez est compris entre 0 et " + limMax + "\n");}
			else{System.out.println("The number you are looking for is between 0 and " + limMax + "\n");}
			
		}
		else if (joueurs == 2) 
		{
			if(language==1){System.out.println("Joueur 1. Intoduisez une valeur pour le 2eme joueur :");}
			else{System.out.println("Player 1. Introduce a value for the second player :");}			

			nbrAleatoire = InOut.getInt();
			challenge.clear();
		}
		else
		{
			joueurs = 1;
		}
		do 
		{
			coup++;
			score = score-10;
			if(score < 0){score = 0;}
			if(language==1){System.out.println("Essayez ! (-1 pour sortir)");}
			else{System.out.println("Try ! (-1 to exit)");}
			
			nbrUser = InOut.getInt();
			if(nbrUser == -1) 
			{break;}
			if(nbrUser < nbrAleatoire) 
			{
				if(language==1){System.out.println("Plus");}
				else{System.out.println("More");}
				
			}
			else if(nbrUser > nbrAleatoire) 
			{
				if(language==1){System.out.println("Moins");}
				else{System.out.println("Less");}
			}
			switch(nbrUser) 
			{
			case -2:
				System.out.println("Bien essayé mais nan");
				System.out.println("\n          ;p");
				score += 2;
				break;
			case 0 :
				System.out.println("Vous etes nul !");
				challenge.dormirSystem(300);
				System.out.println("Ou vous voulez tricher...");
				ee++;
				score++;
				break;
			case 1 :
				System.out.println("Vous etes serieux ?!?");
				challenge.dormirSystem(300);
				System.out.println("Grosse feignasse !!!");
				ee++;
				score++;
				break;
			case 2 :
				System.out.println("On aime ce qui va de paire !!!");
				ee++;
				break;
			case 3 :
				System.out.println("TRIANGLE !!!");
				ee++;
				score++;
				break;
			case 4 :
				System.out.println("13 chinois !!!");
				challenge.dormirSystem(300);
				System.out.println("Le 4 en chinois se prononce \"si\" et est\nphonetiquement tres proche du mot \"mort\"...");
				ee++;
				break;
			case 8 :
				System.out.println("Tu aimes le porte-bonheur chinois ?");
				ee++;
				break;
			case 13 :
				System.out.println("Serions-nous superstitieux ?!");
				ee++;
				score++;
				break;
			case 42 :
				System.out.println("La réponse à la vie, à l'univers et à tout le reste\nn'est pas forcément la réponse à tout");
				if(nbrUser == nbrAleatoire) {
					System.out.println("\tIci oui ;p");
				}
				score +=2;
				break;
			case 69 :
				System.out.println("\tCochonne !");
				challenge.dormirSystem(300);
				System.out.println("\tPervers !");
				score++;
				break;
			case 666 :
				System.out.println("Espece de Sataniste de merde !!!");
				ee++;
				score++;
				break;
			}
			if(ee == 3) {
				System.out.println(ee);
				try {
					Thread.sleep(3000);
					System.out.println("         .          ");
					Thread.sleep(300);
					System.out.println("        / \\        ");
					Thread.sleep(300);
					System.out.println("       /   \\       ");    
					Thread.sleep(300);
					System.out.println("      /  O  \\      ");
					Thread.sleep(300);
					System.out.println("     /       \\     ");
					Thread.sleep(300);
					System.out.println("     =========      ");
					Thread.sleep(400);
					System.out.println("   ILLUMINATIS !!!  ");
					Thread.sleep(300);
					ee++;
					score += 7;
				}
				catch(Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
			if(nbrUser == nbrAleatoire) {
				victoire = true;
				if(language==1){
					System.out.println("\tBRAVO !!!\n\nVous avez reussi en " + coup + " coups");
					System.out.println("Votre score est de " + score);
				}else{
					System.out.println("\tBRAVO !!!\n\nYou win with " + coup + " try");
					System.out.println("Your Final score is " + score);
				}				
			}
		}
		while(victoire == false);
		if(profilGestion.existe())
		{ajouteScore(score);}
	}
	public static void menuOption() {
		byte choix = 0;
		do {
			if(language==1){
				System.out.println("************************\n\tOptions:\n************************");
				System.out.println("1. Limites\n2. Joueurs\n3. Quitter");
			}else{
				System.out.println("************************\n\tSettings:\n************************");
				System.out.println("1. Limits\n2. Players\n3. Quit");
			}
			
			choix = InOut.getByte();
			switch(choix) {
			case 1 :
				option1();
				break;
			case 2 :
				option2();
				break;
			case 3:
				break;
			default:
				if(language==1){System.out.println("Veuillez choisir 1, 2 ou 3 !");}
				else{System.out.println("Please chose 1,2 or 3 !");}
				break;
			}
		}
		while(choix != 3);		 
	}
	/**
	 * This is the option to chose the limit of the number 
	 */	
	public static void option1() {
		if(language==1){
			System.out.println("Quelle serait la limite ?");
			limMax = InOut.getInt();
			System.out.println("La nouvelle limite est de " + limMax);
		}else{
			System.out.println("What would be the limit ?");
			limMax = InOut.getInt();
			System.out.println("The new limit is " + limMax);
		}
		if(!profilGestion.getPremierOuverture())
		{
			profilGestion.gestionConfig(true);
		}
	}
	/**
	 * Option for choose the number of players
	 */
	public static void option2()
	{
		if(language==1){
			System.out.println("Mode 1 ou 2 Joueurs ?");
			joueurs = InOut.getByte();
			if(joueurs != 1 && joueurs != 2)
			{
				joueurs = 1;
			}
			System.out.println("Vous etes actuellement en mode " + joueurs + " joueurs");
		}else{
			System.out.println("Mod 1 or 2 players ?");
			joueurs = InOut.getByte();
			if(joueurs != 1 && joueurs != 2)
			{
				joueurs = 1;
			}
			System.out.println("You are currently in mode " + joueurs + " player");
		}
		if(!profilGestion.getPremierOuverture())
		{
			profilGestion.gestionConfig(true);
		}
	}
	/**
	 * this function add you score to the profil of the player
	 * @param a integer who represent the score to calculate a score
	 */
	public static void ajouteScore(int score)
	{
		if(score < 0)
		{
			score = 0;
		}
		profilGestion.ajoutePtsPlusMoins(score);
		profilGestion.saveProfil();
	}
	/**
	 * @return a random number between 0 and the file length
	 */
	public static int nbrRandom() {
		double n = Math.random();
		n = n*limMax;
		return Math.abs((int) n);
	}
	public static int getLimMax()
	{
		return limMax;
	}
	public static byte getJoueur()
	{
		return joueurs;
	}
	public static void setParam(int limMax_P, byte J)
	{
		limMax = limMax_P;
		joueurs = J;
	}
}
