package fichier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Une classe qui gère les flux d'écriture d'un fichier
 * @author vinsifroid
 * @since v1.0-eta
 */
@SuppressWarnings("serial")
public class FichierW extends File 
{
	private String filename;
	private PrintWriter PW;
	private boolean ouvert;

	public FichierW(String filename)
	{
		super(filename);
		if(!exists() && !isFile())
		{
			try {
				throw new FileNotFoundException(filename + " - wrong filename or path");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println(e);
			}
		}
		this.filename = filename;		
		PW = null;
		ouvert = false;
	}

	public void ouvrirFuxWriter(boolean append)
	{
		if(ouvert)
		{
			System.err.println("Error - stream already open");
		}
		else{
			try{
				PW = new PrintWriter(new FileWriter(filename, append));
			}catch(IOException e){
				System.err.println(e);
				System.exit(-1);
			}
			ouvert = true;
		}
	}

	public void fermerFluxWriter()
	{
		if(ouvert)
		{
			PW.close();
			ouvert = false;
		}else{
			System.err.println("No Stream are open");
		}
	}

	/**
	 * @param tmp is the integer to write in the next line of the file
	 */
	public void ecrireInt(int tmp)
	{		
		String chaine = "";
		chaine = String.valueOf(tmp);
		assert(chaine != null) : "Cette chaine est nulle !";
		PW.println(chaine);	
	}
	/**
	 * @param tmp is the double to write in the next line of the file
	 */
	public void ecrireDouble(double tmp)
	{		
		String chaine = "";
		chaine = String.valueOf(tmp);
		assert(chaine != null) : "Cette chaine est nulle !";
		PW.println(chaine);
	}
	/**
	 * @param tmp is the String to write in the next line of the file. tmp != null
	 */
	public void ecrireString(String tmp)
	{
		assert(tmp != null) : "Il s'agit d'un String vide !";
		PW.println(tmp);
	}
	
	/**
	 * Create a new PrintWriter with the name of the file stocked in the object Fichier
	 */
	public void setNewPrintWriter() {
		try {
			PW = new PrintWriter(new FileWriter(filename));
		}
		catch (IOException e) {			
			System.err.println(e);
			System.exit(-1);
		}
	}

	/**
	 * Force the System to write with a flush() statement
	 */
	public void forcerEcriture()
	{
		PW.flush();
	}

	/**
	 * Write a new empty line. Only in 'W' or 'E' mode !
	 */
	public void introduireEspace() {		
		PW.println("");	
	}
}
