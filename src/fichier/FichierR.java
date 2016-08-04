package fichier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Une classe qui gère les flux de lecture d'un fichier
 * @author vinsifroid
 * @since v1.0-delta
 *
 */
@SuppressWarnings("serial")
public class FichierR extends File
{
	/**
	 * Le nom du fichier ainsi que son chemin (les 2 en même temps)
	 */
	private String filename;
	private BufferedReader BR;
	private boolean ouvert;

	public FichierR(String filename)
	{
		super(filename);
		if(exists() && isFile())
		{
			try {
				throw new FileNotFoundException(filename + " - wrong filename or path");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println(e);
			}
		}
		this.filename = filename;		
		BR = null;
		ouvert = false;
	}

	// Gestionnaires de flux

	public void ouvrirFluxReader()
	{
		if(ouvert)
		{
			System.err.println("Error - stream already open");
		}
		else{
			try{
				BR = new BufferedReader(new FileReader(filename));
			}catch(FileNotFoundException e){
				System.err.println(e);
				System.exit(-1);
			}
			ouvert = true;
		}
	}
	public void fermerFluxReader()
	{
		if(ouvert)
		{
			try{
				BR.close();
			}catch(IOException e){
				System.err.println(e);
				System.exit(-1);
			}
			ouvert = false;
		}else{
			System.err.println("No Stream are open");
		}
	}

	/**
	 * @return the current String read from the file
	 */
	public String lire() {
		if(canRead())
		{
			try {
				String chaine = BR.readLine();
				return chaine;
			}
			catch(IOException e) {		
				System.err.println(e.getMessage());
				System.exit(-1);
				return "Error IOException";
			}
		}
		return "Error don't have the right to read the file";
	}
	/**
	 * @param n the number of line to skip
	 * @return the current String read from the file
	 */
	public String lire(int n) {
		if(canRead())
		{
			try {
				String chaine = "";
				for(int i =0; i<n;i++) {
					chaine = BR.readLine();
				}
				return chaine;
			}
			catch(IOException e) {
				System.err.println(e.getMessage());
				System.exit(-1);
				return "Error IOException";
			}
		}
		return "Error don't have the right to read the file";
	}

	/**
	 * Create a new BufferedReader with the name of the file stocked in the object FichierR
	 */
	public void setNewBufferedReader() {
		try {
			BR = new BufferedReader(new FileReader(new File(filename)));
		}
		catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
	
	/**
	 * Check in the file if the word is inside
	 * @param mot1 the String to check
	 * @return true if the word exist in the file and false if not
	 */
	public boolean equalsMots(String mot1) {
		setNewBufferedReader();
		String mot2 = "";
		for(int i = 0; i < length();i++) {
			mot2 = lire(i);
			if(mot1.equals(mot2)) {
				return false;
			}
		}
		return true;
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
}
