package source;

import fichier.InOut;

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
 * @author vinsifroid
 * @version indev
 */
public class jeuDames {

	private static pion[] lstPions = new pion[40];

	public static void jeu()
	{
		boolean victoire = false;
		boolean change = true;
		//boolean controleur de la deuxième boucle
		//deuxième boucle qui sert si jamais le joueur veut choisir un autre pion
		boolean retourner = false;

		debutJeu();
		//Par convention Joueur 1 = blancs et Joueur 2 = noirs

		do{
			pion pi = null;

			System.out.println("Pions blancs");
			printEtatSonJeu(true);
			System.out.println("Pions noirs");
			printEtatSonJeu(false);
			
			//Tour des blancs
			do{
				pi = interaction(true);

				System.out.println("Vous avez choisi le pion - " + pi.toString());
				System.out.println("Voulez-vous le bouger a gauche en (" + (pi.getCoordX()-1) + "," + (pi.getCoordY()+1) +
						") ou a droite en (" + (pi.getCoordX()+1) + "," + (pi.getCoordY()+1) + ") ?");
				System.out.println("gauche (1) ou droite (2) ? - ou changer de pion a bouger ? (3)");
				do{
					byte choix = 0;
					do{
						choix = InOut.getByte();
					}while(choix!=1 && choix!=2 && choix!=3);
					if(choix==3)
					{
						change = false;
						retourner = true;
					}else{
						retourner = false;
						boolean sens = true;
						if(choix==2){sens=false;}
						byte etat = deplacerPion(pi,sens);
						if(etat == 0 || etat == 1){
							System.err.println("Donnez l'autre direction ou changer de pion");
						}else{
							change=false;
						}
					}				
				}while(change);
				printEtatSonJeu(true);

				victoire = victory(true);
				if(victoire)
				{
					System.out.println("Les Blancs ont gagne !!!");
				}
			}while(retourner);

			//Tour des Noirs

			//On rajoute un if pour que si le joueur blanc a gagne, que le joueur noir ne puisse plus jouer du tout
			if(!victoire)
			{				
				do{
					pi = interaction(false);

					System.out.println("Vous avez choisi le pion - " + pi.toString());
					System.out.println("Voulez-vous le bouger a gauche en (" + (pi.getCoordX()-1) + "," + (pi.getCoordY()-1) +
							") ou a droite en (" + (pi.getCoordX()+1) + "," + (pi.getCoordY()-1) + ") ?");
					System.out.println("gauche (1) ou droite (2) ? - ou changer de pion a bouger ? (3)");
					do{
						byte choix = 0;
						do{
							choix = InOut.getByte();
						}while(choix!=1 && choix!=2 && choix!=3);
						if(choix==3)
						{
							change = false;
							retourner = true;
						}else{
							retourner = false;
							boolean sens = true;
							if(choix==2){sens=false;}
							byte etat = deplacerPion(pi,sens);
							if(etat == 0 || etat == 1){
								System.err.println("Donnez l'autre direction ou changer de pion");
							}else{
								change=false;
							}
						}
					}while(change);
					printEtatSonJeu(true);

					victoire = victory(false);
					if(victoire)
					{
						System.out.println("Les noirs ont gagne !!!");
					}
				}while(retourner);
			}
		}while(victoire!=true);

	}
	/**
	 * Fonction qui prepare le jeu, il remplit le plateau de false,
	 * il initie les pions aux bons endroits
	 */
	private static void debutJeu()
	{
		//Initialisation des blancs
		byte Xtmp = 1;
		byte Ytmp = 1;
		for(int i=0;i<20;i++)
		{
			String name = "PN_" + Integer.toString(i);
			lstPions[i] = new pion(Xtmp, Ytmp, true, name);
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
		//Initialisation des noirs
		Xtmp = 1;
		Ytmp = 7;
		for(int i=20;i<40;i++)
		{
			String name = "PN_" + Integer.toString(i-20);
			lstPions[i] = new pion(Xtmp, Ytmp, false,name);
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
	 * @return 0 si il s'agit du bord, 1 si il y a un pion du meme camp, 2 si y a un pion adverse et 3 si il peut avancer
	 */
	private static byte deplacerPion(pion pi, boolean sens)
	{
		byte alors = canMove(pi,sens);
		switch(alors)
		{
		case 0:
			System.err.println("Erreur - bord du cadre");
			return 0;
		case 1:
			System.err.println("Erreur - Pion deja present sur la case et appartenant au joueur");
			return 1;
		case 2:
			System.err.println("Erreur - Pion deja present sur la case et appartenant a l'adversaire");
			//TODO faire une option pour prendre les pions de l'adversaire
			//TODO trouver une meilleure facon de faire pour prendre le pion d'un adversaire
			//Pour pouvoir verifier qu'on puisse prendre le pion adverse, on doit d'abord regarder
			//en fonction des 4 possibilitees qu'il ne se trouve rien de l'autre cote pour pouvoir bouger

			// On charge un curseur sur l'adversaire et ensuite on l'envoie a la fonction dediee pour tuer les adversaires
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
			byte test = canMove(tmp,sens);
			if(test==3)
			{
				System.out.println("Possbilite de prendre l'adversaire, voulez-vous le faire");
			}
			pion sd = prAdversaire(tmp,true,!sens,true);
			System.out.println("Votre pion est desormais situe aux coordonnees : " + sd.getCoordX() + " , " + sd.getCoordY());
			return 2;
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
			if(nouveau.isWhite())
			{
				if(nouveau.getCoordY()==10)
				{
					nouveau.becomeDame();
				}
			}else{
				if(nouveau.getCoordY()==0)
				{
					nouveau.becomeDame();
				}
			}
			byte indice = searchPionI(coco);
			lstPions[indice] = nouveau;
			return 3;
		}
		return 0;
	}
	/**
	 * [Fonction recyclee pour plus tard lors de l'elaboration de l'IA]
	 * @param pi le pion avec les coodornnes de l'adversaire que l'on veut prendre
	 * @param surUnAdversaire mettre a true
	 * @param sMT mettre a false si l'adversaire est a gauche et a true si a droite
	 * @param arrete mettre a true
	 * @return un pion qui est le pion de l'utilisateur avec de nouvelles coordonnees apres excecution des adversaires
	 */
	private static pion prAdversaire(pion pi, boolean surUnAdversaire, boolean sMT,boolean arrete)
	{
		/*
		 * Le principe general est le suivant :
		 * on sait que l'on doit prendre l'adversaire (cas 3), on est obligé de prendre les "éventuels" adversaires
		 * qui pourraient se trouver sur le passage. Donc cet algorithme recursif s'occupe de rechercher tous
		 * les eventuels pions que l'on pourrait prendre et de mettre a jour le plateau de telle sorte que l'utilisateur
		 * n'a plus rien a faire.
		 * Le pion passe en parametre est forcement l'adversaire que l'on doit prendre (donc penser a changer les coordonnees
		 * surUnAdversaire est a mettre a true des le debut (evidemment)
		 * surMemeTrace determine si il faut regarder a gauche ou pas. Donc il faut l'initialise en fonction
		 *  d'ou se trouve l'adversaire au debut
		 * arrete est a mettre a true des le debut, c'est l'element du cas de base qui dit a l'algo de s'arreter
		 */
		boolean surMemeTrace = sMT;
		boolean onArrete = arrete;
		// Cas de base
		if(onArrete==false)
		{
			return pi;
		}

		//On cree un nouveau pion pour repositionner le curseur sur le plateau
		pion tmp = new pion(pi.getCoordX(),pi.getCoordY(),pi.isWhite());

		if(!surMemeTrace){
			//On regarde a gauche
			byte a = canMove(pi,true);

			switch(a)
			{
			case 0:
				//Place suivante est sur le bord
				surMemeTrace = true;
			case 1:
				//Place suivante est un pion du meme camp
				surMemeTrace = true;
			case 2:
				//Place suivante est un pion adverse, tout depend si on vient d'un pion adverse ou d'une case libre
				//si pion adverse, on ne peut pas le prendre selon les regles internationnales
				//si case libre, alors on peut envisager de prendre ce pion-là				
				if(surUnAdversaire)
				{
					surMemeTrace = true;
				}else{
					if(pi.isWhite())
					{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()-1),(byte)(pi.getCoordY()+1)));
					}else{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()-1),(byte)(pi.getCoordY()-1)));
					}
					surUnAdversaire=true;
				}
				break;
			case 3:
				//Place suivante est un case libre, tout depend si on vient d'un pion adverse ou d'une case libre
				//si case libre, alors on ne peut plus avancer et on prend le pion précédent
				//si pion adverse, on peut envisager d'avancer d'encore 1
				if(surUnAdversaire)
				{
					if(pi.isWhite())
					{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()-1),(byte)(pi.getCoordY()+1)));
					}else{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()-1),(byte)(pi.getCoordY()-1)));
					}
					surUnAdversaire=false;

					//on supprime l'adversaire de la liste de pions
					byte adv = searchPionI(pi.getCoord());
					lstPions[adv] = null;
				}else{
					surMemeTrace = true;
				}
				break;
			}
		}else{
			//On regarde a droite
			byte b = canMove(pi,false);

			switch(b)
			{
			case 0:
				//Place suivante est sur le bord
				onArrete = false;
			case 1:
				//Place suivante est un pion du meme camp
				onArrete = false;
			case 2:
				//Place suivante est un pion adverse, tout depend si on vient d'un pion adverse ou d'une case libre
				//si pion adverse, on ne peut pas le prendre selon les regles internationnales
				//si case libre, alors on peut envisager de prendre ce pion-là				
				if(surUnAdversaire)
				{
					onArrete = false;
				}else{
					if(pi.isWhite())
					{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()+1)));
					}else{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()-1)));
					}
					surUnAdversaire=true;
				}
				break;
			case 3:
				//Place suivante est un case libre, tout depend si on vient d'un pion adverse ou d'une case libre
				//si case libre, alors on ne peut plus avancer et on prend le pion précédent
				//si pion adverse, on peut envisager d'avancer d'encore 1
				if(surUnAdversaire)
				{
					if(pi.isWhite())
					{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()+1)));
					}else{
						tmp.setCoord(new Coordonnees((byte)(pi.getCoordX()+1),(byte)(pi.getCoordY()-1)));
					}
					surUnAdversaire=false;
					surMemeTrace = false;

					//on supprime l'adversaire de la liste de pions
					byte adv = searchPionI(pi.getCoord());
					lstPions[adv] = null;
				}else{
					onArrete = false;
				}
				break;
			}
		}
		return prAdversaire(tmp,surUnAdversaire,surMemeTrace,onArrete);
	}
	/**
	 * Cette methode regarde si le pion peut bouger
	 * @param pi le pion à jouer
	 * @param sens dans quel sens le joueur veut il faire avancer son pion - 
	 * true à gauche et false à droite (avec le plateau ou les blancs sont toujours les plus proches
	 * @return 0 si il s'agit du bord, 1 si il y a un pion du meme camp, 2 si y a un pion adverse et 3 si il peut avancer
	 */
	private static byte canMove(pion pi, boolean sens)
	{
		final Coordonnees meh = pi.getCoord();

		if(pi.isWhite())
		{
			if(sens)
			{
				if(meh.getCoordX()==0 || meh.getCoordY()==10)
					return 0;

				pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()-1),(byte) (meh.getCoordY()+1)));
				if(tmp==null)
				{
					return 3;
				}else if(tmp.isWhite()){
					return 1;
				}else{
					return 2;
				}
			}else{
				if(meh.getCoordX()==10 || meh.getCoordY()==10)
					return 0;

				pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()+1),(byte) (meh.getCoordY()+1)));
				if(tmp ==null)
				{
					return 3;
				}
				else if(tmp.isWhite()){
					return 1;
				}else{
					return 2;
				}

			}			
		}else{
			if(sens)
			{
				if(meh.getCoordX()==0 || meh.getCoordY()==0)
					return 0;

				pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()-1),(byte) (meh.getCoordY()-1)));
				if(tmp==null)
				{
					return 3;
				}
				else if(tmp.isWhite())
				{
					return 1;
				}
				else{
					return 2;
				}

			}else{
				if(meh.getCoordX()==10 || meh.getCoordY()==0)
					return 0;

				pion tmp = searchPion(new Coordonnees((byte) (meh.getCoordX()+1),(byte) (meh.getCoordY()-1)));
				if(tmp==null)
				{
					return 3;
				}
				else if(tmp.isWhite())
				{
					return 1;
				}
				else{
					return 2;
				}
			}			
		}
	}
	private static pion searchPion(Coordonnees co)
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
	private static byte searchPionI(Coordonnees co)
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

	// Interaction avec l'utilisateur

	/**
	 * Cette fonction donne a l'utilisateur des informations sur tous les pions qu'il
	 * possede et demande aussi quel pion il veut bouger.
	 * @param joueur - true pour les blanc, false pour les noirs
	 * @return l'indice dans le tableau lstPions correspondant au pions que le joueur a decide de bouger
	 */
	private static pion interaction(boolean joueur)
	{
		// Ici on affiche seulement tous les pions de l'utilisateur
		printEtatSonJeu(joueur);

		// Maintenant on va prendre des coordonnees

		System.out.println("Indiquez les coordonnees du pion que vous voulez bouger");
		byte X = 0;
		byte Y = 0;
		byte indice;
		boolean boucle = true, gdBoucle = true;
		do
		{
			do{
				System.out.println("X : ");
				X = InOut.getByte();
				if(X>=10 || X<0)
				{
					System.out.println("X est en dehors du plateau, Reesayez un autre s'il vous plait");
				}else{
					boucle = false;
				}
			}while(boucle);
			boucle = true;
			do{
				System.out.println("Y : ");
				Y = InOut.getByte();
				if(Y>=10 || Y<0)
				{
					System.out.println("Y est en dehors du plateau, Reesayez un autre s'il vous plait");
				}else{
					boucle = false;
				}
			}while(boucle);
			Coordonnees coo = new Coordonnees(X,Y);
			indice = searchPionI(coo);
			if(indice == -1)
			{
				System.out.println("Ce pion n'existe pas !");
			}
			else{
				gdBoucle = false;
			}
		}while(gdBoucle);
		final pion resultat = lstPions[indice];
		return resultat;
	}
	/**
	 * cette fonction affiche l'etat des pions d'un joueur
	 * @param joueur par convention, blanc = true et noir = false
	 */
	private static void printEtatSonJeu(boolean joueur)
	{
		System.out.println(joueur?"Joueur 1 - blanc":"Joueur 2 - noir");
		System.out.println();
		if(joueur)
		{
			for(int i=0;i<10;i++)
			{
				System.out.print(lstPions[i].toString() + " | ");
			}
			for(int i=10;i<20;i++)
			{
				System.out.print(lstPions[i].toString() + " | ");
			}
		}else{
			for(int i=20;i<30;i++)
			{
				System.out.print(lstPions[i].toString() + " | ");
			}
			for(int i=30;i<40;i++)
			{
				System.out.print(lstPions[i].toString() + " | ");
			}
		}
	}
	/**
	 * 
	 * @param joueur par convention, blanc = true et noir = false
	 * @return true si le joueur a gagne et false si la partie n'est toujours pas terminee
	 */
	private static boolean victory(boolean joueur)
	{
		boolean resultat = true;
		if(joueur)
		{
			for(int i=0; i<20;i++)
			{
				if(lstPions[i] != null)
				{
					resultat = false;
				}
			}
		}else{
			for(int i=20;i<40;i++)
			{
				if(lstPions[i] != null)
				{
					resultat = false;
				}
			}
		}
		return resultat;
	}
}
/**
 * 
 * @author vinsifroid
 * @version indev
 */
class Coordonnees{
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

/**
 * 
 * @author vinsifroid
 * @version indev
 */
class pion{
	private Coordonnees Coo;
	private String ID;
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
		ID = "name";
	}
	/**
	 * 
	 * @param X
	 * @param Y
	 * @param couleur true for white, false for black
	 * @param ID un identifiant (par ex: PN1)
	 * @category constructor
	 */
	public pion(byte X, byte Y, boolean couleur, String ID)
	{
		Coo.setX(X);
		Coo.setY(Y);
		this.couleur = couleur;
		this.dame = false;
		this.ID = ID;
	}
	public String toString()
	{
		String msg= "";
		if(couleur)
		{
			msg = "Blanc";
		}else{
			msg = "Noir";
		}
		return ID + " - " + msg +  " - X: " + Coo.getCoordX() + ",Y: " + Coo.getCoordY();
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
	public byte getCoordX()
	{
		return Coo.getCoordX();
	}
	public byte getCoordY()
	{
		return Coo.getCoordY();
	}
	public Coordonnees getCoord()
	{
		return Coo;
	}
	public String getID()
	{
		return ID;
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
	public void setCoord(Coordonnees hey)
	{
		Coo = hey;
	}
	public void setName(String name)
	{
		this.ID = name;
	}
}