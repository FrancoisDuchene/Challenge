import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class HighScore {
	
	private byte mode;
	
	private int val1;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private int val9;
	private int val10;
	
	private String specific_Name;
	private String fileName;
	private String [][] tableauScore;
	private Properties blaster;

	public HighScore(byte mode) throws INVALID_MODE
	{
		val1=0;val2=0;val3=0;val4=0;val5=0;val6=0;val7=0;val8=0;val9=0;val10=0;
		blaster = new Properties();
		this.mode = mode;
		this.fileName = null;
		switch(mode)
		{
		case 1:
			this.specific_Name = "pendu";
			this.fileName = "saves/HighScore/" + specific_Name + ".hg";
			break;
		case 2:
			this.specific_Name = "plusMoins";
			this.fileName = "saves/HighScore/" + specific_Name + ".hg";
			break;
		case 3:
			this.specific_Name = "mastermind";
			this.fileName = "saves/HighScore/" + specific_Name + ".hg";
			break;
		case 4:
			this.specific_Name = "puissance4";
			this.fileName = "saves/HighScore/" + specific_Name + ".hg";
			break;
		default:
			this.specific_Name = "error";
			this.fileName = null;
			throw new INVALID_MODE("Mauvais_mode_donne");
		}
		tableauScore = null;
		charge();
	}
	
	private void updateProperties()
	{
		blaster.setProperty("val1", Integer.toString(val1));
		blaster.setProperty("val2", Integer.toString(val2));
		blaster.setProperty("val3", Integer.toString(val3));
		blaster.setProperty("val4", Integer.toString(val4));
		blaster.setProperty("val5", Integer.toString(val5));
		blaster.setProperty("val6", Integer.toString(val6));
		blaster.setProperty("val7", Integer.toString(val7));
		blaster.setProperty("val8", Integer.toString(val8));
		blaster.setProperty("val9", Integer.toString(val9));
		blaster.setProperty("val10", Integer.toString(val10));		
	}
	/**
	 * Cette fonction sauvegarde le tableau de inclu en memoire
	 */
	public void save()
	{
		FileOutputStream Solo = null;
		try {
			File fichier = new File(fileName);
			Solo = new FileOutputStream(fichier);
			blaster.store(Solo, " Proprietes");
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
				Solo.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
	}
	/**
	 * Cette fonction charge le tableau contenu dans le fichier saves/HighScore/<FichierSpecifique>
	 */
	public void charge()
	{
		FileInputStream C3PO = null;
		
		try {
			File fichier = new File(fileName);
			C3PO = new FileInputStream(fichier);
			blaster.load(C3PO);
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
				C3PO.close();
			}catch(Exception e4){
				e4.printStackTrace();
				System.err.println(e4.getMessage());
			}
		}
		updateProperties();
	}

	public void setFileName(String FileName)
	{
		this.fileName = FileName; 
	}
	public void setMode(byte mode)
	{
		this.mode = mode;
	}
	public void setTableauScore(String [][] tableauScore)
	{
		this.tableauScore = tableauScore;
	}
	public byte getMode()
	{
		return mode;
	}
	public String [][] getTableauScore()
	{
		return tableauScore;
	}
}