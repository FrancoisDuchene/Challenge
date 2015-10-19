import java.io.*;
//import java.lang.Exception.*;

public class Fichier
{
	private BufferedWriter fW;
	private BufferedReader fR;
	private char mode = 'X';
	private String nomFichier = "";
	
	//The Constructor
	/**
	 * @category Constructor
	 */
	public Fichier()
	{				
			try {
				fR = new BufferedReader(new FileReader(new File("src/dico.txt")));
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}			
			try {
				fW = new BufferedWriter(new FileWriter(new File("src/dico.txt"),true));
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
	}
	//Other Routines
	/**
	 * @param mode who need R or L for Reading and W or E for Writing
	 * Print the mode currently used
	 */
	public void modeAffiche(char mode)
	{
		assert(mode != 'X') : "Un objet de type FIchier n'a pas été ouvert correctement et le mode n'est pas initialise !";
		System.out.println("Le Mode Actuel est : " + mode);
	}
	/**
	 * @return Int
	 *  The function return the length of the file currently used 
	 */
	public int longueurFichier()
	{
		String str1 = "";
		int longueur = 0;		
		for(int i=0; ;i++)
		{			
			str1 = lire();
			if(str1 == null)
			{
				longueur = i;		
				setNewBufferedReader();
				break;
			}
		}
		return longueur;
	}
	/**
	 * Go to the end of File
	 */
	public void toEnd()
	{
		String str1 = "";
		do
		{
			str1 = lire();			
		}while(str1 != null);
	}
	/**
	 * Create a new Buffered Reader with the name of the file stocked in the object Fichier
	 */
	public void setNewBufferedReader()
	{
		try {
			fR = new BufferedReader(new FileReader(new File(nomFichier)));
		} catch (FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
	}
	/**
	 * Create a new Buffered Writer with the name of the file stocked in the object Fichier
	 */
	public void setNewBufferedWriter()
	{
		try {
			fW = new BufferedWriter(new FileWriter(new File(nomFichier),true));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	/**
	 * Set a new Mark in the file at n position
	 * @param n
	 */
	public void setMark(int n)
	{
		try	{
			fR.mark(n);
		} catch(IOException e) {
			e.printStackTrace();
		}				
	}	
	/**
	 * Open the file with the specified name and the specified mode (Reading or Writing)
	 * @param nomDuFichier
	 * @param s
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
				fW = new BufferedWriter(new FileWriter(new File(nomDuFichier),true));		
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
		assert(chaine != null) : "Cette chaine est nulle !";
			fW.write(chaine, 0, chaine.length());
			fW.newLine();		
	}
	/*
	 * @pre tmp != NULL
	 * @post ecrit dans le fichier le string reçu en paramètre
	 */
	public void ecrireString(String tmp) throws IOException
	{		
		assert(tmp != null) : "Il s'agit d'un String vide !";
			fW.write(tmp);
			fW.newLine();		
	}
	/*
	 * @pre mode = W or E
	 * @post add a new space at the current line
	 */
	public void introduireEspace()
	{		
		if(mode == 'W' || mode == 'E')
		{
			try {
				fW.newLine();
			} catch (IOException e) {				
				e.printStackTrace();
			}
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
	public boolean equalsMots(String mot1, Fichier fi)
	{		
		setNewBufferedReader();
		String mot2 = "";
		for(int i = 0; i < fi.longueurFichier();i++)
		{			
			mot2 = lire(i);
			if(mot1.equals(mot2))
			{
				return false;
			}			
		}
		return true;
	}
	/*
	 * @pre avoir ouvert un flux						
	 * @post ferme le flux précédemment ouvert
	 */
	public void fermer()
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
		}
		catch(IOException e)
		{
			System.out.println("ERROR : IOEXCEPTION");			
		}
	}

}
