package source;

/**
 * 
 * @author vinsifroid
 * @version indev
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