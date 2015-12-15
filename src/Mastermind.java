
/**
 * This is the game mastermind with letters.
 * 
 * @author Luis Tascon Gutierrez 
 * @version 1.2
 */

public class Mastermind
{
    public static final char BLEU = 'B';
    public static final char NOIR = 'N';
    public static final char VERT = 'V';
    public static final char CYAN = 'C';
    public static final char GRIS = 'G';
    public static final char ROUGE = 'R';
    public static final char JAUNE = 'J';
    public static final char MAUVE = 'M';
    public static final char ORANGE = 'O';
    public static final char ARC_EN_CIEL = 'A';
    public static final char[] c = {BLEU,NOIR,VERT,CYAN,GRIS,ROUGE,JAUNE,MAUVE,ORANGE,ARC_EN_CIEL};
    public static final String[] e = {"BLEU","NOIR","VERT","CYAN","GRIS","ROUGE","JAUNE","MAUVE","ORANGE","ARC_EN_CIEL"};
    //all the colors that can be used.

    public static int bienPlace = 0;
    public static int malPlace = 0;
    public static int difficulte = 2;// by default it's two but there are 3 level of difficulty.
    public static boolean gagne  = false;
    public static int vies = 12;
    public static int score = 0;
    public static String ligne = "";

    public static char[] couleur = new char[4];
    public static char[] entree = new char[4];

    public static void jeu()
    {
        gagne = false;
        boolean avancer = true;
        String[] lignes = new String[vies+2];
        String description = "";
        int viesTmp = vies;

        creerCouleur(couleur);// create a tab with 4 colors random.

        for (int v = 1; v <vies+2; v++)
        {
            lignes[v] = "  ";
        }// initialize each line to avoid to have a line equals to null.

        lignes[0] = "Couleurs possibles : ";
        for(int g = 0; g < 1+3*difficulte; g++)
        {
            lignes[0] += c[g] + " ";
        }// Create the line with all the possible colors in fonction of the difficulty.

        while(!gagne && viesTmp > 0)
        {
            try
            {
                ligne = InOut.getLine();
                for(int g = 0; g < 4; g++)
                {
                    entree[g] = firstLetter();
                }
                avancer = true;
            }
            catch(Exception e)
            {
                avancer = false;
            }// create the tab with the colors entered by the user {entree}

            for(int g = 0; g < 4; g++)
            {
                entree[g] = majuscule(entree[g]);
            }// put in UpperCase 

            if(avancer)// if the colors entered are ok
            {
                for(int g = 0; g < 4; g++)
                {
                    lignes[lignes.length - viesTmp] += entree[g] + " ";
                }// complete the current line

                verification(couleur, entree);// count the well and unwell colors
                challenge.clear();// clear screen

                description = "   bien placees:"+bienPlace + "   mal placees:" + malPlace;
                lignes[lignes.length - viesTmp] += description + "   vies restantes:" + (viesTmp-1);// complete the current line

                for(int t = 0; t < vies+2; t ++)
                {
                    if(lignes[t] != "  ")
                    {System.out.println(lignes[t]);}
                }//print all the lines

                if(bienPlace == 4)
                {
                    gagne = true;
                }
                else {viesTmp --;}// if win -->end of loop
            }
        }
        score = calculScore();
    }

    /**
     * This function take the difficulty and the number of life who still here
     * return the score and add to the playerProfile
     * @return the score of the game
     */
    public static int calculScore()
    {
    	int score = difficulte*vies;
    	profilGestion.ajoutePtsMasterMind(score);
    	return score;
    }

    /**
     * verifie le nombre de couleurs bien placees et le nombre de couleurs mal placees.
     * 
     * @pre couleur[] et entree[] != null
     * @post modifie les variables globales.
     */
    public static void verification(char[] couleur, char[] entree)
    {
        bienPlace = 0;
        malPlace = 0;
        boolean utiliseC[] = new boolean[4];
        boolean utiliseE[] = new boolean[4];
        for(int q = 0; q < utiliseC.length; q++)
        {
            utiliseC[q] = false;
            utiliseE[q] = false;
        }// to empty the arrays.

        for (int i = 0; i < entree.length; i ++)
        {
            if (couleur[i] == entree[i])
                {
                    bienPlace++;
                    utiliseC[i] = true;
                    utiliseE[i] = true;
                }
        }//to count the well placed colors

        for (int i = 0; i < entree.length; i++)
        {
            if (utiliseE[i] == false)
            {
                for(int p = 0; p< couleur.length; p++)
                {
                    if(utiliseC[p] == false)
                    {
                        if(couleur[i] == entree[p])
                        {
                            malPlace++;
                            utiliseE[i] = true;
                            utiliseC[p] = true;
                            break;
                        }
                    }
                }
            }
        }// to count the bad placed colors
    }

    /**
     * Creer un tableau de char aleatoire avec les char venant du tableau c[] en fonction de la difficulte.
     */
    public static void creerCouleur(char[] couleur)
    {
        int longueur = (difficulte == 1)? 4: (difficulte == 2)? 7: 10;
        int a = 0;
        double b = 0.0;
        for (int i = 0; i< 4; i++)
        {
            b = longueur*Math.random();
            a = (int) b;
            couleur[i] = c[a];
        }
    }

