/*
 * @author Duchene Francois && Tascon Gutierrez Luis
 * @version 1.1
 * 
 * To ameliorate:
 * 
 * put a dico
 * to code the 2 players mode.. (option to have 2 players     already exist!!)
 * Elaborate a concrete option's menu
 */
/*
 * import java.util.*;
 * import java.lang.*; 
 */

public class main
{
<<<<<<< HEAD
	public static void main (String [] args)
	{
		/* 	
		 * Déclaration des variables
		 */
		
		/*	
		 * ==========================
		 * 			LE PENDU
		 * ==========================
		 * 
		 * jeu destiné à faire un pendu. Ici il s'agit de la classa mère où
		 * tout se déroulera 
		 * 
		 * Le programme se sépare actuellement en 3 fonctions
		 * 
		 * 1) main
		 * 2) jeu où se déroule le jeu
		 * 3) menu
		 */
				menu();				
		
	}
public static void jeu(int vies, byte joueurs)
{
	// all the variables used
	String MotSecret = "zigote";	
	String MotUser = "";
	String LettresFausses = "";
	char LettreUser = 'A';	
	int LongueurMot = MotSecret.length();
	boolean re = true, same = false;	
				
	if (joueurs == 2)
	{//loop to define the word
	      //ask the first player to enter a word
	}
	else
	{
	      //Take a word in a file
	}
	/*
	 * 	Texte dédié Interface utilisateur
	 */
	for (byte o = 0; o < LongueurMot; o++) // loop to have the same number of * than the letters in the "MotSecret"
	  {
	    MotUser = MotUser + "*";
	  }
	System.out.println("Trouvez le mot secret !");	
	
	while( vies != 0)
	{
		
		System.out.println(String.format("\n\n\nIl reste : %d vie(s)",(vies)));
		System.out.println(MotUser);
		System.out.println("Vous avez déjà proposé les lettres suivantes: " + LettresFausses);
					
		System.out.println("Ecrivez une lettre : ");
		same = false;
		re = true;
		
		
		LettreUser = TextIO.getChar();

		for(byte i=0; i < LongueurMot; i++) //loop to test all the string
		{	
			if (LettreUser == MotSecret.charAt(i)) //if it's ok => fonction to replace the chain
			{				
				MotUser = replaceCharAt(MotUser, LettreUser, i);
				re = false;
			//	changeChar(MotUser,i,LettreUser);
			}
		}
		for(int l=0; l < LettresFausses.length(); l++)
		{
			if(LettreUser == LettresFausses.charAt(l))
			{
				same = true;
			}
		}
		
		if(MotUser.equals(MotSecret))
		{
			System.out.println("Félicitation, vous avez trouvé le mot " + MotSecret + " avec encore " + (vies) + " vie(s) !!!");						
			break;
		}	
		
		if (re && !same) //if the letters was wrong, minus 1 life
		{
			vies--;
			LettresFausses = LettresFausses + LettreUser + " ";
		}
	}
	
}

public static String replaceCharAt(String s, char c, int pos)
{
	return s.substring(0,pos) + c + s.substring(pos+1);
}

public static void menu()
{
	byte choix = 0;		
	byte joueurs = 1;			
	int vies = 7;				
	
	do
	{
	System.out.println("==========================\n\t LE PENDU \n==========================");
	System.out.println("1.Jouer \n2.Options \n3.Crédits \n4.Quitter");
		
	choix = TextIO.getByte();
	switch(choix)
	{
		case 1 : 
				System.out.println("\n C'est parti !");
				jeu(vies, joueurs);									
			break;
		case 2 :
				System.out.println("************************\n\tOptions:\n************************");
				vies = option1();
				joueurs = option2();
				jeu(vies, joueurs);					
			break;
		case 3 :
			System.out.println("Developpement : F.Duchene && L.Tascon Gutierrez \nVersion du pendu 1.0");					
			break;
		case 4 :
			System.out.println("Program ended correctly");
			break;			
		default :
				System.out.println("Veuillez indiquer 1,2,3 ou 4 !");
			break;
	}
	
	}while(choix != 4);
}

public static int option1()	//subroutines in the options to choose the number of life
{
	int vies = 7;
	System.out.println("Combien voulez vous de vies ?");
	vies = TextIO.getInt();
	return vies;
>>>>>>> d0bad613a85b754d31f19c348bc61a32611ab7d3
}

public static byte option2(){
    byte joueurs = 1;
    System.out.println("1. 1 Player\n2. 2 Players");
    joueurs = TextIO.getByte();
    if (joueurs == 1){return 1;}
    else {return 2;}
}
 
}
