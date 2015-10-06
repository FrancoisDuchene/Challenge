/*
 * @author Duchene Francois && Tascon Gutierrez Luis
 * @version 1.0
 * 
 * To ameliorate:
 * 
 * put a dico
 * to code the 2 players mode.. (option to have 2 player     already exist!!)
 */

import java.util.*;
import java.lang.*;

public class main
{
 public static void main (String [] args)
 {  
  /* 
   * ==========================
   *    LE PENDU
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
  * @pre : number of lifes and number of players (1 or 2)
  * @post: play the game
  */
 public static void jeu(byte vies, byte joueurs)
 {
  // all the variables used
  String MotSecret = "zigote";
  String MotUser = "";
  String LettresFausses = "";
  char LettreUser = 'A'; 
  int LongueurMot = MotSecret.length();
  boolean condi = false;
  
  if (joueurs == 2){//loop to define the word
      //ask the first player to enter a word
  }
  else {
      //Take a word in a file
  }
  
  for (byte o = 0; o < LongueurMot; o++) // loop to have the same number of * than the letters in the "MotSecret"
  {
    MotUser = MotUser + "*";
  }
  
  
  System.out.println("Trouvez le mot secret !"); 
  
  while( vies != 0)
  {
   int re = 1;
   boolean same = false;
   System.out.println(String.format("\n\n\nIl reste : %d vie(s)",(vies)));
   System.out.println(MotUser);
   System.out.println("Vous avez déjà proposer les lettres suivantes: " + LettresFausses);
      
   System.out.println("Ecrivez une lettre : ");
   
   LettreUser = TextIO.getChar();
   
   for (int i=0; i< LongueurMot;i++) // loop to test all the string
   {
       if(LettreUser == MotSecret.charAt(i)){ // if it s ok => fonction to replace the chain
       MotUser = replaceCharAt(MotUser, LettreUser, i);
       re = 0;
       }
       
   }
   for(int l = 0; l < LettresFausses.length(); l++)
   {
       if(LettreUser == LettresFausses.charAt(l))
       {same = true;}
   }
    
    if(MotUser.equals(MotSecret))
    {
     System.out.println("Félicitation, vous avez trouvé le mot " + MotSecret + " avec encore " + (vies) + " vie(s) !!!"); 
     break;
    }    
    
    if (re == 1 && !same)// if the letters was wrong, minus 1 life
    {
        vies--;
        LettresFausses = LettresFausses + LettreUser + " ";
    }
   }  
  
}
 
public static String replaceCharAt(String s, char c,int pos) {
    
    return s.substring(0,pos) + c + s.substring(pos+1);
}
  
public static void menu()
 {
  byte choix = 0;
  byte vies = 7;
  byte joueurs = 1;
  
  
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

public static byte option1(){// subroutines in the options to choose the number of life
     byte vies = 7;
     System.out.println("Combien voulez vous de vies?");
     vies = TextIO.getByte();
     return vies;
}

public static byte option2(){
    byte joueurs = 1;
    System.out.println("1. 1 Player\n2. 2 Players");
    joueurs = TextIO.getByte();
    if (joueurs == 1){return 1;}
    else {return 2;}
}
 
}