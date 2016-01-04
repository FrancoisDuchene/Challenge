
/**
 * Project Challenge 2015
 */
public class TabAligne3
{
	private class Aligne3
	{
		public int x = -1;
		public int y = -1;
		public boolean joueur = false;// alignement ordi == false, allignement joueur == true
		public int type = 0; // 1 -> ligne, 2 -> colonne, 3 -> diagonale descendante, 4 -> diagonale montante

		/**
		 * Constructor for objects of class Aligne3
		 */
		public Aligne3(int x, int y, boolean joueur, int type )
		{
			this.x = x;
			this.y = y;
			this.joueur = joueur;
			this.type = type;
		}
	}

	private Aligne3[] tab;

	public TabAligne3(int dimension)
	{
		tab = new Aligne3[dimension];
	}

	public int choisirColonne(char[][] ch, int entree)// ne prends pas encore en compte quel joueur...
	{
		boolean[] bool = new boolean[ch.length];

		for(int x = 0; x < bool.length; x ++)
		{bool[x] = false;}

		/**colonne**/
		for(int i = 0; i < tab.length && tab[i] != null; i++)
		{
			if(tab[i].type == 2)
			{
				if(Puissance4.caseIsEmpty(tab[i].x-1, tab[i].y))
				{
					if(tab[i].joueur == false)
					{return tab[i].y;}
					else
					{bool[tab[i].y] = true;}
				}
			}
		}

		/**ligne**/
		for(int i = 0; i < tab.length && tab[i] != null; i++)
		{
			if(tab[i].type == 1)
			{
				if(Puissance4.caseIsEmpty(tab[i].x, tab[i].y-1))
				{
					if(!Puissance4.caseIsEmpty(tab[i].x+1, tab[i].y-1))
					{
						if(tab[i].joueur == false)
						{return tab[i].y-1;}
						else
						{bool[tab[i].y-1] = true;}
					}
				}
				else if(Puissance4.caseIsEmpty(tab[i].x, tab[i].y+3))
				{
					if(!Puissance4.caseIsEmpty(tab[i].x+1, tab[i].y+3))
					{
						if(tab[i].joueur == false)
						{return tab[i].y+3;}
						else
						{bool[tab[i].y+3] = true;}
					}
				}
			}
		}

		/**diagonale descendante**/
		for(int i = 0; i < tab.length && tab[i] != null; i++)
		{
			if(tab[i].type == 3)
			{
				if(Puissance4.caseIsEmpty(tab[i].x-1, tab[i].y-1))
				{
					if(!Puissance4.caseIsEmpty(tab[i].x, tab[i].y-1))
					{
						if(tab[i].joueur == false)
						{return tab[i].y-1;}
						else
						{bool[tab[i].y-1] = true;}
					}
				}
				else if(Puissance4.caseIsEmpty(tab[i].x+3, tab[i].y+3))
				{
					if(!Puissance4.caseIsEmpty(tab[i].x+4, tab[i].y+3))
					{
						if(tab[i].joueur == false)
						{return tab[i].y+3;}
						else
						{bool[tab[i].y+3] = true;}
					}
				}
			}
		}

		/**diagonale montante**/
		for(int i = 0; i < tab.length && tab[i] != null; i++)
		{
			if(tab[i].type == 4)
			{
				if(Puissance4.caseIsEmpty(tab[i].x+1, tab[i].y-1))
				{
					if(!Puissance4.caseIsEmpty(tab[i].x+2, tab[i].y-1))
					{
						if(tab[i].joueur == false)
						{return tab[i].y-1;}
						else
						{bool[tab[i].y-1] = true;}
					}
				}
				else if(Puissance4.caseIsEmpty(tab[i].x-3, tab[i].y+3))
				{
					if(!Puissance4.caseIsEmpty(tab[i].x-2, tab[i].y+3))
					{
						if(tab[i].joueur == false)
						{return tab[i].y+3;}
						else
						{bool[tab[i].y+3] = true;}
					}
				}
			}
		}

		for(int x = 0; x < bool.length; x++)
		{
			if(bool[x] == true)
			{return x;}
		}
		return -1;
	}

