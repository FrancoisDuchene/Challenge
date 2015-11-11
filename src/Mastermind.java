
/**
 * This is the game mastermind with letters.
 * 
 * @author Luis Tascon Gutierrez 
 * @version 1.1
 */

import java.awt.*;

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

    public static int bienPlace = 0;
    public static int malPlace = 0;
    public static int difficulte = 2;
    public static boolean gagne  = false;
    public static int nombreCouleur = 4;
    public static int vies = 12;

    public static char[] couleur = new char[4];
    public static char[] entree = new char[4];

    public static void jeu()
    {
        gagne = false;
        boolean avancer = true;
        String[] lignes = new String[vies+2];
        String description = "";
        int viesTmp = vies;

        creerCouleur(couleur);

        lignes[0] = "Couleurs possibles : ";
        for (int v = 1; v <vies+2; v++)
        {
            lignes[v] = "  ";
        }

        for(int g = 0; g < 1+3*difficulte; g++)
        {
            lignes[0] += c[g] + " ";
        }

        while(!gagne && viesTmp > 0)
        {

            try
            {
                for(int g = 0; g < 4; g++)
                {
                    entree[g] = TextIO.getChar();
                    TextIO.skipBlanks();
                }
                avancer = true;
            }
            catch(Exception e)
            {
                avancer = false;
            }

            for(int g = 0; g < 4; g++)
            {
                entree[g] = majuscule(entree[g]);
            }

            if(avancer)
            {

                for(int g = 0; g < 4; g++)
                {
                    lignes[lignes.length - viesTmp] += entree[g] + " ";
                }

                verification(couleur, entree);
                challenge.clear();

                description = "   bien placees:"+bienPlace + "   mal placees:" + malPlace;
                lignes[lignes.length - viesTmp] += description + "   vies restantes:" + (viesTmp-1);

                for(int t = 0; t < vies+2; t ++)
                {
                    if(lignes[t] != "  ")
                    {System.out.println(lignes[t]);}
                }

                if(bienPlace == 4)
                {
                    gagne = true;
                }
                else {viesTmp --;}
            }
        }
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
        }

        for (int i = 0; i < entree.length; i ++)
        {
            if (couleur[i] == entree[i])
                {
                    bienPlace++;
                    utiliseC[i] = true;
                    utiliseE[i] = true;
                }
        }

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
        }
    }

    /**
     * Creer un tableau de char aleatoire avec les char venant du tableau c[] en fonction de la difficulte.
     */
    public static void creerCouleur(char[] couleur)
    {
        int longueur = (difficulte == 1)? 4: (difficulte == 2)? 7: 10;;
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
            System.out.println("Dommage vous avez perdu, la combinaison etait:");
            for (int s = 0; s < 4; s++)
            {
                System.out.print(couleur[s]+ " ");
            }
            System.out.println("\n");
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
     * @pre recoit un char LETTRE   
     * @post retourne une majuscule si majuscule et si minuscule.
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
            choix = TextIO.getInt();
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

            c = TextIO.getByte();
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
                difficulte = TextIO.getInt();
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
                vies = TextIO.getInt();
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
}
