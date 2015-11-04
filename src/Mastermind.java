
/**
 * Write a description of class Mastermind here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    public static int difficulte = 1;
    public static boolean gagne  = false;
    public static int nombreCouleur = 4;

    public static void mastermind()
    {

        menu();

    }

    public static void jeu()
    {
        gagne = false;
        int vies = 10;
        char[] couleur = new char[4];
        char[] entree = new char[4];
        boolean avancer = true;
        String[] lignes = new String[11];
        String description = "";

        creerCouleur(couleur);

        lignes[0] = "Couleurs possibles : ";
        for (int v = 1; v <11; v++)
        {
            lignes[v] = "  ";
        }

        for(int g = 0; g < 1+3*difficulte; g++)
        {
            lignes[0] += c[g] + " ";
        }

        while(!gagne && vies > 0)
        {

            try
            {
                for(int g = 0; g < 4; g++)
                {
                    TextIO.skipBlanks();
                    entree[g] = TextIO.getChar();
                }
                avancer = true;
            }
            catch(Exception e)
            {
                avancer = false;
            }

            for(int g = 0; g < 4; g++)
            {
                for(int u = 0; u < 10;u++)
                {
                    if (entree[g] == c[u])
                    {
                        avancer = false;
                        break;
                    }
                }
            }

            if(avancer)
            {

                for(int g = 0; g < 4; g++)
                {
                    entree[g] = majuscule(entree[g]);
                    lignes[lignes.length - vies] += entree[g] + " ";
                }

                verification(couleur, entree);
                main.clear();

                description = "   bien placees:"+bienPlace + "   mal placees:" + malPlace;
                lignes[lignes.length - vies] += description + "   vies restantes:" + (vies-1);

                for(int t = 0; t < 11; t ++)
                {
                    if(lignes[t] != "  ")
                    {System.out.println(lignes[t]);}
                }

                if(bienPlace == 4)
                {
                    gagne = true;
                }
                else {vies --;}
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
                        if(couleur[p] == entree[i])
                        {
                            malPlace++;
                            utiliseE[i] = true;
                            utiliseC[p] = true;
                        }
                    }
                }
            }
        }
    }

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
        }
    }

    public static void messageAcceuil()
    {
        System.out.println("Vous jouez au mastemind, tapez :");
        for(int i = 0; i < 1+3*difficulte; i++)
        {System.out.println(c[i]+ " pour la couleur : " + e[i]);}
        System.out.println("ATTENTION!! \nSuivez l'exemple si dessous :\n \"N C B V\"\n");
    }

    public static char majuscule(char l){
        if ((int)l <=122 && (int)l >= 97)
        {return (char)((int)l - 32);}
        else 
            return l;
    }

    public static void menu()
    {
        byte choix = 0;
        do
        {
            System.out.println("==========================\n\t MASTERMIND \n==========================");
            System.out.println("1. Jouer\n2. Option\n3. Regles\n4. Quitter");
            choix = TextIO.getByte();
            switch(choix)
            {
                case 1:
                    messageAcceuil();
                    jeu();
                    messageFin(gagne, c);
                    break;
                case 2:
                    option();
                    break;
                case 3:
                    regles();
                    break;
                case 4:
                    break;
                default :
                    System.out.println("Veuillez introduire 1,2,3 ou 4!");
                    break;

            }
        }while(choix !=4 && choix != 3&& choix != 2 && choix != 1);
    }

    public static void option()
    {

    }

    public static void regles()
    {

    }
}
