import java.io.*;
//import java.lang.Exception.*;

public class Fichier {
	private BufferedReader fR;
	private PrintWriter pW;
	private char mode = 'X';
	private String nomFichier;	
	/**
	 * @category Constructor
	 */
	public Fichier() {
		try {
			this.nomFichier = "src/dico.txt"; 
			fR = new BufferedReader(new FileReader(nomFichier));			
			pW = new PrintWriter(new FileWriter(nomFichier));
		}  catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(-1);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
	//Other Routines
	/**
	 * @param mode who need R or L for Reading and W or E for Writing
	 * Print the mode currently used
	 */
	public void modeAffiche(char mode) {
		assert(mode != 'X') : "Un objet de type \"Fichier\" n'a pas été ouvert correctement et le mode n'est pas initialise !";
		System.out.println("Le Mode Actuel est : " + mode);
	}

	/**
	 * @return Int
	 *  The function return the length of the file currently used 
	 */
	public int longueurFichier() {
		String str1 = "";
		int longueur = 0;
		for(int i=0; ;i++) {
			str1 = lire();
			if(str1 == null) {
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
	public void toEnd() {
		String str1 = "";
		do {
			str1 = lire();
		}while(str1 != null);
	}

	/**
	 * Create a new BufferedReader with the name of the file stocked in the object Fichier
	 */
	public void setNewBufferedReader() {
		try {
			fR = new BufferedReader(new FileReader(new File(nomFichier)));
		}
		catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}

	/**
	 * Create a new PrintWriter with the name of the file stocked in the object Fichier
	 */
	public void setNewPrintWriter() {
		try {
			pW = new PrintWriter(new FileWriter(nomFichier));
		}
		catch (IOException e) {			
			System.err.println(e);
			System.exit(-1);
		}
	}

	/**
	 * Set a new Mark in the file at n position
	 * @param n
	 */
	public void setMark(int n) {
		try	{
			fR.mark(n);
		}
		catch(IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}

	/**
	 * Open the file with the specified name and the specified mode (Reading or Writing)
	 * @param nomDuFichier
	 * @param s
	 */
	public void ouvrir(String nomDuFichier, String s) {
		mode = (s.toUpperCase()).charAt(0);
		nomFichier = nomDuFichier;
		try {
			if(mode == 'R' || mode == 'L') {
				fR = new BufferedReader(new FileReader(new File(nomDuFichier)));
			}
			else if(mode == 'W' || mode == 'E') {
				pW = new PrintWriter(new FileWriter(nomDuFichier));
			}
		}
		catch(IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}

	/**
	 * Force the System to write with a flush() statement
	 */
	public void forcerEcriture()
	{
		pW.flush();
	}

	/**
	 * @param tmp is the integer to write in the next line of the file
	 */
	public void ecrireInt(int tmp)
	{		
		String chaine = "";
		chaine = String.valueOf(tmp);
		assert(chaine != null) : "Cette chaine est nulle !";
		pW.println(chaine);		
	}

	/**
	 * @param tmp is the double to write in the next line of the file
	 */
	public void ecrireDouble(double tmp)
	{
		String chaine = "";
		chaine = String.valueOf(tmp);
		assert(chaine != null) : "Cette chaine est nulle !";
		pW.println(chaine);
	}

	/**
	 * @param tmp is the String to write in the next line of the file. tmp != null
	 */
	public void ecrireString(String tmp)
	{
		assert(tmp != null) : "Il s'agit d'un String vide !";
		pW.println(tmp);
	}

	/**
	 * Write a new empty line. Only in 'W' or 'E' mode !
	 */
	public void introduireEspace() {
		if(mode == 'W' || mode == 'E')			
			pW.println("");	
	}

	/**
	 * @return the current String read from the file
	 */
	public String lire() {
		try {
			String chaine = fR.readLine();
			return chaine;
		}
		catch(IOException e) {		
			System.err.println(e);
			System.exit(-1);
			return "Error IOException";
		}
	}

	/**
	 * @param n the number of line to skip
	 * @return the current String read from the file
	 */
	public String lire(int n) {
		try {
			String chaine = "";
			for(int i =0; i<n;i++) {
				fR.readLine();				
			}
			return chaine;
		}
		catch(IOException e) {
			System.err.println(e);
			System.exit(-1);
			return "Error IOException";
		}
	}
	/**
	 * Check in the file if the word in param is already in
	 * @param mot1 the String to check
	 * @return true if the word exist in the file and false if not
	 */
	public boolean equalsMots(String mot1) {
		setNewBufferedReader();
		String mot2 = "";
		for(int i = 0; i < this.longueurFichier();i++) {
			mot2 = lire(i);
			if(mot1.equals(mot2)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Close the BufferedReader and the PrintWriter
	 */
	public void fermer() {
		try{			
			fR.close();			
			pW.close();			
		}
		catch(IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}