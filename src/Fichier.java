import java.io.*;
//import java.lang.Exception.*;

public class Fichier
{
	private BufferedWriter fW;
	private BufferedReader fR;
	private char mode = 'X';
	private String nomFichier = "";
	
	/**
	 * @param mode == 'R' | 'L' | 'W' | 'E'
	 * @see print the current mode
	 */
	public void modeAffiche(char mode)
	{
		System.out.println("Le Mode Actuel est : " + mode);
	}
	/*
	 * @pre necessite le mode Lecture
	 * @post retoure la longueur du Fichier
	 */
	public int longueurFichier()
	{
		String str1 = "";
		int longueur = 0;		
		for(int i=0; ;i++)
		{			
			str1 = lire();
			if(str1.equals("EOF"))
			{
				longueur = i;		
				try {
					fR = new BufferedReader(new FileReader(new File(nomFichier)));
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				break;
			}
		}
		return longueur;
	}
	public void setNewBufferedReader()
	{
		try {
			fR = new BufferedReader(new FileReader(new File(nomFichier)));
		} catch (FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
	}
	public void setNewBufferedWriter()
	{
		try {
			fW = new BufferedWriter(new FileWriter(new File(nomFichier)));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void setMark(int n)
	{
		try
		{
			fR.mark(n);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}				
	}
	/*
	 * @pre -
	 * @post Cette méthode ouvre un flux. Renvoie True si tout s'est déroulé correctement et false si une erreur est apparue
	 */
	public void ouvrir(String nomDuFichier, String s)
	{			
			mode = (s.toUpperCase()).charAt(0);		
			nomFichier = nomDuFichier;
		try
		{
			if(mode == 'R' || mode == 'L')		
			{		
				fR = new BufferedReader(new FileReader(new File(nomDuFichier)));					
			}		
			else if(mode == 'W' || mode == 'E')		
			{		
				fW = new BufferedWriter(new FileWriter(new File(nomDuFichier)));		
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("\nIOException ERROR");
		}		
	}
	/*
	 * @pre -
	 * @post ecrit dans le fichier le int reçu en paramètre
	 */	
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
	/*
	 * @pre tmp != NULL
	 * @post ecrit dans le fichier le string reçu en paramètre
	 */
	public void ecrireString(String tmp) throws IOException
	{		
		if(tmp != null)
		{
			fW.write(tmp, 0, tmp.length());
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
	public String lire(int n)
	{
		try
		{
			String chaine = "";
			for(int i =0; i<n;i++)
			{
			chaine = fR.readLine();				
			}
			return chaine;
		}
		catch(IOException e)
		{
			return "Error IOException";
		}
	}
	/*
	 * @pre avoir ouvert un flux
	 * @post ferme le flux précédemment ouvert
	 */
	public boolean fermer()
	{
		try
		{
			if(mode == 'R' || mode == 'L')
			{
				fR.close();
			}	
			else if(mode == 'W' || mode == 'E')
			{
				fW.close();
			}
			return true;
		}
		catch(IOException e)
		{
			System.out.println("ERROR : IOEXCEPTION");
			return false;
		}
	}

}
