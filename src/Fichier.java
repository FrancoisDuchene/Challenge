import java.io.*;
import java.lang.Exception.*;

public class Fichier
{
	private BufferedWriter fW;
	private BufferedReader fR;
	private char mode;
	
	/*
	 * @pre -
	 * @post Cette méthode ouvre un flux. Renvoie True si tout s'est déroulé correctement et false si une erreur est apparue
	 */
	public boolean ouvrir(String nomDuFichier, String s)
	{
		try
		{	
			mode = (s.toUpperCase()).charAt(0);
		
			if(mode == 'R' || mode == 'L')		
			{		
				fR = new BufferedReader(new FileReader(new File(nomDuFichier)));		
			}		
			else if(mode == 'W' || mode == 'E')		
			{		
				fW = new BufferedWriter(new FileWriter(new File(nomDuFichier)));		
			}
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	public void ecrireInt(int tmp) throws IOException
	{
		String chaine = "";
		chaine = String.valueOf(tmp);
		if(chaine != null)
		{
			fW.write(chaine, 0, chaine.length());
			fW.newLine();
		}
	}
	public void ecrireString(String tmp) throws IOException
	{
		String chaine = "";
		if(chaine != null)
		{
			fW.write(chaine, 0, chaine.length());
			fW.newLine();
		}
	}
	/*
	 * @pre -
	 * @post lit un fichier et renvoie une string si cela s'est bien passé ou Error IOExcpetion si cette erreur est apparue
	 */
	public String lire()
	{
		try
		{
			String chaine = fR.readLine();
			return chaine;
		}
		catch(IOException e)
		{
			System.out.println(" Erreur de lecture");
			return "Error IOException";
		}
	}
	public void fermer() throws IOException
	{
		if(mode == 'R' || mode == 'L')
		{
			fR.close();
		}
		else if(mode == 'W' || mode == 'E')
		{
			fW.close();
		}
		
	}

}
