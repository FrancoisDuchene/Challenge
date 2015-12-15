
public class Joueur {
	//Attributs
	/**
	 * the name of the player
	 */
	private String name;
	/**
	 * it's the score of the HangmannGame
	 */
	private int [] scorePendu;
	/**
	 * it's the score of the HighLowGame
	 */
	private int [] scorePlusMoins;
	
	private int [] scoreMaster;
	private static int language = 1;
	//Constructeurs
	/**
	 * @category Constructor
	 */
	public Joueur()
	{
		name = "Jean-Michel";
		scorePendu = remplirTab(10);
		scorePlusMoins = remplirTab(10);
		scoreMaster = remplirTab(10);
		language = challenge.getLanguage();
	}

	/**
	 * @category Constructor 
	 * @param n the name of the player
	 */
	public Joueur(String n)
	{
		name = n;
		scorePendu = remplirTab(10);
		scorePlusMoins = remplirTab(10);
		scoreMaster = remplirTab(10);
		language = challenge.getLanguage();
	}

	/**
	 * @category Constructor
	 * @param n the name of the player
	 * @param sPd the score of the Hangmann
	 * @param sPM the score of the HighLow
	 */
	public Joueur(String n, int sPd, int sPM )
	{
		name = n;
		scorePendu = remplirTab(sPd);
		scorePlusMoins = remplirTab(sPM);
		scoreMaster = remplirTab(10);
		language = challenge.getLanguage();
	}
	public Joueur(String name, int sPd, int sPM, int sMM)
	{
		this.name = name;
		scorePendu = remplirTab(sPd);
		scorePlusMoins = remplirTab(sPM);
		scoreMaster = remplirTab(sMM);
		language = challenge.getLanguage();
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
	public void setScoreMasterMind(int [] sMM)
	{
		for(int i=0; i<scoreMaster.length;i++)
		{
			scoreMaster[i] = sMM[i];
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

	/**
	 * Add score to the total score arrays of the Hangmann
	 * @param n the score to add
	 */
	public void ajouteScoreSPd(int n)
	{
		for(int i=0; i<scorePendu.length; i++)
		{
			if(scorePendu[i] == -1)
			{
				scorePendu[i] = n;
				i = (scorePendu.length-1);
			}
		}
	}

	/**
	 * Add score to the total score arrays of the HighLow
	 * @param n the score to add
	 */
	public void ajouteScoreSPM(int n)
	{
		for(int i=0; i<scorePlusMoins.length; i++)
		{
			if(scorePlusMoins[i] == -1)
			{
				scorePlusMoins[i] = n;
				i = (scorePlusMoins.length-1);
			}
		}
	}
	public void ajouteScoreSMM(int n)
	{
		for(int i=0; i<scoreMaster.length; i++)
		{
			if(scoreMaster[i] == -1)
			{
				scoreMaster[i] = n;
				i = (scoreMaster.length-1);
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
			System.out.print("[" + i + "] ");
			System.out.println((scorePendu[i] == -1)?" - ":scorePendu[i]);
		}
	}

	public void afficheScoreSPM()
	{
		for(int i=0; i<scorePlusMoins.length ;i++)
		{
			System.out.print("[" + i + "] ");
			System.out.println((scorePlusMoins[i] == -1)?" - ":scorePlusMoins[i]);
		}
	}
	
	public void afficheScoreSMM()
	{
		for(int i=0; i<scoreMaster.length ;i++)
		{
			System.out.print("[" + i + "] ");
			System.out.println((scoreMaster[i] == -1)?" - ":scoreMaster[i]);
		}
	}

	/**
	 * Print the name of the player
	 */
	public void afficheNom()
	{
		System.out.print(name);
	}

	/**
	 * @return the name of the player
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @return the int array's of Hangmann's score
	 */
	public int[] getScorePendu() 
	{
		return scorePendu;
	}

	/**
	 * @return the int array's of HighLow score
	 */
	public int[] getScorePlusMoins()
	{
		return scorePlusMoins;
	}

	public int[] getScoreMasterMind()
	{
		return scoreMaster;
	}
	/**
	 * @return a String describing the player with his name, his total HighLow score and his total Hangmann score
	 */
	public String toString()
	{
		switch(language)
		{
		case 1:
			return ("Nom : " + name + " - Score Pendu : " + scorePenduTotal() + " - Score PlusMoins : " + scorePlusMoinsTotal() + " - Score Total : " + scoreTotal() );
		case 2:
			return ("Name : " + name + " - Hangmann Score : " + scorePenduTotal() + " - HighLow Score : " + scorePlusMoinsTotal() + " - Total Score : " + scoreTotal() );
		}
		return "";
	}

	public int scoreTotal()
	{		
		int score = scorePenduTotal()+scorePlusMoinsTotal()+scoreMasterMindTotal();
		if(score < 0)
		{
			score = 0;
		}
		return score;
	}

	public int scorePenduTotal()
	{
		int a = 0;
		for(int i = 0; i < scorePendu.length; i++)
		{
			if(scorePendu[i] >= 0)
			{
				a += scorePendu[i];
			}

		}
		return a;
	}

	public int scorePlusMoinsTotal()
	{
		int b = 0;
		for(int i = 0; i< scorePlusMoins.length; i++)
		{
			if(scorePlusMoins[i] >= 0)
			{
				b += scorePlusMoins[i];
			}			
		}
		return b;
	}
	public int scoreMasterMindTotal()
	{
		int c = 0;
		for(int i = 0; i< scoreMaster.length; i++)
		{
			if(scoreMaster[i] >= 0)
			{
				c += scoreMaster[i];
			}			
		}
		return c;
	}
	public boolean equals(Object o)
	{
		if(o instanceof Joueur)
		{
			Joueur jr = (Joueur) o;
			if(this.getName().equals(jr.getName()))
			{	
				if(this.scorePenduTotal() == jr.scorePenduTotal())
				{
					if(this.scorePlusMoinsTotal() == jr.scorePlusMoinsTotal())
					{
						if(this.scoreMasterMindTotal() == jr.scoreMasterMindTotal())
						{
							return true;
						}						
					}
				}
			}
			return false;			
		}
		else
		{return false;}
	}

	public void savePlayer()
	{
		Fichier fl = new Fichier();
		fl.ouvrir(name + ".sav", "w", false);
		fl.ecrireString(name);
		fl.ecrireString(".A");
		for(int i=0; i<scorePendu.length;i++)
		{
			fl.ecrireInt(scorePendu[i]);
		}
		fl.ecrireString(".B");
		for(int i=0; i<scorePlusMoins.length;i++)
		{
			fl.ecrireInt(scorePlusMoins[i]);
		}
		fl.ecrireString(".C");
		for(int i=0; i<scoreMaster.length;i++)
		{
			fl.ecrireInt(scoreMaster[i]);
		}
		fl.ecrireString(".end");
		fl.fermer();
	}
}