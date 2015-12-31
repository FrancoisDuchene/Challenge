
/**
 * Puissance4 is the class who contain the game puissance 4
 * 
 * @author Bivisi & Vinsifroid
 * @version 1.0 gamma
 */
public class Puissance4
{
	private static int longueur = 8;
	private static char[][] ch;
	private static TabAligne3 tab = null;
	private static int nombreJoueurs = 1;
	public static char j1 = (char)9774;
	public static char j2 = (char)9775;
	public static byte language = 1;

	public static void menu()
	{
		byte choix = 0;
		language = challenge.getLanguage();

		do
		{
			if(language == 1)
			{
				System.out.println("==========================\n\t Puissance 4 \n==========================");
				System.out.println("1.Jouer \n2.Options \n3.Quitter");
			}else{
				System.out.println("===========================\n\t Connect 4 \n===========================");
				System.out.println("1.Play \n2.Settings \n3.Quit");
			}
			choix = InOut.getByte();
			switch(choix)
			{
			case 1 : 
				if(language==1){System.out.println("\n C'est parti !");}
				else{System.out.println("\n Let's go !");}                   
				jouer();                                                   
				break;
			case 2 :
				menuOption();           
				break;           
			case 3 :               
				break;          
			default :				
				if(language==1){System.out.println("Veuillez indiquer 1,2 ou 3 !");}
				else{System.out.println("Please enter 1,2 or 3 !");}
				break;
			}

		}while(choix != 3);
	}

