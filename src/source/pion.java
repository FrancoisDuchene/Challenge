package source;

/**
 * 
 * @author vinsifroid
 * @version indev
 */
public class pion{
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