    public static void messageFin(boolean gagne, char[] couleur)
    {
        if(gagne)
        {
            System.out.println("\n\nBRAVO VOUS AVEZ GAGNE!!!");
        }
        else
        {
            System.out.println("Dommage vous avez perdu, la combinaison etait : ");
            for (int s = 0; s < 4; s++)
            {
                System.out.print(couleur[s]+ " ");
            }
            System.out.println("\nVotre score pour cette partie est de : " + score + "\n");
        }
    }

    public static void messageAcceuil()
    {
        System.out.println("Vous jouez au mastemind, tapez :");
        for(int i = 0; i < 1+3*difficulte; i++)
        {System.out.println(c[i]+ " pour la couleur : " + e[i]);}
        System.out.println("ATTENTION!! \nSuivez l'exemple si dessous :\n \"N C B V\"\n");
    }

    /**
	 * @param l Take a char
	 * @return the LowerCase of the char if it's an UpperCase and the char itself if it's a LowerCase.
	 */
    public static char majuscule(char l){
        if ((int)l <=122 && (int)l >= 97)
        {return (char)((int)l - 32);}
        else 
            return l;
    }

    public static void menu()
    {
        int choix = 0;
        do
        {
            System.out.println("==========================\n\t MASTERMIND \n==========================");
            System.out.println("1. Jouer\n2. Option\n3. Regles\n4. Quitter");
            choix = InOut.getInt();
            switch(choix)
            {
                case 1:
                messageAcceuil();
                jeu();
                messageFin(gagne, couleur);
                menu();
                break;
                case 2:
                menuOption();
                break;
                case 3:
                regles();
                break;
                case 4:
                challenge.menuJeu();
                break;
                default :
                System.out.println("Veuillez introduire 1,2,3 ou 4!");
                break;

            }
        }while(choix !=4);
    }

    public static void menuOption()
    {
        byte c = 0;
        do
        {
            System.out.println("~~~~~~~~~ Menu des options ~~~~~~~~~");
            System.out.println("1. Choisir la difficulte\n2. Choisir le nombre de vies\n3. Choisir le nombre de joueurs (Pas encore disponible)\n4. Quitter");

            c = InOut.getByte();
            switch(c)
            {
                case 1:
                option1();
                break;
                case 2:
                option2();
                break;
                case 3:
                //a faire
                break;
                case 4:
                menu();
                break;
                default:
                System.out.println("Veuillez indiquer 1,2 ou 3!");
            }
        }while(c!= 3 && c!= 2 && c!= 1);
    }

    public static void option1()
    {
        boolean re = true;
        challenge.clear();
        System.out.printf("Vous etes actuellement en difficulte %2d .\n", difficulte);
        System.out.println("Quelle niveau de difficulte voulez vous?\n1. facile\n2. normal\n3. difficile");
        do
        {
            try{
                difficulte = InOut.getInt();
                re = false;
            }
            catch(Exception e)
            {
                re = true;
            }
        }while(re);
        menuOption();
    }

    public static void option2()
    {
        boolean re = true;
        challenge.clear();
        System.out.printf("Vous avez actuellement %2d lignes pour resoudre le jeu.\n", vies);
        System.out.println("Combien de lignes voulez vous avoir pour trouver la combinaison?");
        do
        {
            try{
                vies = InOut.getInt();
                re = false;                
            }
            catch(Exception e)
            {
                re = true;
            }
        }while(re);
        if(vies>99)
        {
            vies = 99;
        }
        menuOption();
    }

    public static void regles()
    {
        challenge.clear();
        System.out.println("^^^^^^^^^^^^^^ But du jeu ^^^^^^^^^^^^^^");
        System.out.println("Le but du Mastermind est de gagner en un minimum de manches.\n" +
            "Le joueur qui doit trouver la combinaison secrete gagne une manche des lors qu’il y parvient en maximum 12 coups."+
            "\nLe joueur a perdu si il n’est pas parvenu a trouver la combinaison en 12 coups.");
        System.out.println("\n^^^^^^^^^^^^ Comment jouer ? ^^^^^^^^^^^^");
        System.out.println("L'ordinateur ou le joueur1 choisis une combinaison de couleurs (lettres) qui vont constituer le code secret."+
            "\nLe joueur2 doit essayer de deviner la combinaison et chaque tour propose une combinaison."+
            "\nL'ordinateur ou le joueur1 va indiquer combien de couleurs (ou lettres) sont bien placees et combien sont mal placees.\n\n");
        menu();
    }
    
    private static char firstLetter()
    {
        boolean fL = true;
        char x = ' ';
        for(int i = 0; i < ligne.length() && fL ; i++)
        {
            if(Character.isLetter(ligne.charAt(i)))
            {
                x = ligne.charAt(i);
                ligne = ligne.substring(i+1);
                fL = false;
            }
        }
        return x;
    }
}
