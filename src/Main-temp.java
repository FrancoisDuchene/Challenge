/*
 * @author Duchene Francois && Tascon Gutierrez Luis
 * @version 0.2 
 */

import java.util.*;

public class Main
{
 public static void main (String [] args)
 {
  /*  
   * Déclaration des variables
   */
  
  /* 
   * ==========================
   *    LE PENDU
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
 public static void jeu()
 {
  // Differentes variables utilisées
  String MotSecret = "zigote"; 
  String MotUser = "******";
  String str = "bite";
  char LettreUser = 'A'; 
  int LongueurMot = MotSecret.length();
  boolean condi = false;
  Scanner sc = new Scanner(System.in);
  
  //TO DO 
  //contenir tous les lettres du motSecret dans des char
  //
  //
  
  String MotTape = ""; 
  
  /*
   *  Texte dédié Interface utilisateur
   */
  System.out.println("Trouvez le mot secret !");
  System.out.println(MotUser);
  
  for(byte coup = 0; coup != 10; coup++ )
  {
   System.out.println(String.format("Il reste : %d vie(s)",(10-coup)));
      
   System.out.println("Ecrivez une lettre : ");
   
   str = sc.nextLine();   
   LettreUser = str.charAt(0);  // on récupère le premier caractère de la string
   
   /*
   for(byte n = 0; n <= LongueurMot; n++) //n est le numéro de la lettre et finalCharMotSecret est la dernière lettre du mot Secret
   {
    
   }
   */
   /*
   String LettreTape = Character.toString(LettreUser);
   
    if (MotSecret.contains(LettreTape)){
     System.out.println("Une lettre trouvé : "+ LettreTape); 

     if (!MotTape.contains(LettreTape)) 
     {      
      MotTape += LettreTape;
     }
    
     System.out.println(String.format("Il reste : %d vie(s)",(10-coup)));

     
     if (MotTape.length() == MotSecret.length()) {
     System.out.println("Félicitation, vous avez trouvé le mot " + MotSecret + " en " + coup + " coups !!!"); 
     
     break;
     }
     
     
    }   
   */
   // Bug pour l'instant
   for(byte i = 0; i != LongueurMot; i++)
   {
    
    //condi = LettreUser == MotSecret.charAt(i));
    if (LettreUser == MotSecret.charAt(i))
    {
      
     
     
     
    }
    
    
    if(MotUser.equals(MotSecret))
    {
     System.out.println("Félicitation, vous avez trouvé le mot " + MotSecret + " en " + coup + " coups !!!"); 
     coup = 10;
     break;
    } 
   
   }
   
    
  
  }
  
  sc.close();
  System.out.println("Program ended correctly");
  
 }
 public static void menu()
 {
  byte choix = 0;
  Scanner scr = new Scanner(System.in);  
  
  System.out.println("==========================\n\t LE PENDU \n==========================");
  System.out.println("1.Jouer \n2.Options \n3.Crédits \n4.Quitter");
  choix = scr.nextByte();
  
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
     System.out.println("Dev : F.Duc & ");     
    break;
   case 4 :
    break;
   default :
     System.out.println("Veuillez indiquer 1,2,3 ou 4 !");
    break;
  }
  
  }while(choix <= 0 || choix >= 5);
  scr.close();
 }
 
 
}<a