	public static void menuOption()
	{
		byte choix = 0;     
		do
		{
			if(language == 1)
			{
				System.out.println("************************\n\tOptions:\n************************");
				System.out.println("Voulez-vous Changer :\n1. La taille du tableau ?\n2. le nombre de joueurs ?");
				System.out.println("3. Retourner en arrière");
			}else{
				System.out.println("************************\n\tSettings:\n***********************");
				System.out.println("Want you change :\n1. the array lenght ?\n2. the number of players ?");
				System.out.println("3. Go back");
			}

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
				if(language==1){System.out.println("Veuillez indiquer 1,2 ou 3 !");}
				else{System.out.println("Please enter 1,2 or 3 !");}
				break;
			}
		}while(choix != 3);
	}

	public static void option1()
	{
		if(language==1){System.out.println("La taille actuelle du tableau est de : " + longueur);}
		else{System.out.println("The current array's length is : " + longueur);}
		do
		{
			if(language==1){System.out.println("Quelle taille voulez vous lui donner ?");}
			else{System.out.println("What is the new length you want to give ?");}
			longueur = InOut.getInt();
		}
		while(longueur <1 || longueur > 20);
	}

	public static void option2()
	{
		if(language==1){System.out.println("Vous etes actuellement en mode "+ nombreJoueurs+ " joueurs.");}
		else{System.out.println("Your current player's mode is "+ nombreJoueurs+ " playeurs.");}

		do
		{
			if(language==1){System.out.println("A combien voulez-vous jouer?");}
			else{System.out.println("How many do you want to play ?");}
			nombreJoueurs = InOut.getInt();
		}
		while(nombreJoueurs <1 || nombreJoueurs > 2);
	}

	public static void jouer()
	{
		initialisationTab();
		TabAligne3.afficheTab(longueur, ch);
		if(nombreJoueurs == 2)
		{
			int entree = 0;
			boolean j = true;

			while(!fin())
			{
				challenge.clear();
				TabAligne3.afficheTab(longueur, ch);
				int num = (j)?1:2;
				if(language==1)
				{
					System.out.println("Entrer 0 pour sortir.");
					System.out.println("Entrer le numero de la colonne : C'est au joueur "+ num);
				}else{
					System.out.println("Enter 0 to quit");
					System.out.println("Enter the number of the column : it's player"+ num + "turn");
				}

				entree = InOut.getInt();
				if(entree > ch.length)
				{
					if(language==1){System.out.println("Cette colonne n'existe pas !");}
					else{System.out.println("This column don't exist !");}
				}
				else
				{
					if(entree == 0)
					{
						return;
					}
					if (entree >= 1 && entree <=  ch.length)
					{
						if(!ajouterTab(((j)?1:2), entree-1))
						{
							if(language==1){System.out.println("NON ajoute  -- > toujours au meme joueur");}
							else{System.out.println("NOT ADDED  -- > still to the same player");}
						}
						else{j = (j)? false: true;}
					}
					if(allignement4())
					{
						TabAligne3.afficheTab(longueur, ch);
						num = (j)?2:1;
						if(language==1){System.out.println("Le joueur "+ num+ " a gagne!");}
						else{System.out.println("The player " + num + "won the game !");}
					}
				}

			}
		}
		else
		{
			int entree = 0;
			boolean first = true;
			while(!fin())
			{
				challenge.clear();
				TabAligne3.afficheTab(longueur, ch);
				if(language==1)
				{
					System.out.println("Entrer 0 pour sortir.");
					System.out.println("Entrer le numero de la colonne");
				}else{
					System.out.println("Enter 0 to quit");
					System.out.println("Enter the number of the column");
				}
				entree = InOut.getInt();
				if(entree > ch.length)
				{
					if(language==1){System.out.println("Cette colonne n'existe pas !");}
					else{System.out.println("This column don't exist !");}
				}
				else
				{
					if(entree == 0)
					{
						return;
					}
					if (entree >= 1 && entree <=  ch.length)
					{
						if(!ajouterTab(1, entree-1))
						{
							if(language==1){System.out.println("NON ajoute  -- > toujours a vous");}
							else{System.out.println("Not added -- > still you turn");}
						}
					}//choix du joueur
					if(allignement4())
					{
						challenge.clear();
						TabAligne3.afficheTab(longueur, ch);
						if(language==1){System.out.println("Vous avez gagne!");}
						else{System.out.println("You win !");}
						return;
					}

					challenge.clear();
					TabAligne3.afficheTab(longueur, ch);
					try{Thread.sleep(1500);}
					catch(Exception e){}

					if(first)
					{
						if(!ajouterTab(2, entree-2))
						{ajouterTab(2, entree);}
					}
					else
					{choixOrdi(ch , entree);}
					first = false;
					//choix de l'ordi

					if(allignement4())
					{
						challenge.clear();
						TabAligne3.afficheTab(longueur, ch);
						if(language==1){System.out.println("Vous avez perdu!");}
						else{System.out.println("You loose ! To bad !");}
						return;
					}
				}

			}
		}
		if(fin())
		{
			challenge.clear();
			TabAligne3.afficheTab(longueur, ch);
			if(language==1){System.out.println("Egalite parfaite");}
			else{System.out.println("Perfect equality");}
		}
	}

	public static void choixOrdi(char[][] ch, int entree)
	{
		TabAligne3 t = new TabAligne3(longueur*longueur);

		t.viderTab();
		t.remplirTab(ch);

		for(int i = 0; !t.isEmpty() && i < 10; i++)
		{
			int x = t.choisirColonne(ch, entree);
			if(x != -1)
			{
				if(!ajouterTab(2, x))
				{
					t.enleverA(x);
				}
				else
				{return;}
			}
		}

		choisirCase(entree);
	}

	public static void choisirCase(int entree)
	{
		int count = 0;
		int v = 0;
		do
		{
			v = (int)(Math.random()*3)-1;
			count++;
			if(count >3)
			{v = (int)(Math.random()*ch.length)-1;}
		}while(!ajouterTab(2, (entree+v > ch.length+1 || entree+v < 1)? entree-v-1: entree+v-1));
	}

	/**
	 * Ajoute un char different si {Joueur} == 1 ou == 2 dans la {colonne} envoyee
	 */
	public static boolean ajouterTab(int joueur, int colonne)
	{
		if (colonne <0 || colonne >ch.length-1)
		{
			return false;
		}
		for(int i = ch.length -1; i >= 0 ; i--)
		{
			if(ch[i][colonne] == (char)12288)
			{
				ch[i][colonne] = (joueur ==1 )? j1: j2;
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
		if(allignement4())
		{return true;}
		for(int i = 0; i < ch.length; i++)
		{
			if(verifTab(i))
			{return false;}
		}
		return true;
	}

	public static boolean allignement4()
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
		tab = new TabAligne3(longueur*longueur);
		for(int p = 0; p< ch.length; p++)
		{
			for(int q = 0; q< ch[p].length; q++)
			{
				ch[p][q] =  (char)12288;
			}
		}
	}

	/**
	 * return true if ch[{i}][{j}] is Empty, else return false.
	 */
	public static boolean caseIsEmpty(int i, int j)
	{
		if(i< 0 || i > ch.length-1 || j < 0 || j > ch[0].length-1)
		{return false;}
		return ch[i][j] == (char)12288;
	}
}
