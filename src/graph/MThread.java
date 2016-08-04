package graph;
import sound.SoundPlayer;

public class MThread extends Thread
{
	private String name;
	private PageAccueil pageAccueil;

	public MThread(String name)
	{
		super(name);
		this.name = name;
	}

	public void run()
	{
		if(name.equals("Musique"))
		{
			SoundPlayer leia = new SoundPlayer();
			leia.start();
		}
		else
		{
			new PageAccueil();
		}
	}
}