import java.io.*;
import java.util.Properties;

public class Config
{
	//variables generales
	private String name;
	private byte language;
	//variables PlusMoins
	private byte joueurs_PlusMoins;
	private int limMax_PlusMoins;
	//variables Pendu
	private short vies_pendu;
	private byte joueurs_pendu;
	//variables MasterMind
	private int vies_MasterMind;
	private int difficulty_MasterMind;
	private Properties force;
	
	public Config(String name)
	{
		this.name = name;
		this.language = challenge.getLanguage();
		this.joueurs_PlusMoins = plusMoins.getJoueur();
		this.limMax_PlusMoins = plusMoins.getLimMax();
		this.vies_pendu = pendu.getVies();
		this.joueurs_pendu = pendu.getJoueurs();
		this.vies_MasterMind = Mastermind.getVies();
		this.difficulty_MasterMind = Mastermind.getDifficulte();
		this.force = new Properties();
	}
	/**
	 * Save the Configuration in a properties file
	 */
	public void saveConfig()
	{
		final String filename = "saves/" + name + ".properties";
		FileOutputStream luke = null;
		
		//On ajoute les éléments à l'object représentant toutes les propriétés.
		force.setProperty("Language", Byte.toString(language));
		force.setProperty("joueurs_PlusMoins", Byte.toString(joueurs_PlusMoins));
		force.setProperty("limMax_PlusMoins", Integer.toString(limMax_PlusMoins));
		force.setProperty("vies_pendu", Short.toString(vies_pendu));
		force.setProperty("joueurs_pendu", Byte.toString(joueurs_pendu));
		force.setProperty("vies_MasterMind", Integer.toString(vies_MasterMind));
		force.setProperty("difficulty_MasterMind", Integer.toString(difficulty_MasterMind));
		//force.list(System.out); si on veut les le documents
		
		try {
			File fichier = new File(filename);
			luke = new FileOutputStream(fichier);
			force.store(luke, " Proprietes");			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.err.println(e1.getMessage());
		}catch (IOException e2) {
			e2.printStackTrace();
			System.err.println(e2.getMessage());
		}catch (Exception e3) {
			e3.printStackTrace();
			System.err.println(e3.getMessage());
		}finally{
			try{
				luke.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
	}
	public void chargeConfig()
	{
		final String filename = "saves/" + name + ".properties";
		FileInputStream leia = null;
		
		try {
			File fichier = new File(filename);
			leia = new FileInputStream(fichier);
			force.load(leia);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.err.println(e1.getMessage());
		}catch (IOException e2) {
			e2.printStackTrace();
			System.err.println(e2.getMessage());
		}catch (Exception e3) {
			e3.printStackTrace();
			System.err.println(e3.getMessage());
		}finally{
			try{
				leia.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
		language = Byte.parseByte(force.getProperty("Language"));
		joueurs_PlusMoins = Byte.parseByte(force.getProperty("joueurs_PlusMoins"));
		limMax_PlusMoins = Integer.parseInt(force.getProperty("limMax_PlusMoins"));
		vies_pendu = Short.parseShort(force.getProperty("vies_pendu"));
		joueurs_pendu = Byte.parseByte(force.getProperty("joueurs_pendu"));		
		vies_MasterMind = Integer.parseInt(force.getProperty("vies_MasterMind"));
		difficulty_MasterMind = Integer.parseInt(force.getProperty("difficulty_MasterMind"));
	}
	public void paramInto()
	{
		this.language = challenge.getLanguage();
		this.joueurs_PlusMoins = plusMoins.getJoueur();
		this.limMax_PlusMoins = plusMoins.getLimMax();
		this.vies_pendu = pendu.getVies();
		this.joueurs_pendu = pendu.getJoueurs();
		this.vies_MasterMind = Mastermind.getVies();
		this.difficulty_MasterMind = Mastermind.getDifficulte();
	}
	public void paramExo()
	{
		challenge.setLanguage(language);
		pendu.setParam(vies_pendu, joueurs_pendu);
		plusMoins.setParam(limMax_PlusMoins, joueurs_PlusMoins);
		Mastermind.setParam(difficulty_MasterMind, vies_MasterMind);
	}
	public Properties getProp() {
		return force;
	}
	public void setProp(Properties prop) {
		this.force = prop;
	}
}
