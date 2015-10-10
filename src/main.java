import java.io.IOException;

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


public class Main
{
	public static int vies = 7;
	public static byte joueurs = 1;
		
	public static void main (String [] args)
	{
		  /* 
		   * ==========================
		   *    	LE PENDU
		   * ==========================
		   * 
		   * game to play "le pendu". Here is the main class were all the
		   * program is executed
		   * 
		   *There are actually 6 functions:
		   * 
		   * 1) main
		   * 2) jeu where the game is played
		   * 3) replaceCharAt subroutines of the game
		   * 4) menu
		   * 5) option1 to decide the number of lifes
		   * 6) option2 to decide the number of players
		   */
		
				menu();				
		
	}
	 /*
	  * subroutine to play the game
	  * @pre : number of lifes (n>0) and number of players (1 or 2)
	  * @post: play the game
	  */
	public static void jeu()
	{		
		// all the variables used
		String MotSecret = "zygote";	
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
			
			if(vies == 0)
			{
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t GAME OVER\n~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}						
		}
		
	}
	/*
	 * @pre -
	 * @post replace a char in a string in at a certain position
	 */
	public static String replaceCharAt(String s, char c, int pos)
	{
		return s.substring(0,pos) + c + s.substring(pos+1);
	}
	/*
	 * @pre -
	 * @post give a menu to user and then redirect him
	 */
	public static void menu()
	{
		byte choix = 0;				
		
		do
		{
		System.out.println("==========================\n\t LE PENDU \n==========================");
		System.out.println("1.Jouer \n2.Options \n3.Crédits \n4.Quitter");
			
		choix = TextIO.getByte();
		switch(choix)
		{
			case 1 : 
					System.out.println("\n C'est parti !");
					jeu();									
				break;
			case 2 :					
					menuOption();			
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
	//All of the following instructions are about the option menu
	public static void menuOption()
	{
		byte choix = 0;		
		do
		{
			System.out.println("************************\n\tOptions:\n************************");
			System.out.println("Voulez-vous Changer :\n1. le nombre de vies ?\n2. le nombre de joueurs ?");
			System.out.println("\n3. Retourner en arrière");
			choix = TextIO.getByte();
		
				switch(choix)
				{
				case 1:
					option1();
					break;
				case 2:
					option2();
					break;
				case 3:
					break;
				case 4:
					try {
						Dico.lectureMots();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					break;
				case 5:
					double nbrRandom = 1.0;
					nbrRandom = Math.random();
					System.out.println(nbrRandom);
					break;
				default:
					System.out.println("Veuillez indiquer 1,2 ou 3 !");
					break;
				}
		}while(choix != 3);
	}
	/*
	 * @pre -
	 * @post change the value of the member variable vies
	 */
	public static void option1()	//subroutines in the options to choose the number of life(42)
	{	
		System.out.println("Vous avez actuellement " + vies + " vies");
		System.out.println("Combien voulez vous de vies ?");
		vies = TextIO.getInt();		
		if(vies == 0)
		{
			vies = 1;
		}
	}
	/*
	 * @pre -
	 * @post change the value of the member variable joueurs
	 */
	public static void option2()
	{	
		System.out.println("Vous êtes actuellement en mode " + joueurs + " joueur(s)");
		System.out.println("1. 1 Player\n2. 2 Players");
		joueurs = TextIO.getByte();		
		if(joueurs != 1 || joueurs != 2)
		{
			joueurs = 1;
		}
	}
	
}
