/**
 * @author Vinsifroid && Bivisi
 * @version 1.1
 */
public class main
{        
    public static Joueur j = new Joueur();
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
                profil();
                menuPrincipal();             
        
    }
    public static void profil()
    {
        System.out.println("Veuillez Indiquer votre nom ! :");
        String n = TextIO.getlnWord();
        j.setName(n);
        System.out.println("Bonjour " + j.getName() + " et bienvenue sur ");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) 
        {           
            e.printStackTrace();
        }
        clear();
    }
    
    public static void menuPrincipal()
    {
    byte choix = 0;
    do
    {       
    System.out.println("#  $$$$  $$ $$   $$$   $$    $$    $$$$$  $   $   $$$$  $$$$$");
    System.out.println("# $$     $$ $$  $$ $$  $$    $$    $$     $$  $  $$     $$");
    System.out.println("# $      $$$$$  $$$$$  $$    $$    $$$$   $ $ $  $   $$ $$$");
    System.out.println("# $$     $$ $$  $$ $$  $$    $$    $$     $  $$  $$   $ $$");
    System.out.println("#  $$$$  $$ $$  $$ $$  $$$$$ $$$$$ $$$$$  $   $   $$$$  $$$$$");
     
    
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\t\tCHALLENGE - MINIGAMES");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\nMenu Principal :\n====================");
    System.out.println("1. Jouer\n2. Credits\n3. Quitter");    
        
    choix = TextIO.getByte();
    switch(choix)
    {
        case 1 : 
            clear();
            menuJeu();                                                 
            break;
        case 2 :                    
            credit();
            break;   
        case 3:
            System.out.println("\n\tGOODBYE"); 
            System.exit(0);
            break;
        default :
            System.out.println("Veuillez indiquer 1 ou 2 !");
            break;
    }
    
    }while(choix != 3);
    }
    
    public static void menuJeu()
    {
        byte choix = 0;
        do
        {
            clear();
            System.out.println("    LISTE DE JEUX\n^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("1. Pendu\n2. PlusMoins\n3. Mastermind\n4. Quitter");
            choix = TextIO.getByte();
            switch(choix)
            {
            case 1 :
                clear();
                pendu.menu(j);
                break;
            case 2 :
                clear();
                plusMoins.menu();
                break;
            case 3 :
                clear();
                Mastermind.menu();
            case 4 : 
                menuPrincipal();
                break;
            default :
                System.out.println("Veuillez indiquer 1,2 ou 3 !");
                break;
            }
        }while(choix != 3);
    }
    public static void credit()
    {
        clear();
        System.out.println("Developpeurs Principaux :\n=====================");
        System.out.println("Vinsifroid\nBivisi\nMelvinMajor\n\n\n");
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
