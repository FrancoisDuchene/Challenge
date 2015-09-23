import java.util.*;


public class main
{
	public static void main (String [] args)
	{
		/* 	
		 * Déclaration des variables
		 */
		byte choix = 1;
		
		/*	
		 * ==========================
		 * 			LE PENDU
		 * ==========================
		 * 
		 * jeu destiné à faire un pendu. Ici il s'agit de la classa mère où
		 * tout se déroulera 
		 */
		System.out.println("==========================\n\t LE PENDU \n==========================");
		System.out.println("1.Jouer \n2.Options \n3.Crédits \n4.Quitter");
		
		do
		{
		switch(choix)
		{
			case 1 : 
					System.out.println("\n C'est partit !");
					jeu();
				break;
			case 2 :
					System.out.println("\nMenu des Options");
				break;
			case 3 :
					System.out.println("Dev : f.Duchene");
				break;
			case 4 :
				break;
			default :
					System.out.println("Veuillez indiquer 1,2,3 ou 4 !");
				break;
		}
		
		}while(choix <= 0 || choix >= 5);
	}
	public static void jeu()
	{
		// Differentes variables utilisées
		String MotSecret = "fromage";	
		String MotUser = "un";
		Scanner sc = new Scanner(System.in);
		
		/*
		 * 	Texte dédié Interface utilisateur
		 */
		System.out.println("Trouvez le mot secret !");
		
		for(byte coup = 0; coup != 10; coup++ )
		{
			System.out.println("Il vous reste : " + (10-coup) + " coups pour trouver la solution");
			MotUser = sc.nextLine();
			
			if(MotUser.equals(MotSecret))
			{
				System.out.println("Félicitation, vous avez trouvé le mot " + MotSecret + " en " + coup + " coups !!!");	
				coup = 10;
				break;
			}		
		
		}
		
		
		System.out.println("Program ended correctly");
		
	}
	
	
}
