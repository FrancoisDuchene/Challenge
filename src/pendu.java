
public class pendu {

	public static int vies = 7;
    public static byte joueurs = 1;

	 /*
     * subroutine to play the game
     * @pre : number of lifes (n>0) and number of players (1 or 2)
     * @post: play the game
     */
   public static void jeu() {
       // all the variables used
       String MotSecret = "zygote";
       String MotUser = "";
       String LettresFausses = "";
       char LettreUser = 'a';
       int LongueurMot = MotSecret.length();
       int vies_tmp = vies;
       boolean re = true, same = false;

       if (joueurs == 2) { //condition to define the word
             System.out.println("Le joueur 1 peut entrer un mot\nNe pas mettre d'accent!!");
             MotSecret = TextIO.getWord();
             main.clear();
             System.out.println("Le joueur 2 doit essayer de deviner le mot rentre par le joueur 1");
       }
       else {
             MotSecret = dico();
       }
       MotSecret.toLowerCase();
       LongueurMot = MotSecret.length();

       /*
        *  Texte dédié Interface utilisateur
        */
       for (byte o = 0; o < LongueurMot; o++) // loop to have the same number of * than the letters in the "MotSecret" {
            if(MotSecret.charAt(o) == '-') {
                MotUser = MotUser + "-";
           }
            else {
                MotUser = MotUser + "*";
           }
        }
       System.out.println("Trouvez le mot secret !");

       while( vies_tmp != 0) {           
           main.clear();
           System.out.println(String.format("\n\n\nIl reste : %d vie(s)",(vies_tmp)));
           System.out.println(MotUser);
           System.out.println("Vous avez déjà proposé les lettres suivantes: " + LettresFausses);
           System.out.println("Ecrivez une lettre : ");
           same = false;
           re = true;
           LettreUser = TextIO.getChar();
           //If ASCII of letter is between 65 and 90 it's an UpperCase so we need to convert in a LowerCase
           LettreUser = minuscule(LettreUser);
           for(byte i=0; i < LongueurMot; i++) //loop to test all the string {
               if (LettreUser == MotSecret.charAt(i)) //if it's ok => fonction to replace the chain {
                   MotUser = replaceCharAt(MotUser, LettreUser, i);
                   re = false;
               //  changeChar(MotUser,i,LettreUser);
               }
           }
           for(int l=0; l < LettresFausses.length(); l++) // String with the wrong letters {
               if(LettreUser == LettresFausses.charAt(l)) {
                   same = true;
               }
           }
           if(MotUser.equals(MotSecret)) {
           	main.clear();
           	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>BRAVO !!!<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
               System.out.println("Félicitation, vous avez trouvé le mot " + MotSecret + " avec encore " + (vies_tmp) + " vie(s) !!!");
               break;
           }

           if (re && !same) { //if the letters was wrong and different of the wrong letters's string, minus 1 life
               vies_tmp--;
               LettresFausses = LettresFausses + LettreUser + " ";
           }

           if(vies_tmp == 0) {
           	main.clear();
               System.out.println("Il fallait trouver : " + MotSecret + "\n");
               System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t GAME OVER\n~~~~~~~~~~~~~~~~~~~~~~~~~~");
           }
       }
       
   }
   
   /*
    * @pre -
    * @post replace a char in a string in at a certain position
    */
   public static String replaceCharAt(String s, char c, int pos) {
       return s.substring(0,pos) + c + s.substring(pos+1);
   }
   
   /*
    * @pre -
    * @post Retourne un String venant de la classe Dico.java
    */
   public static String dico() {
       String str = "";
       try {
           str = Dico.lectureMots();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
       return str;
   }
   
   /*
    * @pre -
    * @post give a menu to user and then redirect him
    */
   public static void menu() {
       byte choix = 0;
       do
       {
       System.out.println("==========================\n\t LE PENDU \n==========================");
       System.out.println("1.Jouer \n2.Options \n3.Quitter");
       choix = TextIO.getByte();
       switch(choix) {
           case 1 : 
                   System.out.println("\n C'est parti !");
                   jeu();
               break;
           case 2 :
                   menuOption();           
               break;
           case 3 :
               break;
           default :
                   System.out.println("Veuillez indiquer 1,2,3 ou 4 !");
               break;
       }

       }
       while(choix != 3);
   }

   //All of the following instructions are about the option menu

   /*
    * @pre -
    * @post Renvoie l'utilisateur sur l'option pr�alablement choisi
    */
   public static void menuOption() {
       byte choix = 0;
       do {
           System.out.println("************************\n\tOptions:\n************************");
           System.out.println("Voulez-vous Changer :\n1. le nombre de vies ?\n2. le nombre de joueurs ?\n3. [Still in Dev]ajouter un mot au dico");
           System.out.println("\n4. Retourner en arrière");
           choix = TextIO.getByte();

               switch(choix) {
               case 1:
                   option1();
                   break;
               case 2:
                   option2();
                   break;
               case 3:
               	option3();
                   break;
               case 4:
               	break;
               default:
                   System.out.println("Veuillez indiquer 1,2,3 ou 4 !");
                   break;
               }
       }
       while(choix != 4);
   }

   /*
    * @pre -
    * @post change the value of the member variable vies
    */
   public static void option1() { //subroutines in the options to choose the number of life(42)
       System.out.println("Vous avez actuellement " + vies + " vies");
       System.out.println("Combien voulez vous de vies ?");
       vies = TextIO.getInt();
       if(vies == 0) {
           vies = 1;
       }
   }

   /*
    * @pre -
    * @post change the value of the member variable joueurs
    */
   public static void option2() {   
       System.out.println("Vous êtes actuellement en mode " + joueurs + " joueur(s)");
       System.out.println("1. 1 Player\n2. 2 Players");
       joueurs = TextIO.getByte();
   }

   /*
    * !!!ATTENTION!!! Bug
    */
   public static void option3() {    	
   	System.out.println("Veuillez indiquer ci-dessous quel mot vous voudriez ajouter au dictionnaire");
   	String mot = TextIO.getWord();    	
   	Dico.ecritureMot(mot);
   }

   /*
    * @pre Take a char
    * @post Return the LowerCase of the char if it's an UpperCase and the char itself if it's a LowerCase.
    */
   public static char minuscule(char l) {
       if ((int)l <=90 && (int)l >= 65) {
       return (char)((int)l + 32);
       }
       else { 
       return l;
       }
   }
}