	private void ajouterA(int x, int y, boolean joueur, int type)
	{
		for(int i = 0; i < tab.length; i++)
		{
			if(tab[i] == null)
			{
				tab[i] = new Aligne3(x, y, joueur, type);
				return;
			}
		}
	}

	public void enleverA(int pos)
	{
		if(isEmpty())
		{return;}
		int last = -1;
		for(int i = 0;i < tab.length;i++)
		{
			if(tab[i] != null)
			{last = i;}
		}

		tab[pos] = tab[last];
		tab[last] = null;
	}

	private void enleverA(Aligne3 a)
	{
		int last = -1;
		int pos = -1;
		for(int i = 0;i < tab.length;i++)
		{
			if(tab[i] != null)
			{last = i;}
			if(tab[i] == a)
			{pos = i;}
		}
		tab[pos] = tab[last];
		tab[last] = null;
	}

	public boolean isEmpty()
	{
		for(int i = 0;i < tab.length; i++)
		{
			if(tab[i] != null)
			{return false;} // encore rempli
		}
		return true; // vide
	}

	public void viderTab()
	{
		for(int i = 0;i < tab.length; i++)
		{
			tab[i] = null;
		}
	}

	public void remplirTab(char[][] ch)
	{
		for(int i = 0; i < ch.length; i++)
		{
			for(int j = 0; j < ch[i].length-2; j++)
			{
				if(ch[i][j] != (char)12288 && ch[i][j] == ch[i][j+1] && ch[i][j] == ch[i][j+2])
				{
					ajouterA(i, j, (ch[i][j] == Puissance4.j1)?true: false, 1);// i j  = coordonnee extremite gauche
				}//en ligne

				if(ch[j][i] != (char)12288 && ch[j][i] == ch[j+1][i] && ch[j][i] == ch[j+2][i])
				{
					ajouterA(j, i, (ch[j][i] == Puissance4.j1)?true: false, 2);// i j = coordonnee extremite superieure
				}//en colonne
			}
		}
		for(int i = 0; i < ch.length -2; i++)
		{
			for(int j = 0; j < ch[i].length-2 ; j++)
			{
				if(ch[i][j] != (char)12288 && ch[i][j] == ch[i+1][j+1] && ch[i][j] == ch[i+2][j+2])
				{
					ajouterA(i  , j, (ch[i][j] == Puissance4.j1)?true: false, 3);// i j = coordonnee extremite haut gauche
				}//en diagonale descendante
			}
		}
		for(int i = 2; i < ch.length; i++)
		{
			for(int j = 0; j < ch[i].length-2 ; j++)
			{
				if(ch[i][j] != (char)12288 && ch[i][j] == ch[i-1][j+1] && ch[i][j] == ch[i-2][j+2])
				{
					ajouterA(i  , j, (ch[i][j] == Puissance4.j1)?true: false, 4);// i j = coordonnee extremite bas gauche
				}//en diagonale montante
			}
		}
	}

	public static void afficheTab(int longueur, char[][] ch)
	{
		final int longGrid = longueur*2;
		char x = (char)12288;
		int t = -1;

		for(int i = 0; i < longGrid; i++)
		{
			if(i%2 == 0)
			{
				System.out.print(x);
			}
			else
			{
				System.out.print((i/2)+1);				
			}
		}
		System.out.println();
		for(int j = 0; j < longGrid+1; j++)
		{
			if(j %2 == 0)
			{
				for(int c = 0; c < longGrid+1; c++)
				{
					if(j == 0)
					{
						if(c == 0)
						{x = (char)9556;}
						else if(c == longGrid)
						{x = (char)9559;}
						else
						{
							if(c %2 == 0)
							{x = (char)9572;}
							else
							{x = (char)9552;}
						}
					}
					else if(j == longGrid)
					{
						if(c == 0)
						{x = (char)9562;}
						else if(c == longGrid)
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
						else if(c == longGrid)
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
				for(int c = 0; c < longGrid+1; c++)
				{
					if(c == 0 || c == longGrid)
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
