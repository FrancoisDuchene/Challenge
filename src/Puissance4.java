
/**
 * Write a description of class Puissance4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Puissance4
{
    private static int longueur = 8;
    private static char[][] ch;

    public static void menu()
    {
        byte choix = 0; 

        do
        {
            System.out.println("==========================\n\t Puissance 4 \n==========================");
            System.out.println("1.Jouer \n2.Options \n3.Quitter");

            choix = InOut.getByte();
            switch(choix)
            {
                case 1 : 
                System.out.println("\n C'est parti !");                   
                jouer();                                                   
                break;
                case 2 :
                menuOption();           
                break;           
                case 3 :               
                break;          
                default :
                System.out.println("Veuillez indiquer 1,2 ou 3 !");
                break;
            }

        }while(choix != 3);
    }

    public static void menuOption()
    {
        byte choix = 0;     
        do
        {
            System.out.println("************************\n\tOptions:\n************************");
            System.out.println("Voulez-vous Changer :\n1. La taille du tableau?\n2. le nombre de joueurs ?");
            System.out.println("3. Retourner en arrière");
            choix = InOut.getByte();

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
                default:
                System.out.println("Veuillez indiquer 1,2 ou 3 !");
                break;
            }
        }while(choix != 3);
    }

    public static void option1()
    {
        System.out.println("La taille actuelle du tableau est de : " + longueur);
        do
        {
            System.out.println("Quelle taille voulez vous lui donner?");
            longueur
        }
        while(longueur <1 || longueur > 40);
    }

    public static void option2()
    {
        return;
    }

    public static void jouer()
    {
        initialisationTab();
        afficheTab();
        int entree = 0;
        boolean j = true;

        while(!fin())
        {
            main.clear();
            afficheTab();
            int num = (j)?1:2;
            System.out.println("Entrer le numero de la colonne : C'est au joueur "+ num);
            entree = InOut.getInt();
            if (entree >= 1 && entree <=  ch.length)
            {
                if(!ajouterTab(((j)?1:2), entree-1))
                {System.out.println("NON ajoute  -- > toujours au meme joueur");}
                else{j = (j)? false: true;}
            }
            if(allignement())
            {
                afficheTab();
                num = (j)?2:1;
                System.out.println("Le joueur "+ num+ " a gagne!");
            }
        }
    }

    /**
     * Ajoute un char different si {Joueur} == 1 ou == 2 dans la {colonne} envoyee
     */
    public static boolean ajouterTab(int joueur, int colonne)
    {
        for(int i = ch.length -1; i >= 0 ; i--)
        {
            if(ch[i][colonne] == (char)12288)
            {
                ch[i][colonne] = (joueur ==1 )? (char)9774: (char)9775;
                return true;
            }
        }
        return false;
    }

    /**
     * retourne true si tout le tableau est remplis, false sinon. 
     */
    public static boolean fin()
    {
        if(allignement())
        {return true;}
        for(int i = 0; i < ch.length; i++)
        {
            if(verifTab(i))
            {return false;}
        }
        return true;
    }

    public static boolean allignement()
    {
        for(int i = 0; i < ch.length -3; i++)
        {
            for(int j = 0; j < ch[i].length ; j++)
            {
                if(ch[i][j] != (char)12288 && ch[i][j] == ch[i+1][j] && ch[i+1][j] == ch[i+2][j] && ch[i+2][j] == ch[i+3][j])
                {return true;}//en ligne
                if(ch[j][i] != (char)12288 && ch[j][i] == ch[j][i+1] && ch[j][i+1] == ch[j][i+2] && ch[j][i+2] == ch[j][i+3])
                {return true;}//en colonne
            }
        }
        for(int i = 0; i < ch.length -3; i++)
        {
            for(int j = 0; j < ch[i].length-3 ; j++)
            {
                if(ch[i][j] != (char)12288 && ch[i][j] == ch[i+1][j+1] && ch[i+1][j+1] == ch[i+2][j+2] && ch[i+2][j+2] == ch[i+3][j+3])
                {return true;}//en diagonale descendante
                if(ch[i+3][j] != (char)12288 && ch[i+3][j] == ch[i+2][j+1] && ch[i+2][j+1] == ch[i+1][j+2] && ch[i+1][j+2] == ch[i][j+3])
                {return true;}//en diagonale montante
            }
        }
        return false;
    }

    /**
     * retourne true si il reste de la place dans la {colonne} specifiee, false sinon.
     */
    public static boolean verifTab(int colonne)
    {
        for(int i = 0; i <longueur; i++)
        {
            if(ch[i][colonne] == (char)12288)
            {return true;}
        }
        return false;
    }

    public static void initialisationTab()
    {
        ch = new char[longueur][longueur];
        for(int p = 0; p< ch.length; p++)
        {
            for(int q = 0; q< ch[p].length; q++)
            {
                ch[p][q] =  (char)12288;
            }
        }
    }

    private static void afficheTab()
    {
        int longueurt = 2*longueur;
        char x = (char)12288;
        int t = -1;
        int b = 1;
        for(int i = 0; i < longueurt; i++)
        {
            if(i%2 == 1)
            {System.out.print(b);b++;}
            else
            {
                if(i%6 == 2)
                {System.out.print(x);}
                else
                {System.out.print(x + " ");}
            }
        }
        System.out.println();
        for(int j = 0; j < longueurt+1; j++)
        {
            if(j %2 == 0)
            {
                for(int c = 0; c < longueurt+1; c++)
                {
                    if(j == 0)
                    {
                        if(c == 0)
                        {x = (char)9556;}
                        else if(c == longueurt)
                        {x = (char)9559;}
                        else
                        {
                            if(c %2 == 0)
                            {x = (char)9572;}
                            else
                            {x = (char)9552;}
                        }
                    }
                    else if(j == longueurt)
                    {
                        if(c == 0)
                        {x = (char)9562;}
                        else if(c == longueurt)
                        {x = (char)9565;}
                        else
                        {
                            if(c %2 == 0)
                            {x = (char)9575;}
                            else
                            {x = (char)9552;}
                        }
                    }
                    else
                    {
                        if(c == 0)
                        {x = (char)9567;}
                        else if(c == longueurt)
                        {x = (char)9570;}
                        else
                        {
                            if(c %2 == 0)
                            {x = (char)9532;}
                            else
                            {x = (char)9472;}
                        }
                    }
                    System.out.print(x);
                }
            }
            else
            {
                int r = 0;
                for(int c = 0; c < longueurt+1; c++)
                {
                    if(c == 0 || c == longueurt)
                    {x = (char)9553;}
                    else
                    {
                        if(c%2 == 0)
                        {x = (char)9474;}
                        else
                        {
                            if(r == 0)
                            {t++;}
                            x = ch[t][r];
                            r++;
                        }
                    }

                    System.out.print(x);
                }
            }
            System.out.println();
        }
    }
}
