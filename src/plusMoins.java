
 public class plusMoins {	
	 public static int limMax = 100;	 
	 
	 public static void menu()
	 {
		 byte choix = 0;
		 do
		 {
			 System.out.println("\tPlusMoins\n/////////////////////////////");
			 System.out.println("1.Jouer\n2.Quitter");
			 choix = TextIO.getByte();
			 
			 switch(choix)
			 {
			 case 1 :
				 jeu();
				 break;
			 case 2 :
				 break;
			 default :
				 System.out.println("Veuillez choisir 1 ou 2 !");
				 break;				
			 }
		 }while(choix != 2);
	 }
	 public static void jeu()
	 {				
		 int nbrAleatoire = 0;
		 int nbrUser = 0;
		 int coup = 0;
		 byte joueurs = 1;
		 boolean victoire = false;
		 
		 System.out.println("Mode 1 ou 2 Joueurs ???");
		 joueurs = TextIO.getByte();
		 
		 if(joueurs == 1)
		 {
			  nbrAleatoire = nbrRandom() ;
		 }
		 else if (joueurs == 2)
		 {
			 System.out.println("Joueur 1. Intoduisez une valeur pour Joueur 2");
			 nbrAleatoire = TextIO.getInt();
		 }		 
		 do
		 {
			 coup++;
			 System.out.println("Essayez !");
		     nbrUser = TextIO.getInt();
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
		     case 13 :
		    	 System.out.println("Serions-nous superstitieux ?!");
		    	 break;
		     case 42 :
		    	 System.out.println("La réponse à la vie, à l'univers et à tout le reste\nn'est pas forcément la réponse à tout");
		    	 if(nbrUser == nbrAleatoire)
		    	 {System.out.println("\tIci oui ;p");}
		    	 break;
		     case 69 :
		    	 System.out.println("\tCochonne !");
		    	 break;
		     case 666 :
		    	 System.out.println("Espece de Sataniste de merde !!!");
		    	 break;
		     }
		     if(nbrUser == nbrAleatoire)
		     {
		    	 victoire = true;
		    	 System.out.println("\tBRAVO !!!\n\nVous avez reussi en " + coup + " coups");
		     }
		    	 
		 }while(victoire == false);
		 
	 }
	 public static int nbrRandom()
	 {
		 double n = Math.random();
		 n = n*limMax;
		 return Math.abs((int) n);
	 }	 
}
