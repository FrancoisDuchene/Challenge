package sound;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import fichier.FichierR;
import source.challenge;

public class SoundPlayer extends Thread
{
	private Position curPosition;
	private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
	enum Position {LEFT, RIGHT, NORMAL}

	public SoundPlayer()
	{
		curPosition = Position.NORMAL;
	}

	public void run()
	{
		while(challenge.continuons)
		{

			File soundFile = rdmMusique();
			if(soundFile==null)
				return;

			//Declaration des Objets utiles
			AudioInputStream audioInputStream = null;
			AudioFormat format = null;
			SourceDataLine auline = null;
			DataLine.Info info = null;

			try {
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				format = audioInputStream.getFormat();
				info = new DataLine.Info(SourceDataLine.class, format);
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format);

				if (auline.isControlSupported(FloatControl.Type.PAN)) {
					FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
					if (curPosition == Position.RIGHT) {
						pan.setValue(1.0f);
					} else if (curPosition == Position.LEFT) {
						pan.setValue(-1.0f);
					}
				}
				auline.start();
				int nBytesRead = 0;
				byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

				while (nBytesRead != -1 && challenge.continuons) 
				{
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					if (nBytesRead >= 0) {
						auline.write(abData, 0, nBytesRead);
					}
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			} finally {
				if(auline != null)
				{
					auline.drain();
					try {
						if(audioInputStream != null)
						{
							audioInputStream.close();
						}					
					} catch (IOException e) {
						e.printStackTrace();
					}
					auline.close();
				}
			}
		}
	}


	public File rdmMusique()
	{
		final String filenameL = "res/data/mus.txt";
		FichierR f = new FichierR(filenameL);
		f.ouvrirFluxReader();
		int nbrRdm = nbrRandom(f.longueurFichier());
		for(int i=0;i<nbrRdm;i++)
		{
			f.lire();
		}
		final String fileName = "res/sound/" + f.lire();
		File soundFile = new File(fileName);
		if (!soundFile.exists()) {
			System.err.println("Wave file not found: " + fileName);
			return null;
		}
		return soundFile;
	}
	/**
	 * 
	 * @param longueur the length of the file currently used
	 * @return a random number between 0 and the file length
	 */
	private static int nbrRandom(int nbMusique) {
		double nbr = Math.random();
		nbr = nbr*nbMusique;
		int nbrRandom = (int) nbr;
		return Math.abs(nbrRandom);
	}

	public void setCurToLeft()
	{
		curPosition = Position.LEFT;
	}
	public void setCurToRight()
	{
		curPosition = Position.RIGHT;
	}
	public void setCurToNormal()
	{
		curPosition = Position.NORMAL;
	}
}