package source;

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
	 * 
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
			//TODO faire une option pour prendre le(s) pion(s) de l'adversaire
			break;
		case 3:
			Coordonnees coco = pi.getCoord();
			if(sens){
				if(pi.isWhite()){
					pi.setCoord(new Coordonnees((byte)(coco.getCoordX()-1),(byte)(coco.getCoordY()+1)));
				}else{
					pi.setCoord(new Coordonnees((byte)(coco.getCoordX()-1),(byte)(coco.getCoordY()-1)));
				}				
			}else{
				if(pi.isWhite()){
					pi.setCoord(new Coordonnees((byte)(coco.getCoordX()+1),(byte)(coco.getCoordY()+1)));
				}else{
					pi.setCoord(new Coordonnees((byte)(coco.getCoordX()+1),(byte)(coco.getCoordY()-1)));
				}
			}
			//TODO enregistrer la nouvelle position du pion
			//Il faudrait faire une nouvelle methode de recherche dans la liste de pions qui renvoie l'indice du pion recherche
			//Et mettre a jour le plateau
			break;
		}
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
		for(int i=0;i<lstPions.length;i++)
		{
			pion tmp = lstPions[i];
			if(tmp.getCoordX()== co.getCoordX() && tmp.getCoordY() == co.getCoordY())
			{
				return tmp;
			}
		}
		return null;
	}

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