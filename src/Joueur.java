
public class Joueur {
	//Attributs
	private String name;
	private int [] scorePendu, scorePlusMoins;
		
	//Constructeurs
	public Joueur()
	{
		name = "Jean-Michel";
		scorePendu = remplirTab(10);
		scorePlusMoins = remplirTab(10);
	}
	public Joueur(String n)
	{
		name = n;
		scorePendu = remplirTab(10);
		scorePlusMoins = remplirTab(10);
	}
	public Joueur(String n, int sPd, int sPM )
	{
		name = n;
		scorePendu = remplirTab(sPd);
		scorePlusMoins = remplirTab(sPM);
	}
	
	//Methodes
	
	/**
	 * Met l'attribut scorePendu à la valeur du tableau envoyé en paramètre
	 * @param sPd tableau de int à mettre à la place du scorePendu actuel
	 */
	public void setScorePendu(int [] sPd)
	{
		for(int i=0; i<scorePendu.length; i++)
		{
			scorePendu[i] = sPd[i];
		}
	}
	/**
	 * Met l'attribut scorePlusMoins à la valeur du tableau envoyé en paramètre
	 * @param sPM tableau de int à mettre à la place du scorePlusMoins actuel
	 */
	public void setScorePlusMoins(int [] sPM)
	{
		for(int i=0; i<scorePlusMoins.length;i++)
		{
			scorePlusMoins[i] = sPM[i];
		}
	}
	/**
	 * Remplace le nom actuel par rapport au nom donné en paramètre
	 * @param n un String
	 */
	public void setName(String n)
	{
		name = n;
	}
	public void ajouteScoreSPd(int n)
	{
		for(int i=0; i<scorePendu.length; i++)
		{
			if(scorePendu[i] != -1)
			{
				scorePendu[i] = n;
				i = (scorePendu.length-1);
			}
		}
	}	
	public void ajouteScoreSPM(int n)
	{
		for(int i=0; i<scorePlusMoins.length; i++)
		{
			if(scorePlusMoins[i] != -1)
			{
				scorePlusMoins[i] = n;
				i = (scorePlusMoins.length-1);
			}
		}
	}
	public int[] remplirTab(int nbr)
	{
		int [] newTab = new int[nbr];
		for(int i=0; i<nbr; i++)
		{
			newTab[i] = -1;
		}
		return newTab;
	}
	public void afficheScoreSPd()
	{
		for(int i=0; i<scorePendu.length ;i++)
		{
			System.out.println("[" + i + "] " + scorePendu[i]);
		}
	}
	public void afficheScoreSPM()
	{
		for(int i=0; i<scorePlusMoins.length ;i++)
		{
			System.out.println("[" + i + "] " + scorePlusMoins[i]);
		}
	}
	public void afficheNom()
	{
		System.out.print(name);
	}
	public String getName()
	{
		return name;
	}
	public int[] getScorePendu() 
	{
		return scorePendu;
	}
	public int[] getScorePlusMoins()
	{
		return scorePlusMoins;
	}
	public String toString()
	{
		return ("Nom : " + name + " - Score Pendu : " + scorePenduTotal() + " - Score PlusMoins : " + scorePlusMoinsTotal() + " - Score Total : " + scoreTotal() );
	}
	public int scoreTotal()
	{		
		return scorePenduTotal()+scorePlusMoinsTotal();
	}
	public int scorePenduTotal()
	{
		int a = 0;
		for(int i = 0; i < scorePendu.length; i++)
		{
			a += scorePendu[i];
		}
		return a;
	}
	public int scorePlusMoinsTotal()
	{
		int b = 0;
		for(int i = 0; i< scorePlusMoins.length; i++)
		{
			b += scorePlusMoins[i];
		}
		return b;
	}
}
