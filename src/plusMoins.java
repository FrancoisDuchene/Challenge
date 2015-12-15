
 public class plusMoins {	
	 public static int limMax = 100;
	 public static byte joueurs = 1;

	 public static void menu() {
		 byte choix = 0;
		 do {
			 System.out.println("\tPlusMoins\n/////////////////////////////");
			 System.out.println("1.Jouer\n2.Option\n3.Quitter");
			 choix = InOut.getByte();

			 switch(choix) {
			 case 1 :
				 jeu();
				 break;
			 case 2 :
				 menuOption();
				 break;
			 default :
				 System.out.println("Veuillez choisir 1 ou 2 !");
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
			  System.out.println("Le nombre que vous recherchez est compris entre 0 et " + limMax + "\n");
		 }
		 else if (joueurs == 2) 
		 {
			 System.out.println("Joueur 1. Intoduisez une valeur pour le 2eme joueur :");

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
			 System.out.println("Essayez ! (-1 pour sortir)");
		     nbrUser = InOut.getInt();
		     if(nbrUser == -1) 
		     {break;}
		     if(nbrUser < nbrAleatoire) 
		     {
		    	 System.out.println("Plus");
		     }
		     else if(nbrUser > nbrAleatoire) 
		     {
		    	 System.out.println("Moins");
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
		    	 System.out.println("\tBRAVO !!!\n\nVous avez reussi en " + coup + " coups");
		     }

		 }
		 while(victoire == false);
		 ajouteScore(score);
	 }
	 public static void menuOption() {
		 byte choix = 0;
		 do {
			 System.out.println("************************\n\tOptions:\n************************");

			 System.out.println("1. Limites\n2. Joueurs\n3. Quitter");
			 choix = InOut.getByte();
			 switch(choix) {
			 case 1 :
				 option1();
				 break;
			 case 2 :
				 break;
			 default:
				 System.out.println("Veuillez indiquer 1, 2 ou 3 !");
				 break;
			 }
		 }
		 while(choix != 3);		 
	 }

	 public static void option1() {
		 System.out.println("Quelle serait la limite ?");
		 limMax = InOut.getInt();
		 System.out.println("La nouvelle limite est de " + limMax);
	 }
	 public static void option2()
	 {
		 System.out.println("Mode 1 ou 2 Joueurs ?");
		 joueurs = InOut.getByte();
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
}
