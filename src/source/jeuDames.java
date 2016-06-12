package source;

/*
 * Liste des choses a faire pour que le jeu fonctionne
 * TODO dessiner en ASCII un plateau avec une representation des pions
 * code unicode pour symboles : U+26C0 (pion blanc), U+26C1 (dame blanche),
 * U+26C2 (pion noir), U+26C3 (dame noire)
 * TODO une liste d'actions possible pour les pions et quels pions sont susceptibles
 * d'etre bouge
 * TODO faire des actions speciales si le pion est une dame
 * TODO une intelligence artificielle pour pouvoir jouer contre l'ordinateur
 */

/**
 * 
 * @author vinsifroid
 * @version indev
 */
public class jeuDames {

	private static boolean[][] plateau = new boolean[10][10];
	private static pion[] lstPions = new pion[40];

	public void jeu()
	{
		debutJeu();
		//Par convention Joueur 1 = blancs et Joueur 2 = noirs
		System.out.println("Joueur 1");
		System.out.println("Joueur 2");
	}
	/**
	 * Fonction qui prepare le jeu, il remplit le plateau de false,
	 * il initie les pions aux bons endroits
	 */
	private void debutJeu()
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;i<10;i++)
			{
				plateau[i][j] = false;
			}
		}
		byte Xtmp = 1;
		byte Ytmp = 1;
		for(int i=0;i<20;i++)
		{
			lstPions[i] = new pion(Xtmp, Ytmp, true);
			plateau[Xtmp][Ytmp] = true;
			Xtmp = (byte) (Xtmp +2);		
			if(Xtmp==11)
			{
				Xtmp = 2;
				Ytmp++;
			}else if(Xtmp==12)			
			{
				Xtmp = 1;
				Ytmp++;
			}
		}
		Xtmp = 1;
		Ytmp = 7;
		for(int i=20;i<40;i++)
		{
			lstPions[i] = new pion(Xtmp, Ytmp, false);
			plateau[Xtmp][Ytmp] = true;
			Xtmp = (byte) (Xtmp +2);
			if(Xtmp==11)
			{
				Xtmp = 2;
				Ytmp++;
			}else if(Xtmp==12)
			{
				Xtmp = 1;
				Ytmp++;
			}
		}
	}
	/**
	 * @param pi
	 * @param sens true si gauche et false si droit
	 */
	public void deplacerPion(pion pi, boolean sens)
	{
		byte alors = canMove(pi,sens);
		switch(alors)
		{
		case 0:
			System.err.println("Erreur - bord du cadre");
			break;
		case 1:
			System.err.println("Erreur - Pion deja present sur la case et appartenant au joueur");
			break;
		case 2:
			System.err.println("Erreur - Pion deja present sur la case et appartenant a l'adversaire");

			//TODO faire une option pour prendre les pions de l'adversaire
			//TODO trouver une meilleure facon de faire pour prendre le pion d'un adversaire
			//Pour pouvoir verifier qu'on puisse prendre le pion adverse, on doit d'abord regarder
			//en fonction des 4 possibilitees qu'il ne se trouve rien de l'autre cote pour pouvoir bouger

			// On verifie si c'est possible de bouger
			pion tmp = new pion((byte)0,(byte)0,true);
			Coordonnees coord = pi.getCoord();
			if(sens){
				if(pi.isWhite()){
					tmp.setCoord(new Coordonnees((byte)(coord.getCoordX()-1),(byte)(coord.getCoordY()+1)));
				}else{
					tmp.setCoord(new Coordonnees((byte)(coord.getCoordX()-1),(byte)(coord.getCoordY()-1)));
				}				
			}else{
				if(pi.isWhite()){
					tmp.setCoord(new Coordonnees((byte)(coord.getCoordX()+1),(byte)(coord.getCoordY()+1)));
				}else{
					tmp.setCoord(new Coordonnees((byte)(coord.getCoordX()+1),(byte)(coord.getCoordY()-1)));
				}
			}
			byte et = canMove(tmp,sens);

			if(et==3)	//On le bouge
			{
				pion nouvea = pi;
				if(sens){
					if(pi.isWhite()){
						nouvea.setCoord(new Coordonnees((byte)(coord.getCoordX()-2),(byte)(coord.getCoordY()+2)));
					}else{
						nouvea.setCoord(new Coordonnees((byte)(coord.getCoordX()-2),(byte)(coord.getCoordY()-2)));
					}				
				}else{
					if(pi.isWhite()){
						nouvea.setCoord(new Coordonnees((byte)(coord.getCoordX()+2),(byte)(coord.getCoordY()+2)));
					}else{
						nouvea.setCoord(new Coordonnees((byte)(coord.getCoordX()+2),(byte)(coord.getCoordY()-2)));
					}
				}
			}
			else
			{
				System.err.println("Erreur Impossible de prendre le pion Adverse");
			}
			break;
		case 3:
			Coordonnees coco = pi.getCoord();
			pion nouveau = pi;
			//On cree un nouveau pion et on change ses coordonnees
			if(sens){
				if(pi.isWhite()){
					nouveau.setCoord(new Coordonnees((byte)(coco.getCoordX()-1),(byte)(coco.getCoordY()+1)));
				}else{
					nouveau.setCoord(new Coordonnees((byte)(coco.getCoordX()-1),(byte)(coco.getCoordY()-1)));
				}				
			}else{
				if(pi.isWhite()){
					nouveau.setCoord(new Coordonnees((byte)(coco.getCoordX()+1),(byte)(coco.getCoordY()+1)));
				}else{
					nouveau.setCoord(new Coordonnees((byte)(coco.getCoordX()+1),(byte)(coco.getCoordY()-1)));
				}
			}
			byte indice = searchPionI(coco);
			lstPions[indice] = nouveau;
			plateau[nouveau.getCoordX()][nouveau.getCoordY()] = true;
			break;
		}
	}
	private pion prAdversaire(pion pi, boolean surUnAdversaire, boolean arrete)
	{
		// Cas de base
		if(arrete==false)
		{
			return pi;
		}
		//On verifie a gauche et a droite si il y a un autre adversaire
		byte a = canMove(pi,true);
		byte b = canMove(pi,false);
		
		//On cree un nouveau pion pour repositionner le curseur sur le plateau
		pion tmp = new pion(pi.getCoordX(),pi.getCoordY(),pi.isWhite());
		//On regarde a droite
		switch(a)
		{
		case 0:
			return pi;
		case 1:
			return pi;
		case 2:
			if(surUnAdversaire)
			{
				return pi;
			}else{
				if(pi.isWhite())
				{
					tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()+1)));
				}else{
					tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()-1)));
				}
			}
			break;
		case 3:
			if(surUnAdversaire)
			{
				if(pi.isWhite())
				{
					tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()+1)));
				}else{
					tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()-1)));
				}
			}else{
				return pi;
			}
			break;
		}
		//TODO changer les affectations pour la recursion
		//quand on ne sait plus du tout avancer, on met arrete a false pour arreter la recursion
		//dire si le pion est sur un adversaire ou pas avec la variable correspondante
		
		return prAdversaire(tmp,false,true);
	}
	/**
	 * Cette methode regarde si le pion peut bouger
	 * @param pi le pion à jouer
	 * @param sens dans quel sens le joueur veut il faire avancer son pion - 
	 * true à gauche et false à droite (avec le plateau ou les blancs sont toujours les plus proches
	 * @return 0 si il s'agit du bord, 1 si il y a un pion du même camp, 2 si y a un pion adverse et 3 si il peut avancer
	 */
	public byte canMove(pion pi, boolean sens)
	{
		Coordonnees meh = pi.getCoord();
		boolean blip = false;

		if(pi.isWhite())
		{
			if(sens)
			{
				if(meh.getCoordX()==0)
					return 0;
				blip = plateau[meh.getCoordX()-1][meh.getCoordY()+1];
				if(blip)
				{
					pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()-1),(byte) (meh.getCoordY()+1)));
					if(tmp.isWhite()){
						return 1;
					}else{
						return 2;
					}
				}
			}else{
				if(meh.getCoordX()==10)
					return 0;
				blip = plateau[meh.getCoordX()+1][meh.getCoordY()+1];
				if(blip)
				{
					pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()+1),(byte) (meh.getCoordY()+1)));
					if(tmp.isWhite()){
						return 1;
					}else{
						return 2;
					}
				}
			}			
		}else{
			if(sens)
			{
				if(meh.getCoordX()==0)
					return 0;
				blip = plateau[meh.getCoordX()-1][meh.getCoordY()-1];
				if(blip)
				{
					pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()-1),(byte) (meh.getCoordY()-1)));
					if(tmp.isWhite())
					{
						return 1;
					}
					else{
						return 2;
					}
				}
			}else{
				if(meh.getCoordX()==10)
					return 0;
				blip = plateau[meh.getCoordX()+1][meh.getCoordY()-1];
				if(blip)
				{
					pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()+1),(byte) (meh.getCoordY()-1)));
					if(tmp.isWhite())
					{
						return 1;
					}
					else{
						return 2;
					}
				}
			}			
		}		
		return 3;
	}
	private pion searchPion(Coordonnees co)
	{
		pion tmp;
		for(int i=0;i<lstPions.length;i++)
		{
			tmp = lstPions[i];
			if(tmp.getCoordX()== co.getCoordX() && tmp.getCoordY() == co.getCoordY())
				return tmp;			
		}
		return null;
	}
	/**
	 * 
	 * @param co des coordonnees
	 * @return l'indice du tableau ou se trouve le pion
	 */
	@SuppressWarnings("unused")
	private byte searchPionI(Coordonnees co)
	{
		pion tmp;
		for(int i=0;i<lstPions.length;i++)
		{
			tmp = lstPions[i];
			if(tmp.getCoordX() == co.getCoordX() && tmp.getCoordY() == co.getCoordY());
			{return (byte)i;}
		}
		return -1;
	}
	
	// Classes Annexes

	/**
	 * 
	 * @author vinsifroid
	 * @version 1.0
	 */
	public class pion{
		private Coordonnees Coo;
		private boolean couleur;
		private boolean dame;

		/**
		 * 
		 * @param X
		 * @param Y
		 * @param couleur true for white, false for black
		 * @category constructor
		 */
		public pion(byte X, byte Y, boolean couleur)
		{
			Coo.setX(X);
			Coo.setY(Y);
			this.couleur = couleur;
			this.dame = false;
		}
		/**
		 * 
		 * @return true if white and false if black
		 */
		public boolean isWhite()
		{
			return couleur;
		}
		public boolean isDame()
		{
			return dame;
		}
		public void becomeDame()
		{
			dame = true;
		}		
		public void setX(byte X)
		{
			Coo.setX(X);
		}
		public void setY(byte Y)
		{
			Coo.setY(Y);
		}
		public byte getCoordX()
		{
			return Coo.getCoordX();
		}
		public byte getCoordY()
		{
			return Coo.getCoordY();
		}
		public void setCoord(Coordonnees hey)
		{
			Coo = hey;
		}
		public Coordonnees getCoord()
		{
			return Coo;
		}

	}
	/**
	 * 
	 * @author vinsifroid
	 * @version 1.0
	 */
	public class Coordonnees{
		private byte X;
		private byte Y;

		public Coordonnees(byte X, byte Y)
		{
			this.X = X;
			this.Y = Y;
		}
		public void setX(byte X)
		{
			this.X = X;
		}
		public void setY(byte Y)
		{
			this.Y = Y;
		}
		public byte getCoordX()
		{
			return X;
		}
		public byte getCoordY()
		{
			return Y;
		}
	}
}