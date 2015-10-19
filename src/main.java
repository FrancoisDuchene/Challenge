/*
 * @author Duchene Francois && Tascon Gutierrez Luis
 * @version 1.1
 */
/*
 * import java.util.*;
  import java.lang.*; 
  import java.io.IOException;
 */


public class main
{
    
        
    public static void main (String [] args)
    {
          /* 
           * ==========================
           *        LE PENDU
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
        
                menuPrincipal();             
        
    }
    
    public static void menuPrincipal()
    {
    byte choix = 0;
	do
    {    	
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\t\tCHALLENGE - MINIGAMES");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\nMenu Principal :\n====================");
    System.out.println("1. Jouer\n2. Quitter");    
        
    choix = TextIO.getByte();
    switch(choix)
    {
        case 1 : 
        	clear();
            menuJeu();                                                 
            break;
        case 2 :                    
                System.out.println("\n\tGOODBYE");         
            break;                 
        default :
                System.out.println("Veuillez indiquer 1 ou 2 !");
            break;
    }
    
    }while(choix != 2);
    }
    
    public static void menuJeu()
    {
    	byte choix = 0;
    	do
    	{
    		System.out.println("    LISTE DE JEUX\n^^^^^^^^^^^^^^^^^^^^^^^^^^");
    		System.out.println("1. Pendu\n2. Quitter");
    		choix = TextIO.getByte();
    		switch(choix)
    		{
    		case 1 :
    			clear();
    			pendu.menu();
    			break;
    		case 2 :
    			System.out.println("Retour");
    			break;
    		default :
    			System.out.println("Veuillez indiquer 1 ou 2 !");
    			break;
    		}
    	}while(choix != 2);
    }
    /*
     * @pre -
     * @post clear the screen
     */
    public static void clear(){
       for (int i = 0; i<25; i++)
       {System.out.println("\n");}
    }  
}
