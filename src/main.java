
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
	public void jeu()
	{
		/*
		 * 	Texte dédié Interface utilisateur
		 */
		
	}
}
